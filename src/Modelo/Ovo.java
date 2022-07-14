package Modelo;

import Auxiliar.Consts;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ovo extends Elemento {
    public boolean aberto = false;
    private static final String[] nomesImagens = {"ovo.png", "ovo_rachado.png"};
    private static final ImageIcon[] imagens = new ImageIcon[2];

    protected Ovo() {
        super(null);
        criaImagens();
        iImage = imagens[0]; // começa normal
    }

    private void criaImagens() {
        for (int i = 0; i < 2; i++) {
            try {
                imagens[i] = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + nomesImagens[i]);
                Image img = imagens[i].getImage();
                BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
                imagens[i] = new ImageIcon(bi);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
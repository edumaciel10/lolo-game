package Modelo;

import javax.swing.*;

import Auxiliar.Consts;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Porta extends Elemento {
    public boolean aberto = true;
    private static final String[] nomesImagens = {"porta.png", "porta_aberta.png"};
    private static final ImageIcon[] imagens = new ImageIcon[2];

    protected Porta() {
        super(null);
        criaImagens();
        iImage = imagens[0]; // começa fechada
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

    public void abrir() {
        this.aberto = true;
        this.iImage = imagens[1];
    }
}

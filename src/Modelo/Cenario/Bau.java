package Modelo.Cenario;

import javax.swing.*;

import Auxiliar.Consts;
import Modelo.Elemento;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bau extends Elemento {
    public boolean aberto = false;
    private static final String[] nomesImagens = {"bau.png", "bau_aberto_com_joia.png", "bau_aberto_sem_joia.png"};
    private static final ImageIcon[] imagens = new ImageIcon[3];

    public Bau() {
        super(null);
        this.bTransponivel = true;
        criaImagens();
        iImage = imagens[0]; // começa fechado
    }

    private void criaImagens() {
        for (int i = 0; i < 3; i++) {
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

    public void abrirComJoia() {
        this.aberto = true;
        this.setbTransponivel(true);
        this.iImage = imagens[1];
    }

    public void abrirSemJoia() {
        this.aberto = true;
        this.iImage = imagens[2];
    }
}

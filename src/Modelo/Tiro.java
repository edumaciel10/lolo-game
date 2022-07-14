package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tiro extends Elemento {
    private static final String[] nomesImagens = {"tiro_baixo.png", "tiro_cima.png", "tiro_esq.png", "tiro_dir.png"};
    private static final ImageIcon[] imagens = new ImageIcon[4];
    private static final byte BAIXO = 0;
    private static final byte CIMA = 1;
    private static final byte ESQUERDA = 2;
    private static final byte DIREITA = 3;
    private final byte direcao;

    protected Tiro(byte direcao) {
        super("tiro.png");
        this.direcao = direcao;
        criaImagens();
        iImage = imagens[direcao];
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        switch (direcao) {
            case BAIXO:
                if(!this.moveDown())
                    Desenho.getCenario().removePersonagem(this);
                break;
            case CIMA:
                if(!this.moveUp())
                    Desenho.getCenario().removePersonagem(this);
                break;
            case ESQUERDA:
                if(!this.moveLeft())
                    Desenho.getCenario().removePersonagem(this);
                break;
            case DIREITA:
                if(!this.moveRight())
                    Desenho.getCenario().removePersonagem(this);
                break;
        }
    }

    private void criaImagens() {
        for (int i = 0; i < 4; i++) {
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

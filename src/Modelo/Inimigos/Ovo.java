package Modelo.Inimigos;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.Cenario.Empurravel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ovo extends Empurravel {
    private static final String[] nomesImagens = {"ovo.png", "ovo_rachado.png"};
    private static final ImageIcon[] imagens = new ImageIcon[2];

    private String tipoInimigo;

    protected Ovo(String tipoInimigo) {
        super(null);
        this.tipoInimigo = tipoInimigo;
        criaImagens();
        iImage = imagens[0]; // começa normal
        bTransponivel = false;
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

    private int n = 0;

    @Override
    public void autoDesenho() {
        n++;
        if (n > 40) {
            iImage = imagens[1];
        }
        if (n > 65) {
            Desenho.getCenario().removePersonagem(this);
            switch (tipoInimigo) {
                case "Modelo.Inimigos.Cobrinha":
                    Cobrinha c = new Cobrinha();
                    c.setPosicao(pPosicao.getLinha(), pPosicao.getColuna());
                    Desenho.getCenario().addPersonagem(c);
                    break;
                case "Modelo.Inimigos.Caveira":
                    Caveira ca = new Caveira(Desenho.getCenario().getFase());
                    ca.setPosicao(pPosicao.getLinha(), pPosicao.getColuna());
                    Desenho.getCenario().addPersonagem(ca);
                    break;
                case "Modelo.Inimigos.Tatu":
                    Tatu t = new Tatu(Desenho.getCenario().getFase());
                    t.setPosicao(pPosicao.getLinha(), pPosicao.getColuna());
                    Desenho.getCenario().addPersonagem(t);
                    break;
            }
        }
        super.autoDesenho();
    }
}
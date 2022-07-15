package Modelo;

import Auxiliar.Consts;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tatu extends Inimigo {
    private static final String[] nomesImagens = {"tatu_baixo.png", "tatu_cima.png",
            "tatu_esquerda.png", "tatu_direita.png"};
    private static final ImageIcon[] imagens = new ImageIcon[4];

    public static final byte BAIXO = 0;
    public static final byte CIMA = 1;
    public static final byte ESQUERDA = 2;
    public static final byte DIREITA = 3;
    private byte ultimoMovimento = BAIXO;

    private Fase fase;

    protected Tatu(Fase fase) {
        super(null);
        this.inimigo = true;
        this.fase = fase;
        criaImagens();
        this.bTransponivel = false;
        iImage = imagens[0];
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

    private int n = 0;
    @Override
    public void autoDesenho() {
        if (n % 3 == 0) {
            Lolo l = (Lolo) fase.get(0);
            int lColuna = l.getColuna();
            int lLinha = l.getLinha();
            int pColuna = pPosicao.getColuna();
            int pLinha = pPosicao.getLinha();
            while (true) {
                if (pColuna < lColuna && pColuna < 11) {
                    if (fase.posicaoEhValida(pLinha, pColuna + 1)) {
                        iImage = imagens[DIREITA];
                        moveRight();
                        break;
                    }
                }
                if (pColuna > lColuna && pColuna > 1) {
                    if (fase.posicaoEhValida(pLinha, pColuna - 1)) {
                        iImage = imagens[ESQUERDA];
                        moveLeft();
                        break;
                    }
                }
                if (pLinha < lLinha && pLinha < 11) {
                    if (fase.posicaoEhValida(pLinha + 1, pColuna)) {
                        iImage = imagens[BAIXO];
                        moveDown();
                        break;
                    }
                }
                if (pLinha > lLinha && pLinha > 1) {
                    if (fase.posicaoEhValida(pLinha - 1, pColuna)) {
                        iImage = imagens[CIMA];
                        moveUp();
                        break;
                    }
                }
                break;
            }
        }
        n++;
        super.autoDesenho();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author junio
 */
public class Caveira extends Inimigo implements Serializable {
    private static final String[] nomesImagens = {"caveira.png", "caveira_baixo.png", "caveira_cima.png",
            "caveira_esquerda.png", "caveira_direita.png"};
    private static final ImageIcon[] imagens = new ImageIcon[5];

    public static final byte PARADA = 0;
    public static final byte BAIXO = 1;
    public static final byte CIMA = 2;
    public static final byte ESQUERDA = 3;
    public static final byte DIREITA = 4;
    private byte ultimoMovimento = BAIXO;

    private boolean movendo = false;
    private Fase fase;

    public Caveira(Fase fase) {
        super(null);
        this.bTransponivel = false;
        this.inimigo = true;
        this.fase = fase;
        criaImagens();
        iImage = imagens[0];
    }

    private void criaImagens() {
        for (int i = 0; i < 5; i++) {
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

    public void setMovendo(boolean movendo) {
        this.movendo = movendo;
        if (movendo)
            iImage = imagens[BAIXO];
    }

    public boolean isMovendo() {
        return movendo;
    }

    private static int n = 0;

    @Override
    public void autoDesenho() {
        if (movendo) {
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
        }
        super.autoDesenho();
    }
}

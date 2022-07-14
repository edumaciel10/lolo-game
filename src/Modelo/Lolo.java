/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Consts;
import Auxiliar.Posicao;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Junio
 */
public class Lolo extends Elemento {
    private static final String[] nomesImagens = {"loloBaixo.png", "loloCima.png", "loloEsquerda.png", "loloDireita.png"};
    private static final ImageIcon[] imagens = new ImageIcon[4];
    private static final byte BAIXO = 0;
    private static final byte CIMA = 1;
    private static final byte ESQUERDA = 2;
    private static final byte DIREITA = 3;
    private static byte ultimoMovimento = BAIXO;

    private static int vidas = 0;

    private int municoes = 0;

    public Lolo() {
        super(null);
        criaImagens();
        iImage = imagens[0]; // começa virado pra baixo
    }

    public void adicionaMunicoes(int quantidadeMunicoes) {
        municoes += quantidadeMunicoes;
    }

    public int getMunicoes() {
        return municoes;
    }

    public void atira(Fase faseAtual) {
        if (municoes > 0) {
            Tiro tiro = new Tiro(ultimoMovimento);
            tiro.pPosicao = new Posicao(pPosicao.getLinha(), pPosicao.getColuna());
            faseAtual.add(tiro);
            municoes--;
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

    private void atualizaImagem() {
        iImage = imagens[ultimoMovimento];
    }

    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }

    public boolean moveUp() {
        ultimoMovimento = CIMA;
        atualizaImagem();
        return this.pPosicao.moveUp();
    }

    public boolean moveDown() {
        ultimoMovimento = BAIXO;
        atualizaImagem();
        return this.pPosicao.moveDown();
    }

    public boolean moveRight() {
        ultimoMovimento = DIREITA;
        atualizaImagem();
        return this.pPosicao.moveRight();
    }

    public boolean moveLeft() {
        ultimoMovimento = ESQUERDA;
        atualizaImagem();
        return this.pPosicao.moveLeft();
    }

    public int getLinha() {
        return this.pPosicao.getLinha();
    }

    public int getColuna() {
        return this.pPosicao.getColuna();
    }

    public void setVida(int vida) {
        vidas = vida;
    }

    public int getVida() {
        return vidas;
    }
}

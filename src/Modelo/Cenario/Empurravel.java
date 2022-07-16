package Modelo.Cenario;

import Modelo.Elemento;

public class Empurravel extends Elemento {
    protected Empurravel(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }

    public boolean moveUp() {
        if (pPosicao.getLinha() > 1) {
            return this.pPosicao.moveUp();
        }
        return false;
    }

    public boolean moveDown() {
        if (pPosicao.getLinha() < 11) {
            return this.pPosicao.moveDown();
        }
        return false;
    }

    public boolean moveRight() {
        if (pPosicao.getColuna() < 11) {
            return this.pPosicao.moveRight();
        }
        return false;
    }

    public boolean moveLeft() {
        if (pPosicao.getColuna() > 1) {
            return this.pPosicao.moveLeft();
        }
        return false;
    }
}

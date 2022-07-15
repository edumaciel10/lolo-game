package Modelo;

public abstract class Inimigo extends Elemento {
    protected Inimigo(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }

    public void morrer(Fase faseAtual) {
        Ovo ovo = new Ovo(this.getClass().getName());
        ovo.setPosicao(pPosicao.getLinha(), pPosicao.getColuna());
        faseAtual.add(ovo);
        faseAtual.remove(this);
    }
}
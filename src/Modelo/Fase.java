package Modelo;

import java.util.ArrayList;

public abstract class Fase extends ArrayList<Elemento> {
    public int coracoesRestantes;
    public boolean proximaFase;

    public void montaTudo() {
        this.clear();
    }

    protected void adicionaMoitaBranca(int linha, int coluna) {
        MoitaAmarela ma1 = new MoitaAmarela();
        ma1.setPosicao(linha, coluna);
        this.add(ma1);
    }

    protected void adicionaMoitaVerde(int linha, int coluna) {
        MoitaVerde mv1 = new MoitaVerde();
        mv1.setPosicao(linha, coluna);
        this.add(mv1);
    }

    protected void adicionaCoracao(int linha, int coluna, int quantidadeMunicoes) {
        Coracao c = new Coracao(quantidadeMunicoes);
        c.setPosicao(linha, coluna);
        this.add(c);
    }
}

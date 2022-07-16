package Modelo.Fases;

import Auxiliar.Posicao;
import Modelo.Cenario.Agua;
import Modelo.Cenario.Caixa;
import Modelo.Cenario.MoitaAmarela;
import Modelo.Cenario.MoitaVerde;
import Modelo.Cenario.Coracao;
import Modelo.Elemento;

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

    protected void adicionarAgua(int linha, int coluna) {
        Agua a = new Agua();
        a.setPosicao(linha, coluna);
        this.add(a);
    }

    protected void adicionarCaixa(int linha, int coluna) {
        Caixa c = new Caixa();
        c.setPosicao(linha, coluna);
        this.add(c);
    }

    public boolean posicaoEhValida(int linha, int coluna) {
        Posicao p = new Posicao(linha, coluna);
        for (int i = 1; i < this.size(); i++) {
            if (this.get(i).getPosicao().igual(p)) {
                return false;
            }
        }
        return true;
    }
}

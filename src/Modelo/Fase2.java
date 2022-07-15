package Modelo;

import Auxiliar.Posicao;

public class Fase2 extends Fase {
    public Fase2() {
        /*Cria e adiciona personagens*/
        montaTudo();
    }

    public void montaTudo() {
        this.coracoesRestantes = 7;
        Lolo lLolo = new Lolo();
        lLolo.setPosicao(11, 11);
        this.add(lLolo);

        Bau b = new Bau();
        b.setPosicao(1, 1);
        this.add(b);

        Porta p = new Porta();
        p.setPosicao(0, 7);
        this.add(p);

        Cobrinha c1 = new Cobrinha();
        c1.setPosicao(7, 5);
        this.add(c1);

        Caveira ca1 = new Caveira(this);
        ca1.setPosicao(3, 5);
        this.add(ca1);

        Caveira ca2 = new Caveira(this);
        ca2.setPosicao(6, 8);
        this.add(ca2);

        adicionaMoitasBrancas();
        adicionaMoitasVerdes();
        adicionaCoracoes();
    }

    private void adicionaMoitasBrancas() {
        adicionaMoitaBranca(11, 1);
        adicionaMoitaBranca(11, 4);
        adicionaMoitaBranca(11, 9);
        adicionaMoitaBranca(10, 2);
        adicionaMoitaBranca(8, 3);
        adicionaMoitaBranca(8, 5);
        adicionaMoitaBranca(8, 6);
        adicionaMoitaBranca(8, 8);
        adicionaMoitaBranca(6, 4);
        adicionaMoitaBranca(6, 9);
        adicionaMoitaBranca(5, 1);
        adicionaMoitaBranca(5, 10);
        adicionaMoitaBranca(4, 2);
        adicionaMoitaBranca(4, 4);
        adicionaMoitaBranca(4, 6);
        adicionaMoitaBranca(3, 2);
        adicionaMoitaBranca(3, 8);
        adicionaMoitaBranca(2, 10);
    }

    private void adicionaMoitasVerdes() {
        adicionaMoitaVerde(1, 2);
        adicionaMoitaVerde(1, 4);
        adicionaMoitaVerde(1, 5);
        adicionaMoitaVerde(1, 6);
        adicionaMoitaVerde(1, 8);
        adicionaMoitaVerde(2, 6);
        adicionaMoitaVerde(2, 4);
        adicionaMoitaVerde(2, 8);
        adicionaMoitaVerde(3, 10);
        adicionaMoitaVerde(4, 8);
        adicionaMoitaVerde(4, 10);
        adicionaMoitaVerde(5, 2);
        adicionaMoitaVerde(5, 6);
        adicionaMoitaVerde(6, 3);
        adicionaMoitaVerde(6, 5);
        adicionaMoitaVerde(6, 7);
        adicionaMoitaVerde(7, 1);
        adicionaMoitaVerde(7, 11);
        adicionaMoitaVerde(8, 4);
        adicionaMoitaVerde(8, 7);
        adicionaMoitaVerde(8, 9);
        adicionaMoitaVerde(8, 10);
        adicionaMoitaVerde(9, 8);
        adicionaMoitaVerde(10, 4);
        adicionaMoitaVerde(10, 6);
        adicionaMoitaVerde(10, 7);
        adicionaMoitaVerde(10, 10);
        adicionaMoitaVerde(11, 2);
        adicionaMoitaVerde(11, 10);
    }

    private void adicionaCoracoes() {
        adicionaCoracao(1, 3, 0);
        adicionaCoracao(1, 7, 0);
        adicionaCoracao(6, 1, 2);
        adicionaCoracao(6, 6, 0);
        adicionaCoracao(8, 11, 0);
        adicionaCoracao(9, 7, 2);
        adicionaCoracao(10, 1, 0);
    }
}

package Modelo;

public class Fase3 extends Fase {
    public Fase3() {
        /*Cria e adiciona personagens*/
        Lolo lLolo = new Lolo();
        lLolo.setPosicao(8, 6);
        this.add(lLolo);

        Porta p = new Porta();
        p.setPosicao(0, 6);
        this.add(p);

        adicionaMoitasBrancas();
        adicionaMoitasVerdes();
        adicionaCoracoes();
    }

    private void adicionaMoitaBranca(int linha, int coluna) {
        MoitaAmarela ma1 = new MoitaAmarela();
        ma1.setPosicao(linha, coluna);
        this.add(ma1);
    }

    private void adicionaMoitasBrancas() {
        adicionaMoitaBranca(1, 1);
        adicionaMoitaBranca(3, 1);
        adicionaMoitaBranca(10, 1);
        adicionaMoitaBranca(11, 1);
        adicionaMoitaBranca(11, 4);
        adicionaMoitaBranca(10, 5);
        adicionaMoitaBranca(11, 5);
        adicionaMoitaBranca(10, 6);
        adicionaMoitaBranca(2, 8);
        adicionaMoitaBranca(3, 8);
        adicionaMoitaBranca(3, 9);
        adicionaMoitaBranca(4, 9);
        adicionaMoitaBranca(10, 10);
        adicionaMoitaBranca(9, 11);
    }

    private void adicionaMoitaVerde(int linha, int coluna) {
        MoitaVerde mv1 = new MoitaVerde();
        mv1.setPosicao(linha, coluna);
        this.add(mv1);
    }

    private void adicionaMoitasVerdes() {
        adicionaMoitaVerde(4, 1);
        adicionaMoitaVerde(7, 1);
        adicionaMoitaVerde(8, 1);
        adicionaMoitaVerde(9, 1);
        adicionaMoitaVerde(8, 2);
        adicionaMoitaVerde(9, 2);
        adicionaMoitaVerde(2, 3);
        adicionaMoitaVerde(3, 3);
        adicionaMoitaVerde(2, 4);
        adicionaMoitaVerde(3, 4);
        adicionaMoitaVerde(11, 6);
        adicionaMoitaVerde(9, 7);
        adicionaMoitaVerde(10, 7);
        adicionaMoitaVerde(11, 7);
        adicionaMoitaVerde(1, 9);
        adicionaMoitaVerde(2, 9);
        adicionaMoitaVerde(1, 10);
        adicionaMoitaVerde(2, 10);
        adicionaMoitaVerde(3, 10);
        adicionaMoitaVerde(11, 10);
        adicionaMoitaVerde(10, 11);
        adicionaMoitaVerde(11, 11);
    }

    private void adicionaCoracao(int linha, int coluna) {
        Coracao c = new Coracao();
        c.setPosicao(linha, coluna);
        this.add(c);
    }

    private void adicionaCoracoes() {
        adicionaCoracao(2, 1);
        adicionaCoracao(11, 2);
        adicionaCoracao(1, 8);
        adicionaCoracao(11, 8);
        adicionaCoracao(11, 9);
        adicionaCoracao(1, 11);
    }
}

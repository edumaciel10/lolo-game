package Modelo;

public class Fase3 extends Fase {
    public Fase3() {
        montaTudo();
    }

    public void montaTudo() {
        /* Cria e adiciona personagens */
        Lolo lLolo = new Lolo();
        lLolo.setPosicao(11, 6);
        this.add(lLolo);

        Bau b = new Bau();
        b.setPosicao(5, 2);
        this.add(b);

        Porta p = new Porta();
        p.setPosicao(0, 6);
        this.add(p);

        adicionaMoitasBrancas();
        adicionaMoitasVerdes();
        adicionaAguas();
        adicionaCoracoes();
        adicionarCaixas();
    }

    private void adicionaMoitasBrancas() {
        adicionaMoitaBranca(1, 3);
        adicionaMoitaBranca(2, 3);
        adicionaMoitaBranca(2, 4);
        adicionaMoitaBranca(1, 8);
        adicionaMoitaBranca(2, 8);
        adicionaMoitaBranca(2, 7);
        adicionaMoitaBranca(4, 2);
        adicionaMoitaBranca(4, 3);
        adicionaMoitaBranca(4, 4);
        adicionaMoitaBranca(5, 5);
        adicionaMoitaBranca(8, 10);
        adicionaMoitaBranca(9, 9);
        adicionaMoitaBranca(10, 9);
    }

    private void adicionaMoitasVerdes() {
        adicionaMoitaVerde(1, 4);
        adicionaMoitaVerde(1, 5);
        adicionaMoitaVerde(2, 5);
        adicionaMoitaVerde(4, 1);
        adicionaMoitaVerde(4, 5);
        adicionaMoitaVerde(4, 6);
        adicionaMoitaVerde(4, 7);
        adicionaMoitaVerde(4, 8);
        adicionaMoitaVerde(4, 10);
        adicionaMoitaVerde(4, 11);
        adicionaMoitaVerde(8, 9);
        adicionaMoitaVerde(11, 9);
        adicionaMoitaVerde(10, 5);
        adicionaMoitaVerde(9, 6);
    }

    private void adicionaCoracoes() {
        adicionaCoracao(5, 1, 0);
        adicionaCoracao(5, 3, 0);
        adicionaCoracao(8, 2, 0);
        adicionaCoracao(9, 10, 0);
        adicionaCoracao(1, 7, 0);
        adicionaCoracao(3, 3, 0);
    }

    private void adicionaAguas() {
        adicionarAgua(10, 4);
        adicionarAgua(9, 4);
        adicionarAgua(8, 4);
        adicionarAgua(7, 4);
        adicionarAgua(6, 4);
        adicionarAgua(5, 4);
        adicionarAgua(9, 5);
        adicionarAgua(8, 5);
        adicionarAgua(7, 5);
        adicionarAgua(8, 6);
    }

    public void adicionarCaixas() {
        adicionarCaixa(7, 1);
        adicionarCaixa(7, 2);
        adicionarCaixa(7, 3);

        adicionarCaixa(10, 1);
        adicionarCaixa(10, 2);
        adicionarCaixa(10, 3);

        adicionarCaixa(7, 10);
    }

}

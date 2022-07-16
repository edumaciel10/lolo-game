package Modelo.Cenario;

import Modelo.Elemento;

public class Coracao extends Elemento {
    public int getQuantidadeMunicoes() {
        return quantidadeMunicoes;
    }

    private final int quantidadeMunicoes;

    public Coracao(int quantidadeMunicoes) {
        super("coracao.png");
        this.quantidadeMunicoes = quantidadeMunicoes;
    }
}

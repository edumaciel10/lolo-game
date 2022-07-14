package Modelo;

public class Coracao extends Elemento {
    public int getQuantidadeMunicoes() {
        return quantidadeMunicoes;
    }

    private final int quantidadeMunicoes;

    protected Coracao(int quantidadeMunicoes) {
        super("coracao.png");
        this.quantidadeMunicoes = quantidadeMunicoes;
    }
}

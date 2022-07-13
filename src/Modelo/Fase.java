package Modelo;

import Auxiliar.Posicao;

import java.util.ArrayList;

public abstract class Fase extends ArrayList<Elemento> {
  public int coracoesRestantes;
  public boolean proximaFase;
  public Posicao posicaoAbrePorta;
}

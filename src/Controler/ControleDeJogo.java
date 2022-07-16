/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.*;
import Auxiliar.Posicao;
import Modelo.Cenario.Bau;
import Modelo.Cenario.Coracao;
import Modelo.Cenario.Empurravel;
import Modelo.Cenario.Porta;
import Modelo.Fases.Fase;
import Modelo.Inimigos.Caveira;
import Modelo.Inimigos.Inimigo;
import Modelo.Inimigos.Ovo;
import Modelo.Inimigos.Tatu;
import Modelo.Lolo.Lolo;
import Modelo.Lolo.Tiro;

import java.util.ArrayList;

/**
 *
 * @author junio
 */
public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Elemento> e) {
        for (int i = 1; i < e.size() ; i++) {
            e.get(i).autoDesenho();
        }
        e.get(0).autoDesenho();
    }

    public void processaTudo(Fase e) {
        Lolo lolo = (Lolo) e.get(0);
        Bau bau = (Bau) e.get(1);
        Porta porta = (Porta) e.get(2);

        Elemento pTemp;
        for (int i = 1; i < e.size(); i++) {
            pTemp = e.get(i);
            if (pTemp instanceof Tiro) {
                if (colisaoTiro(e, pTemp)) return;
            }
            if (lolo.getPosicao().igual(pTemp.getPosicao())) {
                colisaoInimigos(pTemp);

                if (pTemp.isbTransponivel()) {
                    if(pTemp instanceof Coracao){
                        colisaoCoracoes(e, lolo, bau, pTemp);
                    }

                    if(pTemp instanceof Bau && bau.aberto){
                        colisaoBau(e, bau, porta);
                    }
                }
            }
        }
    }

    private void colisaoBau(Fase e, Bau bau, Porta porta) {
        bau.abrirSemJoia();
        porta.abrir();
        for (Elemento elemento : e) {
            if (elemento instanceof Inimigo || elemento instanceof Ovo) {
                e.remove(elemento);
            }
        }
    }

    private void colisaoCoracoes(Fase e, Lolo lolo, Bau bau, Elemento pTemp) {
        e.coracoesRestantes--;
        if(e.coracoesRestantes == 0){
            for (Elemento elemento : e) {
                if (elemento instanceof Caveira) {
                    ((Caveira) elemento).setMovendo(true);
                }
            }
            bau.abrirComJoia();
        }
        lolo.adicionaMunicoes(((Coracao) pTemp).getQuantidadeMunicoes());
        e.remove(pTemp);
    }

    private void colisaoInimigos(Elemento pTemp) {
        if (pTemp instanceof Tatu) {
            Desenho.getCenario().reiniciaFase();
        }
        if (pTemp instanceof Caveira) {
            if (((Caveira) pTemp).isMovendo()) {
                Desenho.getCenario().reiniciaFase();
            }
        }
    }

    private boolean colisaoTiro(Fase e, Elemento pTemp) {
        for (int j = 1; j < e.size(); j++) {
            Elemento pTemp2 = e.get(j);
            if (pTemp2 != pTemp) {
                if (pTemp.getPosicao().igual(pTemp2.getPosicao())) {
                    if (pTemp2.isInimigo()) {
                        ((Inimigo) pTemp2).morrer(e);
                        e.remove(pTemp);
                        return true;
                    }
                    if (pTemp2 instanceof Ovo) {
                        e.remove(pTemp2);
                        return true;
                    }
                    e.remove(pTemp);
                }

            }
        }
        return false;
    }

    /*
     * Retorna true se a posicao p é válida para Lolo com relacao a todos os
     * personagens no array
     */
    public boolean ehPosicaoValidaLolo(Fase e, Posicao p) {
        // Colisao porta
        Porta porta = (Porta) e.get(2);
        if (p.igual(porta.getPosicao()) && porta.aberto) {
            e.proximaFase = true;
        }

        // Colisao com as paredes
        if (p.getColuna() == 0 || p.getColuna() >= Consts.RES - 1 || p.getLinha() == 0
                || p.getLinha() >= Consts.RES - 1) {
            return false;
        }

        if (colisaoEmpurraveis(e, p))
            return false;

        return true;
    }

    private boolean colisaoEmpurraveis(Fase e, Posicao p) {
        Elemento pTemp;
        for (int i = 1; i < e.size(); i++) {
            pTemp = e.get(i);
            if (pTemp instanceof Empurravel) {
                if (pTemp.getPosicao().igual(p)) {
                    int linha = pTemp.getPosicao().getLinha();
                    int coluna = pTemp.getPosicao().getColuna();
                    switch (((Lolo) e.get(0)).getUltimoMovimento()) {
                        case Lolo.BAIXO:
                            if (!ocupado(e, linha + 1, coluna)) {
                                pTemp.moveDown();
                            }
                            break;
                        case Lolo.CIMA:
                            if (!ocupado(e, linha - 1, coluna)) {
                                pTemp.moveUp();
                            }
                            break;
                        case Lolo.ESQUERDA:
                            if (!ocupado(e, linha, coluna - 1)) {
                                pTemp.moveLeft();
                            }
                            break;
                        case Lolo.DIREITA:
                            if (!ocupado(e, linha, coluna + 1)) {
                                pTemp.moveRight();
                            }
                            break;
                    }
                }
            }
            if (!pTemp.isbTransponivel())
                if (pTemp.getPosicao().igual(p))
                    return true;
        }
        return false;
    }

    private boolean ocupado(Fase f, int linha, int coluna) {
        Posicao p = new Posicao(linha, coluna);
        for (Elemento e : f) {
            if (e.getPosicao().igual(p)) {
                return true;
            }
        }
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Auxiliar.Consts;
import Modelo.Bau;
import Modelo.Coracao;
import Modelo.Elemento;
import Modelo.Fase;
import Modelo.Lolo;
import Modelo.Porta;
import Auxiliar.Posicao;
import java.util.ArrayList;

/**
 *
 * @author junio
 */
public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Elemento> e) {
        for (int i = e.size()-1; i >= 0 ; i--) {
            e.get(i).autoDesenho();
        }
    }

    public void processaTudo(Fase e) {
        Lolo lolo = (Lolo) e.get(0);
        Bau bau = (Bau) e.get(1);
        Porta porta = (Porta) e.get(2);

        Elemento pTemp;
        for (int i = 1; i < e.size(); i++) {
            pTemp = e.get(i);
            if (lolo.getPosicao().igual(pTemp.getPosicao())) {
                if (pTemp.isbTransponivel()) {
                    if(pTemp instanceof Coracao){
                        e.coracoesRestantes--;
                        if(e.coracoesRestantes == 0){
                            bau.abrirComJoia();
                        }
                        e.remove(pTemp);
                    }

                    if(pTemp instanceof Bau && bau.aberto){
                        bau.abrirSemJoia();
                        porta.abrir();
                    }

                    if(pTemp.isInimigo()) {
                        e.remove(pTemp);
                    }
                }
            }
        }
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

        Elemento pTemp;
        for (int i = 1; i < e.size(); i++) {
            pTemp = e.get(i);
            if (!pTemp.isbTransponivel())
                if (pTemp.getPosicao().igual(p))
                    return false;
        }
        return true;
    }
}

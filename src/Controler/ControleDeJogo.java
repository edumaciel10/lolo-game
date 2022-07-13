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
import Auxiliar.Posicao;
import java.util.ArrayList;

/**
 *
 * @author junio
 */
public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Elemento> e) {
        for (int i = 0; i < e.size(); i++) {
            e.get(i).autoDesenho();
        }
    }

    public void processaTudo(Fase e) {
        Lolo lolo = (Lolo) e.get(0);
        Bau bau = (Bau) e.get(1);
        Elemento pTemp;
        for (int i = 2; i < e.size(); i++) {
            pTemp = e.get(i);
            if (lolo.getPosicao().igual(pTemp.getPosicao())) {
                if (pTemp.isbTransponivel()) {
                    if(pTemp instanceof Coracao){
                        e.coracoesRestantes--;
                        if(e.coracoesRestantes == 0){
                            bau.setbTransponivel(true);
                        }
                    }
                    e.remove(pTemp);
                }
            }
        }
    }

    /*
     * Retorna true se a posicao p é válida para Lolo com relacao a todos os
     * personagens no array
     */
    public boolean ehPosicaoValida(ArrayList<Elemento> e, Posicao p) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Auxiliar.Consts;
import Modelo.Coracao;
import Modelo.Elemento;
import Modelo.Lolo;
import auxiliar.Posicao;
import java.util.ArrayList;

/**
 *
 * @author junio
 */
public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Elemento> e){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
    }
    public void processaTudo(ArrayList<Elemento> e){
        Lolo bBomberman = (Lolo)e.get(0);
        Elemento pTemp;
        for(int i = 1; i < e.size(); i++){
            pTemp = e.get(i);
            if(bBomberman.getPosicao().igual(pTemp.getPosicao()))
                if(pTemp.isbTransponivel())
                    e.remove(pTemp);
        }        
    }
    
    /*Retorna true se a posicao p é válida para Lolo com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(ArrayList<Elemento> e, Posicao p){
        // Colisao com as paredes
        if (p.getColuna() == 0 || p.getColuna() >= Consts.RES - 1 || p.getLinha() == 0 || p.getLinha() >= Consts.RES - 1) {
            return false;
        }

        Elemento pTemp;
        for(int i = 1; i < e.size(); i++){
            pTemp = e.get(i);
            if(!pTemp.isbTransponivel())
                if(pTemp.getPosicao().igual(p))
                    return false;
        }        
        return true;
    }
}

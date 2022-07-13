/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Desenho;

import java.io.Serializable;

/**
 *
 * @author junio
 */
public class Fogo extends Elemento{
            
    public Fogo(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bMortal = true;
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(!this.moveRight())
            Desenho.getCenario().removePersonagem(this);
    }
    
}
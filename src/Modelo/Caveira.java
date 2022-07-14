/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;

import java.io.Serializable;

/**
 *
 * @author junio
 */
public class Caveira extends Inimigo implements Serializable{
    public Caveira() {
        super("caveira.png");
        this.bTransponivel = false;
        this.inimigo = true;
    }
}

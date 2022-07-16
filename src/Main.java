/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Controler.Tela;
import Modelo.Inimigos.Cobrinha;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author junio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cobrinha cobrinha = new Cobrinha();
        cobrinha.setPosicao(4, 6);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("cobrinha.lolo");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(cobrinha);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tela tTela = new Tela();
                tTela.setVisible(true);
                tTela.createBufferStrategy(2);
                tTela.go();
            }
        });
    }
}
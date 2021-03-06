/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Tela.java
 *
 * Created on 03/03/2011, 18:28:20
 */
package Controler;

import Modelo.*;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.Fases.Fase;
import Modelo.Fases.Fase1;
import Modelo.Fases.Fase2;
import Modelo.Fases.Fase3;
import Modelo.Lolo.Lolo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author junio
 */
public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private Fase[] fases = new Fase[] { new Fase1(), new Fase2(), new Fase3() };
    private byte indiceFaseAtual = 0;
    private Fase e = fases[indiceFaseAtual];
    private Lolo lLolo = (Lolo) e.get(0);
    private ControleDeJogo cj = new ControleDeJogo();
    public boolean gameOver = false;
    public static boolean winGame = false;
    private Graphics g2;
    private Estado estado = new Estado();
    /**
     * Creates new form tabuleiro
     */
    public Tela() {
        Desenho.setCenario(this);
        initComponents();
        this.addMouseListener(this); /* mouse */
        this.lLolo.setVida(5);
        this.addKeyListener(this); /* teclado */
        /* Cria a janela do tamanho do tabuleiro + insets (bordas) da janela */
        this.setSize((Consts.RES + 1) * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);
    }

    public Fase getFase() {
        return fases[indiceFaseAtual];
    }

    public void addPersonagem(Elemento umPersonagem) {
        e.add(umPersonagem);
    }

    public void removePersonagem(Elemento umPersonagem) {
        e.remove(umPersonagem);
    }

    public Graphics getGraphicsBuffer() {
        return g2;
    }

    public void atualizarEstado () {
        estado.ultimoMovimento = lLolo.getUltimoMovimento();
        estado.indiceFaseAtual = indiceFaseAtual;
    }

    public void carregarEstado (Estado novoEstado) {
        indiceFaseAtual = novoEstado.indiceFaseAtual;
        lLolo.setUltimoMovimento(novoEstado.ultimoMovimento);
        this.fases[indiceFaseAtual] = this.e;
    }


    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /* Criamos um contexto gr??fico */
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);

        if(winGame) {
            win(g);
            return;
        }

        if (gameOver) {
            gameOver(g);
            return;
        }

        /************* Desenha cen??rio de fundo **************/
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES + 1; j++) {
                try {
                    Image newImage;

                    // Pinta de preto parte do menu a direita
                    if (j == Consts.RES) {
                        newImage = Toolkit.getDefaultToolkit()
                                .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "black.png");
                    }
                    // Muro esquerda
                    else if (j == 0) {
                        newImage = Toolkit.getDefaultToolkit()
                                .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "muroEsquerda.png");
                    }
                    // Muro direita
                    else if (j == Consts.RES - 1) {
                        newImage = Toolkit.getDefaultToolkit()
                                .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "muroDireita.png");
                    }
                    // Muro baixo
                    else if (i == Consts.RES - 1) {
                        newImage = Toolkit.getDefaultToolkit()
                                .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "muroBaixo.png");
                    }
                    // Muro cima
                    else if (i == 0) {
                        newImage = Toolkit.getDefaultToolkit()
                                .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "muroCima.png");
                    }
                    // Fundo normal
                    else {
                        newImage = Toolkit.getDefaultToolkit()
                                .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
                    }
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // Imprime ??cones a direita
        // @todo dividir em fun????o
        try {
            Image newImage = Toolkit.getDefaultToolkit()
                    .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "loloBaixo.png");

            g2.drawImage(newImage,
                    13 * Consts.CELL_SIDE, 1 * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

            newImage = Toolkit.getDefaultToolkit()
                    .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + lLolo.getVida()+ ".png");

            g2.drawImage(newImage,
                    13 * Consts.CELL_SIDE, 2 * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

            newImage = Toolkit.getDefaultToolkit()
                    .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "menuTiro.png");

            g2.drawImage(newImage,
                    13 * Consts.CELL_SIDE, 3 * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            
            newImage = Toolkit.getDefaultToolkit()
                .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + lLolo.getMunicoes()+ ".png");
                
            g2.drawImage(newImage,
                    13 * Consts.CELL_SIDE, 4 * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

            
            
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!this.e.isEmpty()) {
            this.cj.desenhaTudo(e);
            this.cj.processaTudo(e);
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }

        if(e.proximaFase) {
            this.proximaFase();
        }
    }

    private void gameOver(Graphics g) {
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES + 1; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit()
                                .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "black.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.setColor(Color.WHITE);
        g.drawString("GAME OVER", 5 * Consts.CELL_SIDE, 5 * Consts.CELL_SIDE);
        g.setFont(new Font("Arial", Font.PLAIN, 40));
        g.drawString("Eduardo Maciel de Matos", 240, 7 * Consts.CELL_SIDE);
        g.drawString("Jo??o Ot??vio da Silva"   , 295, 8 * Consts.CELL_SIDE);
        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }


    private void win(Graphics g) {
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES + 1; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit()
                                .getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "black.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.setColor(Color.WHITE);
        g.drawString("GAME WIN", 5 * Consts.CELL_SIDE, 5 * Consts.CELL_SIDE);
        g.setFont(new Font("Arial", Font.PLAIN, 40));
        g.drawString("Eduardo Maciel de Matos", 240, 7 * Consts.CELL_SIDE);
        g.drawString("Jo??o Ot??vio da Silva"   , 295, 8 * Consts.CELL_SIDE);
        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
        TimerTask task = new TimerTask() {
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.PERIOD);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            this.e.clear();
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            carregaEstado();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            salvaEstado();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            lLolo.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            lLolo.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            lLolo.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            lLolo.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            lLolo.atira(fases[indiceFaseAtual]);
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            reiniciaFase();
            return;
        } else if (e.getKeyCode() == KeyEvent.VK_I) {
            leElementoDeArquivo();
        }


        if (!cj.ehPosicaoValidaLolo(this.e, lLolo.getPosicao())) {
            lLolo.voltaAUltimaPosicao();
        }

        this.setTitle("-> Cell: " + (lLolo.getPosicao().getColuna()) + ", "
                + (lLolo.getPosicao().getLinha()));

        // repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    private void salvaEstado() {
        try {
            File tanque = new File("./POO.zip");
            tanque.createNewFile();
            FileOutputStream canoOut = new FileOutputStream(tanque);
            GZIPOutputStream compactador = new GZIPOutputStream(canoOut);
            ObjectOutputStream serializador = new ObjectOutputStream(compactador);

            atualizarEstado();

            serializador.writeObject(this.e);
            serializador.writeObject(this.estado);
            serializador.flush();
            serializador.close();
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregaEstado() {
        try {
            File tanque = new File("./POO.zip");
            FileInputStream canoOut = new FileInputStream(tanque);
            GZIPInputStream compactador = new GZIPInputStream(canoOut);
            ObjectInputStream serializador = new ObjectInputStream(compactador);
            this.e = (Fase) serializador.readObject();
            Estado novoEstado = (Estado) serializador.readObject();

            this.carregarEstado(novoEstado);

            this.lLolo = (Lolo) this.e.get(0);
            serializador.close();
        } catch (Exception ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void leElementoDeArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int retorno = fileChooser.showOpenDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File aqruivo = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                FileInputStream inputStream = new FileInputStream(aqruivo);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Elemento elemento = (Elemento) objectInputStream.readObject();
                addPersonagem(elemento);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void reiniciaFase() {
        this.lLolo.setVida(this.lLolo.getVida() - 1);
        int vidasAtual = this.lLolo.getVida();
        if(vidasAtual <= 0 ) {
            gameOver = true;
            return;
        }
        if(indiceFaseAtual == 0) {
            this.fases[indiceFaseAtual] = new Fase1();
            reiniciaFase(vidasAtual);
        }
        if(indiceFaseAtual == 1) {
            this.fases[indiceFaseAtual] = new Fase2();
            reiniciaFase(vidasAtual);
        }
        if(indiceFaseAtual == 2) {
            this.fases[indiceFaseAtual] = new Fase3();
            reiniciaFase(vidasAtual);
        }
        return;
    }

    private void reiniciaFase(int vidasAtual) {
        this.e = this.fases[indiceFaseAtual];
        
        Lolo novo = (Lolo) this.e.get(0);
        novo.setMunicoes(0);
        this.lLolo = novo;
        this.e.set(0, novo);
    }

    public void mousePressed(MouseEvent e) {
        /*
         * Clique do mouse desligado
         * int x = e.getX();
         * int y = e.getY();
         * 
         * this.setTitle("X: "+ x + ", Y: " + y +
         * " -> Cell: " + (y/Consts.CELL_SIDE) + ", " + (x/Consts.CELL_SIDE));
         * 
         * this.lLolo.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
         */
        repaint();
    }

    public void proximaFase() {
        Lolo loloAntigo = (Lolo) this.e.get(0);

        this.e = fases[++indiceFaseAtual];

        Lolo loloNovo = (Lolo) this.e.get(0);

        loloAntigo.setPosicao(loloNovo.getLinha(), loloNovo.getColuna());

        this.e.set(0,loloAntigo);
        this.lLolo = (Lolo) this.e.get(0);
        this.lLolo.setMunicoes(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2015-1 - Adventures of lolo");
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 561, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents
     // Variables declaration - do not modify//GEN-BEGIN:variables
     // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author's Cristian Parrado y Laura Parrado
 */
public class Juego extends javax.swing.JFrame {

    public Juego() {
        initComponents();

    }
    ListaCircular baraja = CrearLista(); //Lista de la baraja
    ListaDePila pila = new ListaDePila(); //Lista de las cartas que se botan en el centro
    ListaCircular maso1 = new ListaCircular(); //lista del maso del jugador 1
    ListaCircular maso2 = new ListaCircular(); //lista del maso del jugador 2
    //Se crea un metodo para llenar la baraja con todas las cartas
    boolean floaguno=false; //bandera para saber si el judador toco el boton uno
    public ListaCircular CrearLista() {
        String Colores[] = {"amarillo", "rojo", "azul", "verde"};
        ListaCircular baraja = new ListaCircular();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 13; i++) {
                if (i == 0) {
                    baraja.AgregarAlFinal(i, Colores[j]);
                } else {
                    baraja.AgregarAlFinal(i, Colores[j]);
                    baraja.AgregarAlFinal(i, Colores[j]);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            baraja.AgregarAlFinal(14, "mas");
            baraja.AgregarAlFinal(14, "Comodin");
        }
        baraja.listar();
        return baraja;
    }

    //Metodo para agregar cartas al maso del jugador uno con sus respectivo evento
    public JLabel Robar(JPanel maso, ListaCircular baraja, ListaCircular maso1) {
        JLabel carta = new JLabel(); //se crea un label
        carta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Nodo aux = baraja.Aleatorio();  //se asigna auxiliar a un nodo aleatorio de la baraja
        carta.setName(aux.getColor() + "-" + aux.getNumero()); //se cambia el nombre con el color y el numero separados por un guion
        //se cambia el icono de la carta 
        carta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/" + aux.getColor() + aux.getNumero() + ".jpg")));
        maso1.AgregarAlFinal(aux.getNumero(), aux.getColor()); //se agrega a la lista del maso 1
        baraja.Eliminar(aux.getNumero(), aux.getColor()); //Se elimina de la baraja
        //separamos por partes el numero y el color de la carta y la carta actual para poder comparar despues
        String parte[] = carta.getName().split("-");

        //Se le asgina el evento a cada carta
        carta.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                String[] partecarta = CartaActual.getName().split("-");
                //si la carta es un mas 4 o un comodin
                if (carta.getName().equals("mas-14") || carta.getName().equals("Comodin-14")) {
                    CartaActual.setName("null"); //se nombra null para que no pueda tirar mientras escoje el color
                    CambioColor.setVisible(true); //se llama el panel para setear el color de la carta actual
                    //si es igual a mas 4
                    if (carta.getName().equals("mas-14")) {
                        //se agregan 4 cartas al maso 2
                        for (int j = 0; j < 4; j++) {
                            Nodo aux = baraja.Aleatorio();
                            JLabel carta5 = new JLabel();
                            carta5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/respaldouno.png")));
                            carta5.setName(aux.getColor() + "-" + aux.getNumero());
                            maso2.AgregarAlFinal(aux.getNumero(), aux.getColor());
                            Maso2.add(carta5);
                            baraja.Eliminar(aux.getNumero(), aux.getColor());
                        }
                        CartaActual.setIcon(carta.getIcon());
                        pila.PUSH(Integer.parseInt(parte[1]), parte[0]);
                        carta.setVisible(false);
                        Maso1.remove(carta);
                        maso1.Eliminar(Integer.parseInt(parte[1]), parte[0]);
                    } else {
                        CartaActual.setIcon(carta.getIcon());
                        pila.PUSH(Integer.parseInt(parte[1]), parte[0]);
                        maso1.Eliminar(Integer.parseInt(parte[1]), parte[0]);
                        carta.setVisible(false);
                        Maso1.remove(carta);

                    }

                } else {
                    boolean floag = false;
                    //Si la carta es un mas 2
                    if (Integer.parseInt(partecarta[1]) == 12) {
                        for (int j = 0; j < 2; j++) {
                            Nodo aux = baraja.Aleatorio();
                            JLabel carta5 = new JLabel();
                            carta5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/respaldouno.png")));
                            carta5.setName(aux.getColor() + "-" + aux.getNumero());
                            Maso2.add(carta5);
                            maso2.AgregarAlFinal(aux.getNumero(), aux.getColor());
                            baraja.Eliminar(aux.getNumero(), aux.getColor());
                        }
                        CartaActual.setIcon(carta.getIcon());
                        CartaActual.setName(carta.getName());
                        pila.PUSH(Integer.parseInt(parte[1]), parte[0]);
                        carta.setVisible(false);
                        Maso1.remove(carta);
                        maso1.Eliminar(Integer.parseInt(parte[1]), parte[0]);
                        floag = true;
                    } else {
                        if (Integer.parseInt(partecarta[1]) == 10 || Integer.parseInt(partecarta[1]) == 11) {
                            CartaActual.setIcon(carta.getIcon());
                            CartaActual.setName(carta.getName());
                            pila.PUSH(Integer.parseInt(parte[1]), parte[0]);
                            carta.setVisible(false);
                            Maso1.remove(carta);
                            maso1.Eliminar(Integer.parseInt(parte[1]), parte[0]);
                            floag = true;
                        }
                    }
                    if ((partecarta[0].equals(parte[0]) || Integer.parseInt(partecarta[1]) == Integer.parseInt(parte[1])) && floag == false) {
                        CartaActual.setIcon(carta.getIcon());
                        CartaActual.setName(carta.getName());
                        pila.PUSH(Integer.parseInt(parte[1]), parte[0]);
                        carta.setVisible(false);
                        Maso1.remove(carta);
                        maso1.Eliminar(Integer.parseInt(parte[1]), parte[0]);
                        if (maso2.EncontrarRef(partecarta[0], Integer.parseInt(partecarta[1]))) {
                            int ayuda = maso2.validar(CartaActual.getName());
                            String nombre = Maso2.getComponent(ayuda).getName();
                            String parte2[] = nombre.split("-");
                            if (Integer.parseInt(parte2[1]) == 12) {
                                Robar(Maso1, baraja, maso1);
                                Robar(Maso1, baraja, maso1);
                            } else {
                                if (parte2[0].equals("mas")) {
                                    Robar(Maso1, baraja, maso1);
                                    Robar(Maso1, baraja, maso1);
                                    Robar(Maso1, baraja, maso1);
                                    Robar(Maso1, baraja, maso1);
                                    CartaActual.setName("rojo-17");
                                }else{
                                    if(parte2[0].equals("Comodin")){
                                     CartaActual.setName("verde-17");
                                    }
                                }
                            }
                            CartaActual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/" + parte2[0] + parte2[1] + ".jpg")));
                            CartaActual.setName(nombre);
                            Maso2.remove(ayuda);
                            maso2.Eliminar(Integer.parseInt(parte2[1]), parte2[0]);

                        } else {
                            JLabel carta2 = new JLabel();
                            Nodo aux = baraja.Aleatorio();
                            maso2.AgregarAlFinal(aux.getNumero(), aux.getColor());
                            carta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/respaldouno.png")));
                            carta2.setName(aux.getColor() + "-" + aux.getNumero());
                            Maso2.add(carta2);
                            baraja.Eliminar(aux.getNumero(), aux.getColor());
                        }
                    }
                }
                System.out.println("MASO 1: ");
                maso1.listar();
                System.out.println("MASO 2: ");
                maso2.listar();
                if (maso1.getTam() == 1) {
                    PanelUno.setVisible(true);
                }
                if (maso1.getTam() == 0 && floaguno==true) {
                    try {
                        JOptionPane.showMessageDialog(null, "El jugador 1 ha ganado");
                        Juego w = new Juego();
                        w.LlenarMasos();
                        w.setVisible(true);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (maso2.getTam() == 0) {
                        try {
                            JOptionPane.showMessageDialog(null, "El jugador 2 ha ganado");
                            Juego w = new Juego();
                            w.LlenarMasos();
                            w.setVisible(true);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        Robar(Maso1, baraja, maso1);
                        Robar(Maso1, baraja, maso1);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        Maso1.add(carta);
        return carta;
    }

    //metodo para llenar el maso del jugador 1 y 2

    public void LlenarMasos() throws InterruptedException {
        Nodo aux = baraja.Aleatorio();
        //Se llena el pimer maso
        for (int i = 0; i < 7; i++) {
            JLabel carta1 = Robar(Maso1, baraja, maso1);
        } //Se llena el segundo maso
        for (int i = 0; i < 7; i++) {
            JLabel carta2 = new JLabel();
            carta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/respaldouno.png")));
            carta2.setName(aux.getColor() + "-" + aux.getNumero());
            Maso2.add(carta2);
            maso2.AgregarAlFinal(aux.getNumero(), aux.getColor());
            baraja.Eliminar(aux.getNumero(), aux.getColor());
            aux = baraja.Aleatorio();
        }
        while (aux.getColor().equals("mas") || aux.getColor().equals("Comodin")) {
            aux = baraja.Aleatorio();
        }
        pila.PUSH(aux.getNumero(), aux.getColor());
        CartaActual.setName(aux.getColor() + "-" + aux.getNumero());
        CartaActual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/" + aux.getColor() + aux.getNumero() + ".jpg")));
        baraja.Eliminar(aux.getNumero(), aux.getColor());
        baraja.listar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        PanelUno = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        CambioColor = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jBrojo = new javax.swing.JButton();
        jBazul = new javax.swing.JButton();
        jBverde = new javax.swing.JButton();
        jBamarillo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPprimeracarta = new javax.swing.JPanel();
        CartaActual = new javax.swing.JLabel();
        Maso1 = new javax.swing.JPanel();
        jPbaraja = new javax.swing.JPanel();
        RespaldoCarta = new javax.swing.JLabel();
        Maso2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNO");
        setName("UNO"); // NOI18N
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Familia Barreto\\Documents\\para\\Estructura de datos\\Uno\\cartas\\ayuda.png")); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 60, 50));

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Familia Barreto\\Documents\\para\\Estructura de datos\\salir.png")); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 50, 50));

        PanelUno.setBackground(new java.awt.Color(255, 255, 51));

        jButton3.setBackground(new java.awt.Color(255, 255, 0));
        jButton3.setFont(new java.awt.Font("Ebrima", 1, 80)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 0, 0));
        jButton3.setText("UNO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelUnoLayout = new javax.swing.GroupLayout(PanelUno);
        PanelUno.setLayout(PanelUnoLayout);
        PanelUnoLayout.setHorizontalGroup(
            PanelUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );
        PanelUnoLayout.setVerticalGroup(
            PanelUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(PanelUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 370, 260, 110));
        PanelUno.setVisible(false);

        CambioColor.setBackground(new java.awt.Color(255, 255, 255));
        CambioColor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setMaximumSize(new java.awt.Dimension(200, 280));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 280));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 280));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBrojo.setBackground(new java.awt.Color(255, 0, 0));
        jBrojo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBrojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBrojoActionPerformed(evt);
            }
        });
        jPanel2.add(jBrojo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 130));

        jBazul.setBackground(new java.awt.Color(0, 51, 255));
        jBazul.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBazul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBazulActionPerformed(evt);
            }
        });
        jPanel2.add(jBazul, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 80, 130));

        jBverde.setBackground(new java.awt.Color(0, 153, 51));
        jBverde.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBverde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBverdeActionPerformed(evt);
            }
        });
        jPanel2.add(jBverde, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 80, 130));

        jBamarillo.setBackground(new java.awt.Color(255, 255, 0));
        jBamarillo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBamarillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBamarilloActionPerformed(evt);
            }
        });
        jPanel2.add(jBamarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 80, 130));

        CambioColor.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 180, 280));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/jueocmbio.png"))); // NOI18N
        CambioColor.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 90, 300, 240));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel3.setText("ELIJE TU COLOR");
        CambioColor.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 320, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/FONDOCOLOR.jpg"))); // NOI18N
        CambioColor.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 400));

        jPanel1.add(CambioColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, -1, -1));
        CambioColor.setVisible(false);

        jPprimeracarta.setBackground(new java.awt.Color(255, 255, 255));
        jPprimeracarta.setLayout(null);
        jPprimeracarta.add(CartaActual);
        CartaActual.setBounds(0, 0, 140, 170);

        jPanel1.add(jPprimeracarta, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 140, 170));

        Maso1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Maso1.setOpaque(false);
        Maso1.setLayout(new java.awt.GridLayout(1, 0));
        jPanel1.add(Maso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 730, 200));

        jPbaraja.setOpaque(false);
        jPbaraja.setLayout(null);

        RespaldoCarta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/respaldouno.png"))); // NOI18N
        RespaldoCarta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RespaldoCarta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RespaldoCartaMouseClicked(evt);
            }
        });
        jPbaraja.add(RespaldoCarta);
        RespaldoCarta.setBounds(0, 0, 130, 200);

        jPanel1.add(jPbaraja, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 230, 130, 200));

        Maso2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Maso2.setOpaque(false);
        Maso2.setLayout(new java.awt.GridLayout(1, 0));
        jPanel1.add(Maso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 730, 200));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/fondo2.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/fondojuego.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RespaldoCartaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RespaldoCartaMouseClicked
        // TODO add your handling code here:
        floaguno=false;
        JLabel carta1 = new JLabel();
        carta1 = Robar(Maso1, baraja, maso1);
        Maso1.add(carta1);
        Maso1.setVisible(false);
        Maso1.setVisible(true);
        String partecarta[] = CartaActual.getName().split("-");
        if (!maso2.EncontrarRef(partecarta[0], Integer.parseInt(partecarta[1]))) {
            JLabel carta2 = new JLabel();
            Nodo aux = baraja.Aleatorio();
            maso2.AgregarAlFinal(aux.getNumero(), aux.getColor());
            carta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/respaldouno.png")));
            carta2.setName(aux.getColor() + "-" + aux.getNumero());
            Maso2.add(carta2);
            baraja.Eliminar(aux.getNumero(), aux.getColor());
        } else {
            // CartaActual.setIcon(carta2.geticon);
            int ayuda = maso2.validar(CartaActual.getName());
            String nombre = Maso2.getComponent(ayuda).getName();
            String parte2[] = nombre.split("-");
            CartaActual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartas/" + parte2[0] + parte2[1] + ".jpg")));
            CartaActual.setName(nombre);
            System.out.println(ayuda);
            Maso2.remove(ayuda);
            maso2.Eliminar(Integer.parseInt(parte2[1]), parte2[0]);

        }
    }//GEN-LAST:event_RespaldoCartaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //AQUI SE COLOCA A QUE LO MANDE AL ARCHIVO PDF
        String url = "C:\\Users\\Wanana\\Documents\\Uno\\cartas\\reglas.pdf";
        //gestiona un conjunto de atributos de proceso
        ProcessBuilder p = new ProcessBuilder();
        //solicitad una operacion y lo encapsula como un objeto
        p.command("cmd.exe", "/c", url);
        try {
            //crea una nueva subproceso
            p.start();
        } catch (IOException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBrojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBrojoActionPerformed
        CartaActual.setName("rojo-16");
        CambioColor.setVisible(false);
    }//GEN-LAST:event_jBrojoActionPerformed

    private void jBazulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBazulActionPerformed
        CartaActual.setName("azul-16");
        CambioColor.setVisible(false);
    }//GEN-LAST:event_jBazulActionPerformed

    private void jBverdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBverdeActionPerformed
        CartaActual.setName("verde-16");
        CambioColor.setVisible(false);
    }//GEN-LAST:event_jBverdeActionPerformed

    private void jBamarilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBamarilloActionPerformed
        CartaActual.setName("amarillo-16");
        CambioColor.setVisible(false);
    }//GEN-LAST:event_jBamarilloActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        floaguno=true;
        PanelUno.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Juego().setVisible(false);
                    Juego w = new Juego();
                    w.setVisible(true);
                    w.LlenarMasos();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel CambioColor;
    public javax.swing.JLabel CartaActual;
    public javax.swing.JPanel Maso1;
    public javax.swing.JPanel Maso2;
    private javax.swing.JPanel PanelUno;
    private javax.swing.JLabel RespaldoCarta;
    private javax.swing.JButton jBamarillo;
    private javax.swing.JButton jBazul;
    public javax.swing.JButton jBrojo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jBverde;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPbaraja;
    private javax.swing.JPanel jPprimeracarta;
    // End of variables declaration//GEN-END:variables
}

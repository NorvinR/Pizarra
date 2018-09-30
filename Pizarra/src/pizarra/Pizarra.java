package pizarra;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Pizarra extends javax.swing.JFrame {

    /*Constructor de la clase pizarra*/
    public Pizarra() {
        
        initComponents();
        
        /*Trama sera el objeto que se serializara para enviarlo por la red*/
        trama = new Trama();
        g = getGraphics();
        //v = new Vector();
        c = Color.white;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPizarra = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbLimpiarPizarra = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaMensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pizarra distribuida");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jpPizarra.setBackground(new java.awt.Color(58, 104, 122));
        jpPizarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpPizarraMouseDragged(evt);
            }
        });
        jpPizarra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpPizarraMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jpPizarraMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jpPizarraLayout = new javax.swing.GroupLayout(jpPizarra);
        jpPizarra.setLayout(jpPizarraLayout);
        jpPizarraLayout.setHorizontalGroup(
            jpPizarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpPizarraLayout.setVerticalGroup(
            jpPizarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Pizarra");

        jbLimpiarPizarra.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jbLimpiarPizarra.setText("Limpiar pizarra");
        jbLimpiarPizarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimpiarPizarraActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Comentarios:");

        jtaMensajes.setColumns(20);
        jtaMensajes.setRows(5);
        jtaMensajes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtaMensajesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtaMensajes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel1)
                .addContainerGap(297, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbLimpiarPizarra))
                    .addComponent(jpPizarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbLimpiarPizarra)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbLimpiarPizarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarPizarraActionPerformed
        /*Evento ocurre cuando se preciona el boton limpiar pizarra*/

        /*Metodo para vaciar los datos poligonos del vector*/
        trama.vaciarVector();
        
        /*Al atributo s se le asigna el string que haya en el TextArea*/
        trama.setString(jtaMensajes.getText());
        try {
            /*Serializando el objeto para que pueda ser enviado por la red*/
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bs);
            os.writeObject(trama);
            byte[] m = bs.toByteArray();/*devuelve un byte []*/
            
            /*Preparando el paquete para ser enviado*/
            DatagramPacket messageOut = new DatagramPacket(m, m.length, group, puerto);
            /*Envio del paquete al grupo multicas*/
            s.send(messageOut);

        } catch (Exception exc) {
            System.err.println("Error jbLimpiarPizarraActionPerformed:" + exc.getMessage());
        }
    }//GEN-LAST:event_jbLimpiarPizarraActionPerformed

    private void jpPizarraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpPizarraMouseReleased
        /*Evento ocurre cuando se suelta el click del raton*/
        
        /*Se agrega al vector un dato de tipo poligono*/
        trama.set_poygon_to_vector(p);
        
        /*Siempre al enviar una trama se actualizan los datos de la trama a enviar*/
        trama.setString(jtaMensajes.getText());
        try {
            /*Serializando el objeto para que pueda ser enviado por la red*/
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bs);
            os.writeObject(trama);
            byte[] m = bs.toByteArray();/*devuelve un byte []*/
            
            /*Preparando el paquete para ser enviado*/
            DatagramPacket messageOut = new DatagramPacket(m, m.length, group, puerto);
            s.send(messageOut);
        } catch (Exception exc) {
            System.err.println("Error jpPizarraMouseReleased:" + exc.getMessage());
        }
    }//GEN-LAST:event_jpPizarraMouseReleased

    private void jpPizarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpPizarraMousePressed
        /*Evento ocurre cuando clickeamos con el raton*/
        
        /*Se optendran las coordenadas x,y del lugar donde se hizo click*/
        p = new Polygon();
        p.addPoint(evt.getX(), evt.getY());
    }//GEN-LAST:event_jpPizarraMousePressed

    private void jpPizarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpPizarraMouseDragged
        /*Evento ocurre cuando estamos arrastrando el puntoro clickeado sobre el jPanel*/
        
        /*Cada vez que avanza se optienen las coordenadas y se pintan en la pizarra*/
        p.addPoint(evt.getX(), evt.getY());
        g.setColor(c);
        jpPizarra.getGraphics().drawPolyline(p.xpoints, p.ypoints, p.npoints);
    }//GEN-LAST:event_jpPizarraMouseDragged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        /*Evento ocurre cuando la ventana se abrio por primera vez*/
        
        /*En las siguientes lineas lo que se hace es unir al usuario a un grupo multicast*/
        try {
            group = InetAddress.getByName("228.4.5.6");
            s = new MulticastSocket(puerto);
            s.joinGroup(group);/*Unimos el equipo al grupo multicast*/
        } catch (Exception exc) {
            System.err.println(" formWindowOpened:" + exc.getMessage());
        }
        ActualizarPizarra actualizar = new ActualizarPizarra();
        this.jtaMensajes.requestFocus();

    }//GEN-LAST:event_formWindowOpened

    private void jtaMensajesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaMensajesKeyReleased

        try {
            /*La siguiente condicion se utilizo para evitar que sucediera un error de IO cuando
            la trama llegava al hilo y al poligono no se le habia asignado memoria, esto sucedia
            cuando se escribia en la pizarra pero nunca se habia rayado*/
            if (p == null || p.npoints == 0) {
                p = new Polygon();
                trama.set_poygon_to_vector(p);
            }
            /*Serializando el objeto para que pueda ser enviado por la red*/
            trama.setString(jtaMensajes.getText());
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bs);
            os.writeObject(trama);
            byte[] m = bs.toByteArray();/*devuelve un byte []*/
            
            /*Preparando el paquete para ser enviado*/
            DatagramPacket messageOut = new DatagramPacket(m, m.length, group, puerto);
            s.send(messageOut);
        } catch (Exception exc) {
            System.err.println("Error jtaMensajesKeyReleased:" + exc.getMessage());
        }
    }//GEN-LAST:event_jtaMensajesKeyReleased

    /*El efecto que se vio en este metodo fue mantener la pizarra pintada aunque se minimizara,
    achicara, agrandara etc.*/
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Polygon q;
        if (trama.getVector() != null) {
            for (int i = 0; i < trama.getVector().size(); i++) {
                q = (Polygon) trama.getVector().elementAt(i);
                jpPizarra.getGraphics().drawPolyline(q.xpoints, q.ypoints, q.npoints);
            }
        }
    }

    public static void main(String args[]) {
        Pizarra pizarra = new Pizarra();
        pizarra.setVisible(true);

    }

    public class ActualizarPizarra extends Thread {

        public ActualizarPizarra() {
            this.start();
        }

        public void run() {
            try {
                /*El hilo se ejecutara siempre para estar capturando los cambios hechos por los usuarios*/
                while (true) {
                    Trama tr = new Trama();
                    /*Se asigno una cantidad 2^16 porque con otras cantidades lanzaba un error de
                    IO porque la cantidad de memoria era menor al objeto recibido*/
                    
                    /*Preparando el objeto para la descerializacion*/
                    byte[] buffer = new byte[65536];
                    DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                    s.receive(messageIn);
                    ByteArrayInputStream is = new ByteArrayInputStream(buffer);
                    ObjectInputStream ois = new ObjectInputStream(is);
                    tr = (Trama) ois.readObject();

                    /*Se pintara la pizarra solo si existe un dato que pintar contenido en el vector*/
                    if (tr.getVector().size() != 0) {
                        g.setColor(c);
                        Polygon q;

                        for (int i = 0; i < tr.getVector().size(); i++) {
                            q = (Polygon) tr.getVector().elementAt(i);
                            jpPizarra.getGraphics().drawPolyline(q.xpoints, q.ypoints, q.npoints);
                        }
                    /*Si lo anterior no se cumplio significa que el usuario selecciono limpiar pizarra*/
                    } else {
                        jpPizarra.repaint();//Metodo para re-pintar la pizarra
                        trama.vaciarVector();/*Se vacia el vector nuevamente porque daba efectos no
                        deseados si no se hacia*/
                        p = new Polygon();/*Se asigna memoria al poligono para sobreescribir datos
                        anteriores ya que que quedaban datos en el y provocaba diferentes dibujos en
                        las distintas pizarras*/
                    }
                    
                    /*Si el dato string de trama tiene texto se actualiza jtaMensajes*/
                    if (tr.getString() != null) {
                        jtaMensajes.setText(tr.getString());
                        int ultimaLinea = jtaMensajes.getDocument().getLength();
                        jtaMensajes.setCaretPosition(ultimaLinea);

                    }
                }
            } catch (Exception exc) {
                System.err.println("Error run: " + exc.getMessage());
            }
        }
    }

    /*Datos miembros*/
    private Graphics g = null;
    private Polygon p = null;
    private Color c = null;

    private MulticastSocket s = null;
    private InetAddress group = null;
    private int puerto = 60500;

    private Trama trama = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbLimpiarPizarra;
    private javax.swing.JPanel jpPizarra;
    private javax.swing.JTextArea jtaMensajes;
    // End of variables declaration//GEN-END:variables
}

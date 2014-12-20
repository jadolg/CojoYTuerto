/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cojoytuerto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author akiel
 */
public class CojoYTuertoFrame extends javax.swing.JFrame {

    /**
     * Creates new form CojoYTuertoFrame
     */
    public static Twitter twitter;
    public static ConfigurationBuilder configBuilder = new ConfigurationBuilder();
    String Token = new String(); //Los almaceno en un string, ya que pueden variar según la cuenta
    String TokenSecret = new String();
    File archivo = null;
    FileReader fileR = null;
    BufferedReader lecturaTeclado = null;
    ArrayList<Status> mensajes = new ArrayList<>();
    int pageIndex = 1;

    public CojoYTuertoFrame() {
        initComponents();
        jTable1.getTableHeader().setReorderingAllowed(false);
        try {
            TAuth auth = new TAuth();
        } catch (IOException | TwitterException ex) {
            Logger.getLogger(CojoYTuertoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
// Apertura del fichero y creacion de BufferedReader
            archivo = new File("auth_file.txt");
            fileR = new FileReader(archivo);
            lecturaTeclado = new BufferedReader(fileR);
// Lectura del fichero
            String linea = new String();
            int n = 1;
            while ((linea = lecturaTeclado.readLine()) != null) {
                if (n == 1) { //La primera línea es el Access Token
                    System.out.println(linea);
                    Token = linea;
                } else if (n == 2) { //La segunda línea es el Access Token Secret
                    System.out.println(linea);
                    TokenSecret = linea;
                }
                n++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
// En el finally cerramos el fichero, para asegurarnos
// que se cierra tanto si todo va bien como si salta
// una excepción.
            try {
                if (null != fileR) {
                    fileR.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        configBuilder = new ConfigurationBuilder();
        configBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("wCWSOW8xHlxfQq24kSxXuXNm9")
                .setOAuthConsumerSecret("5B1R4bxZv7TcO7Vmq3NvhM3Bo3YcO0qCIJP2vDD9HnOaPL63YD");
        configBuilder.setHttpProxyHost("127.0.0.1");
        configBuilder.setUseSSL(true);
        configBuilder.setHttpProxyPort(8080);

        configBuilder.setOAuthAccessToken(Token)
                .setOAuthAccessTokenSecret(TokenSecret);
        twitter = new TwitterFactory(configBuilder.build()).getInstance();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cojo&Tuerto by Akiel");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton1.setText("Tweet!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "Tweet", "FV", "RT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(100);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(300);
            jTable1.getColumnModel().getColumn(2).setMinWidth(30);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(30);
            jTable1.getColumnModel().getColumn(3).setMinWidth(30);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(30);
        }

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Older Tweets...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        try {
            Status tweetEscrito;
            tweetEscrito = twitter.updateStatus(jTextField1.getText());
        } catch (Exception ex) {
        }
        jTextField1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Paging pagina = new Paging(1,60);
//            pagina.setCount(60);
            ResponseList listado;

            listado = twitter.getHomeTimeline(pagina);

            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();

            for (int i = listado.size() - 1; i >= 0; i--) {
                if (!mensajes.contains((Status) listado.get(i))) {
                    String RT = "";
                    String FV = "";
                    if (((Status) listado.get(i)).isRetweetedByMe()){
                        RT = "RT";
                    }
                    if (((Status) listado.get(i)).isFavorited()){
                        FV = "FV";
                    }
                    
                    
                    Object[] row = {((Status) listado.get(i)).getUser().getName()+" (@"+((Status) listado.get(i)).getUser().getScreenName()+")", ((Status) listado.get(i)).getText(), FV, RT};
                    tm.insertRow(0, row);
                    mensajes.add(0, (Status) listado.get(i));
                }

//                System.out.println(((Status) listado.get(i)).getUser().getName() + ": " + ((Status) listado.get(i)).getText());
            }
        } catch (TwitterException ex) {
            Logger.getLogger(CojoYTuertoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if (jTextField1.getText().length() > 140) {
            jTextField1.setText(jTextField1.getText().substring(0, 139));
        }

    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            Status ms = mensajes.get(jTable1.getSelectedRow());
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();

            if (jTable1.getColumnName(jTable1.getSelectedColumn()).equals("FV")) {
                //FV
                
                System.out.println("FV: " + ms.getText());
                try {
                    twitter.createFavorite(ms.getId());
                } catch (TwitterException ex) {
                    Logger.getLogger(CojoYTuertoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                tm.setValueAt("FV", jTable1.getSelectedRow(), jTable1.getSelectedColumn());

            } else if (jTable1.getColumnName(jTable1.getSelectedColumn()).equals("RT")) {
                //RT
                if (!ms.isRetweetedByMe()) {
                    try {
                        twitter.retweetStatus(ms.getId());
                        System.out.println("RT: " + mensajes.get(jTable1.getSelectedRow()).getText());
                    } catch (TwitterException ex) {
                        Logger.getLogger(CojoYTuertoFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                tm.setValueAt("RT", jTable1.getSelectedRow(), jTable1.getSelectedColumn());
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            Paging pagina = new Paging(++pageIndex, 60);
//            pagina.setCount(60);
            ResponseList listado;

            listado = twitter.getHomeTimeline(pagina);

            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();

            for (int i = 0; i < listado.size(); i++) {
                if (!mensajes.contains((Status) listado.get(i))) {
                    String RT = "";
                    String FV = "";
                    if (((Status) listado.get(i)).isRetweetedByMe()){
                        RT = "RT";
                    }
                    if (((Status) listado.get(i)).isFavorited()){
                        FV = "FV";
                    }
                                       
                    Object[] row = {((Status) listado.get(i)).getUser().getName()+" (@"+((Status) listado.get(i)).getUser().getScreenName()+")", ((Status) listado.get(i)).getText(), FV, RT};
                    tm.addRow(row);
                    mensajes.add((Status) listado.get(i));
                }

//                System.out.println(((Status) listado.get(i)).getUser().getName() + ": " + ((Status) listado.get(i)).getText());
            }
        } catch (TwitterException ex) {
            Logger.getLogger(CojoYTuertoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            java.util.logging.Logger.getLogger(CojoYTuertoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CojoYTuertoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CojoYTuertoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CojoYTuertoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CojoYTuertoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
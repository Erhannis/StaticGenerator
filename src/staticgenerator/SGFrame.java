/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package staticgenerator;

import java.awt.image.BufferedImage;
import java.util.Random;
import mathnstuff.components.ImagePanel;

/**
 *
 * @author MEwer
 */
public class SGFrame extends javax.swing.JFrame {

    public ImagePanel ipImage;
    
    /**
     * Creates new form SGFrame
     */
    public SGFrame() {
        initComponents();
        ipImage = new ImagePanel();
        jSplitPane1.setRightComponent(ipImage);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        cbPalette = new javax.swing.JComboBox();
        btnGenerate = new javax.swing.JButton();
        spinScale = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(200);

        cbPalette.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BW", "Greyscale", "Color", "Sampled" }));

        btnGenerate.setText("Generate");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        spinScale.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        jLabel1.setText("Scale");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(cbPalette, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnGenerate)
                    .add(spinScale))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(cbPalette, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(spinScale, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 264, Short.MAX_VALUE)
                .add(btnGenerate)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static final int PALETTE_BW             = 0;
    private static final int PALETTE_GREYSCALE      = 1;
    private static final int PALETTE_COLOR          = 2;
    private static final int PALETTE_SAMPLED        = 3;
    
    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        int palette = PALETTE_BW;
        if ("BW".equals(cbPalette.getSelectedItem())) {
            palette = PALETTE_BW;
        } else if ("Greyscale".equals(cbPalette.getSelectedItem())) {
            palette = PALETTE_GREYSCALE;
        } else if ("Color".equals(cbPalette.getSelectedItem())) {
            palette = PALETTE_COLOR;
        } else if ("Sampled".equals(cbPalette.getSelectedItem())) {
            palette = PALETTE_SAMPLED;
            //TODO Do
        }
        int width = ipImage.getWidth();
        int height = ipImage.getHeight();
        int scale = (Integer)spinScale.getValue();
        
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y + scale <= height; y += scale) {
            for (int x = 0; x + scale <= width; x += scale) {
                int color = getColor(palette);
                for (int y0 = 0; y0 < scale; y0++) {
                    for (int x0 = 0; x0 < scale; x0++) {
                        bi.setRGB(x + x0, y + y0, color);
                    }
                }
            }
        }
        
        ipImage.setImage(bi);
    }//GEN-LAST:event_btnGenerateActionPerformed

    private Random r = new Random();
    
    private int getColor(int palette) {
        switch (palette) {
            case PALETTE_BW:
                return 0xFF000000 + (0x00FFFFFF * r.nextInt(2));
            case PALETTE_GREYSCALE:
                return 0xFF000000 + (0x00010101 * r.nextInt(0x100));
            case PALETTE_COLOR:
                return 0xFF000000 + (0x00010000 * r.nextInt(0x100))
                                  + (0x00000100 * r.nextInt(0x100))
                                  + (0x00000001 * r.nextInt(0x100));
            case PALETTE_SAMPLED:
                //TODO Do
            default:
                return 0xFF000000;
        }
    }
    
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
            java.util.logging.Logger.getLogger(SGFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SGFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SGFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SGFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SGFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerate;
    private javax.swing.JComboBox cbPalette;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSpinner spinScale;
    // End of variables declaration//GEN-END:variables
}

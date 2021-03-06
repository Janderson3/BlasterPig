/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BPigDrawingPane.java
 *
 * Created on Mar 10, 2010, 12:50:43 AM
 */

package blasterpig;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 *
 * @author blackMamba
 */
public class BPigDrawingPane extends javax.swing.JPanel {

    /** Creates new form BPigDrawingPane */
    private BPigController huston;
    private boolean hasController;
    private boolean clicked;
    private Point oldPoint;

    public BPigDrawingPane() {
        initComponents();
        hasController = false;
        clicked = false;
        oldPoint = new Point(0,0);
    }

    public void setController(BPigController huston)
    {
        this.huston = huston;
        hasController = true;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        int height = getHeight();
        int width = getWidth();
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        BufferedImage drawnStrand;

        g2.setColor(this.getBackground());
        g2.drawRect(0, 0, width, height);
        
        if(hasController){
            drawnStrand = huston.drawFold(getWidth(), getHeight());
            if(drawnStrand != null){
                g2.drawImage(drawnStrand, null, 1, 1);
            }
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        clicked = true;
        oldPoint.x = evt.getX();
        oldPoint.y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        clicked = false;
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(clicked && hasController){
            huston.changeOffset(oldPoint.x - evt.getX(),  oldPoint.y - evt.getY());
            oldPoint.x = evt.getX();
            oldPoint.y = evt.getY();
            repaint();
        }
    }//GEN-LAST:event_formMouseDragged

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        char key = evt.getKeyChar();
        huston.keyStruck(key);
        System.out.println("BOOM");
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}

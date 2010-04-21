/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * BlasterPigFrame.java
 *
 * Created on Feb 14, 2010, 12:32:26 PM
 */

package blasterpig;


import javax.swing.JFileChooser;
/**
 *
 * @author blackMamba
 */
public class BlasterPigFrame extends javax.swing.JFrame {

    private BPigController huston;
    /** Creates new form BlasterPigFrame */
    public BlasterPigFrame() {
        huston = new BPigController(this);

        initComponents();

        strandList.setModel(huston.getStrandListModel());
        drawingPane.setController(huston);
        comparativeFoldingMenuItem.setSelected(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel = new javax.swing.JPanel();
        viewControll = new javax.swing.JPanel();
        ClusterPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        removeStrands = new javax.swing.JButton();
        moveStrandUp = new javax.swing.JButton();
        moveStrandDown = new javax.swing.JButton();
        strandPane = new javax.swing.JScrollPane();
        strandList = new javax.swing.JList();
        viewPanel = new javax.swing.JPanel();
        drawingPane = new blasterpig.BPigDrawingPane();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        addStrandMenuItem = new javax.swing.JMenuItem();
        clearStrandsMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        prefixSortMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        comparativeFoldingMenuItem = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout viewControllLayout = new javax.swing.GroupLayout(viewControll);
        viewControll.setLayout(viewControllLayout);
        viewControllLayout.setHorizontalGroup(
            viewControllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );
        viewControllLayout.setVerticalGroup(
            viewControllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
        );

        removeStrands.setText("Remove");
        removeStrands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeStrandsActionPerformed(evt);
            }
        });

        moveStrandUp.setText("Up");
        moveStrandUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveStrandUpActionPerformed(evt);
            }
        });

        moveStrandDown.setText("Down");
        moveStrandDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveStrandDownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(removeStrands)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(moveStrandDown)
                .addGap(18, 18, 18)
                .addComponent(moveStrandUp)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(removeStrands)
                .addComponent(moveStrandDown)
                .addComponent(moveStrandUp))
        );

        strandList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        strandList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                strandListValueChanged(evt);
            }
        });
        strandPane.setViewportView(strandList);

        javax.swing.GroupLayout ClusterPanelLayout = new javax.swing.GroupLayout(ClusterPanel);
        ClusterPanel.setLayout(ClusterPanelLayout);
        ClusterPanelLayout.setHorizontalGroup(
            ClusterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClusterPanelLayout.createSequentialGroup()
                .addGroup(ClusterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(strandPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ClusterPanelLayout.setVerticalGroup(
            ClusterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClusterPanelLayout.createSequentialGroup()
                .addComponent(strandPane, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ClusterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addComponent(viewControll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addComponent(ClusterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewControll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        drawingPane.setPreferredSize(new java.awt.Dimension(700, 700));

        javax.swing.GroupLayout drawingPaneLayout = new javax.swing.GroupLayout(drawingPane);
        drawingPane.setLayout(drawingPaneLayout);
        drawingPaneLayout.setHorizontalGroup(
            drawingPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );
        drawingPaneLayout.setVerticalGroup(
            drawingPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(drawingPane, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawingPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );

        fileMenu.setText("File");

        addStrandMenuItem.setText("Add Strands");
        addStrandMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStrandMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(addStrandMenuItem);

        clearStrandsMenuItem.setText("Clear Strands");
        clearStrandsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearStrandsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(clearStrandsMenuItem);

        mainMenuBar.add(fileMenu);

        editMenu.setText("Edit");

        prefixSortMenuItem.setText("Prefix Sort");
        prefixSortMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefixSortMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(prefixSortMenuItem);

        mainMenuBar.add(editMenu);

        viewMenu.setText("View");

        comparativeFoldingMenuItem.setSelected(true);
        comparativeFoldingMenuItem.setText("Comparative Folding");
        comparativeFoldingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comparativeFoldingMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(comparativeFoldingMenuItem);

        mainMenuBar.add(viewMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addComponent(viewPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeStrandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeStrandsActionPerformed
       int selection = strandList.getSelectedIndex();
       if(selection != -1)
       {
        huston.removeStrand(selection);
       }
    }//GEN-LAST:event_removeStrandsActionPerformed


    private void strandListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_strandListValueChanged
        drawingPane.repaint();
    }//GEN-LAST:event_strandListValueChanged

    private void clearStrandsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearStrandsMenuItemActionPerformed
        huston.clearStrands();
    }//GEN-LAST:event_clearStrandsMenuItemActionPerformed

    private void addStrandMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStrandMenuItemActionPerformed
        addStrands();
    }//GEN-LAST:event_addStrandMenuItemActionPerformed

    private void moveStrandDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveStrandDownActionPerformed
        huston.moveStrandDown();
    }//GEN-LAST:event_moveStrandDownActionPerformed

    private void moveStrandUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveStrandUpActionPerformed
        huston.moveStrandUp();
    }//GEN-LAST:event_moveStrandUpActionPerformed

    private void comparativeFoldingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comparativeFoldingMenuItemActionPerformed
        huston.setComparativeView(comparativeFoldingMenuItem.getState());
    }//GEN-LAST:event_comparativeFoldingMenuItemActionPerformed

    private void prefixSortMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefixSortMenuItemActionPerformed
        huston.setSort(true);
    }//GEN-LAST:event_prefixSortMenuItemActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlasterPigFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ClusterPanel;
    private javax.swing.JMenuItem addStrandMenuItem;
    private javax.swing.JMenuItem clearStrandsMenuItem;
    private javax.swing.JCheckBoxMenuItem comparativeFoldingMenuItem;
    private javax.swing.JPanel controlPanel;
    private blasterpig.BPigDrawingPane drawingPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JButton moveStrandDown;
    private javax.swing.JButton moveStrandUp;
    private javax.swing.JMenuItem prefixSortMenuItem;
    private javax.swing.JButton removeStrands;
    private javax.swing.JList strandList;
    private javax.swing.JScrollPane strandPane;
    private javax.swing.JPanel viewControll;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables

    private void addStrands(){
       JFileChooser chooser = new JFileChooser();
       chooser.setMultiSelectionEnabled(true);
       chooser.showOpenDialog(null);

       huston.addStrandFromFileArray(chooser.getSelectedFiles());
    }


    public int getCurrentlySelectedStrand()
    {
        return strandList.getSelectedIndex();
    }

    public void setSelectedStrand(int i)
    {
        strandList.setSelectedIndex(i);
    }


}

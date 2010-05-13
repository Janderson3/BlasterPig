/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig;


import blasterpig.exceptions.BaseConflictException;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
/*
 *
 * @author Joshua Anderson
 */
public class BPigController {

    private BPigModel model;
    private BlasterPigFrame frameRefrence;

    public BPigController(BlasterPigFrame controlledFrame)
    {
        model = new BPigModel();
        frameRefrence = controlledFrame;
    }

    public void clearStrands()
    {
       model.clearAll();
    }

    public void removeStrand(int i)
    {
        model.removeFold(i);
    }

    public void addStrandFromFileArray(File[] fileArray)
    {
        for(int i=0; i<fileArray.length; i++)
        {
            try
            {
                model.addStrand(fileArray[i]);
            }
            catch(FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(frameRefrence, "File not Found");
            }
            catch(BaseConflictException ex)
            {
                JOptionPane.showMessageDialog(frameRefrence, "Conflict of bases");
            }/*
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(frameRefrence, "Out of order strand input or other error");
            }*/
        }
    }

    public DefaultListModel getStrandListModel()
    {
        return model.getListModel();
    }

    public BufferedImage drawFold(int imageWidth, int imageHeight)
    {
        int selection = frameRefrence.getCurrentlySelectedStrand();
        if(selection != -1){
            return model.drawStrand(selection, imageWidth, imageHeight);
        }
        else{
            return null;
        }
    }

    public void moveStrandUp()
    {
        int selection = frameRefrence.getCurrentlySelectedStrand();

        if(model.moveFoldUp(selection)){
            frameRefrence.setSelectedStrand(selection-1);
        }


    }

    public void moveStrandDown()
    {
        int selection = frameRefrence.getCurrentlySelectedStrand();
        if(model.moveFoldDown(selection)){
            frameRefrence.setSelectedStrand(selection+1);
        }

    }

    public void setComparativeView(boolean view){
        model.setComparativeView(view);
    }

    public void setSort(boolean sort){
        model.setSort(sort);
    }
   
}

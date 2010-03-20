/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig;

import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author blackMamba
 */
public class BPigModel {

    private DrawingStrand ourStrand;

    public BPigModel()
    {
        ourStrand = new DrawingStrand();
    }
    
    public void removeFold(int i)
    {
        ourStrand.removeFold(i);
    }

    public void clearAll()
    {
        ourStrand.clearFolds();
    }

    public int addStrand(File newStrand)
            throws BaseConflictException,
            FileNotFoundException
    {
        BPStrandFoldWithBases strand;
        strand = BPStrandFactory.createFromCTFile(newStrand);
        return ourStrand.addFold(strand);
    }

    public BufferedImage drawStrand(int i)
    {
        return //ourStrand.circleTester();
                ourStrand.drawFold(i);
    }

    public DefaultListModel getListModel()
    {
        return ourStrand.getListModel();
    }

    public String parenRepOfStrand(int i)
    {
        return ourStrand.parenFold(i);
    }

}

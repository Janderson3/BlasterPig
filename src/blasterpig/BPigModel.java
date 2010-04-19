/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig;

import blasterpig.strands.SortableStrand;
import blasterpig.exceptions.BaseConflictException;
import blasterpig.strands.BPStrandFactory;
import blasterpig.strands.BPStrandFoldWithBases;
import blasterpig.drawing.StrandScribbler;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author blackMamba
 */
public class BPigModel {

    private SortableStrand ourStrand;

    public BPigModel()
    {
        ourStrand = new SortableStrand();
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
        strand = BPStrandFactory.createFromFile(newStrand);
        return ourStrand.addFold(strand);
    }

    public BufferedImage drawStrand(int i)
    {
        return  StrandScribbler.drawCircleFold(ourStrand, i);
    }

    public DefaultListModel getListModel()
    {
        return ourStrand.getListModel();
    }

    public String parenRepOfStrand(int i)
    {
        return ourStrand.parenFold(i);
    }

    public boolean moveFoldUp(int i)
    {
        return ourStrand.moveFoldUp(i);
    }

    public boolean moveFoldDown(int i)
    {
        return ourStrand.moveFoldDown(i);
    }

    public void setSort(boolean sort){
       ourStrand.prefixSort(sort);
    }

}

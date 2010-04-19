/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig.strands;


import blasterpig.exceptions.BaseConflictException;
import blasterpig.strands.BPStrandFold;
import blasterpig.strands.BPStrandFoldWithBases;
import java.util.*;
import javax.swing.*;

/**
 *Represents the RNA strand which checks for consistancy of the folds it contains
 *
 * @author blackMamba
 */
public class BPStrand {
    private String name;
    protected StringBuffer strand;
    protected ArrayList<BPStrandFold> folds;
    protected DefaultListModel listModel;


    public BPStrand(String name)
    {
        this.name = name;
        listModel = new DefaultListModel();
        folds = new ArrayList<BPStrandFold>();
        strand = new StringBuffer();
    }

    public BPStrand ()
    {
        this("Root");
    }

    public int addFold(BPStrandFoldWithBases aFold) 
            throws BaseConflictException
    {
        int offset = aFold.getOffset();
        int endOfSubstrand = aFold.getLastBaseNumber();
        for(int i = offset; i <= endOfSubstrand; i++)
        {


            /* If strand length is one less then
             * append current base to string buffer
             */
            if(strand.length() == i - 1)
            {
              strand.append(aFold.getBaseAt(i));
           
            }
            /*If current strand is too short next statement will throw index out of bounds
             * exception
             * bug out for now.
             */
             /*
              * If theny don't match, there's a conflict
              */
            else if(!compareBases(strand.charAt(i - 1), aFold.getBaseAt(i)))
            {
              throw new BaseConflictException();
            }
               
        }
        /* If we make it here, all's peachy add to array and return 0*/
        folds.add(aFold);
        listModel.addElement(aFold.getName());
        return 0;
    }

    /*
     * That the fold was already added using addFold is a precondition
     * for using this function, insertFold does not check if the fold is
     * valid.
     */

    private void insertFold(BPStrandFold fold, int i)
    {
        folds.add(i, fold);
        listModel.add(i, fold.getName());
    }

    public boolean moveFoldUp(int i)
    {
        BPStrandFold fold = null;
        if(i > 0)
        {
            fold = removeFold(i);
            insertFold(fold, i-1);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean moveFoldDown(int i)
    {
        BPStrandFold fold = null;
        if(i < folds.size())
        {
            fold = removeFold(i);
            insertFold(fold, i+1);
            return true;
        }
        else{
            return false;
        }
    }

    public BPStrandFold removeFold(int i)
    {
        BPStrandFold retStrand = null;
        if(folds.isEmpty())
        {
            return retStrand;
        }
        retStrand = folds.remove(i);
        listModel.remove(i);
        if(folds.isEmpty())
        {
            strand = new StringBuffer();
        }
        return retStrand;
    }

    public void clearFolds()
    {
        strand = new StringBuffer();
        listModel.clear();
        folds.clear();
    }

    public DefaultListModel getListModel()
    {
        return listModel;
    }

    @Override 
    public String toString()
    {
        return name;
    }

    public String parenFold(int i)
    {
        return folds.get(i).parenRepresentation();
    }

    /**
     * Compares two bases to make sure they're the same strand, ignores case
     * and 'u' is the same as 't'
     *
     * @param a first base
     * @param b second base
     * @return true if a match, false otherwise
     */
    private boolean compareBases(Character a, Character b)
    {
        char newA = Character.toLowerCase(a);
        char newB = Character.toLowerCase(b);
        if(newA == 't')
        {
            newA = 'u';
        }
        if(newB == 't')
        {
            newB = 'u';
        }

        return newA == newB;

    }

    public int getLength()
    {
        return strand.length();
    }

    /**
     * Returns the pairing of the requested strand at the requested base
     *
     * @param strandDex desired strand
     * @param baseDex desired base
     * @return
     */
    public int getPairingOfStrandAtIndex(int strandDex, int baseDex)
    {
        return folds.get(strandDex).getPairingAt(baseDex);
    }


}

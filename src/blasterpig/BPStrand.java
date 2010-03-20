/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig;


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
    private DefaultListModel listModel;


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

    public void removeFold(int i)
    {
        if(folds.isEmpty())
        {
            return;
        }
        folds.remove(i);
        listModel.remove(i);
        if(folds.isEmpty())
        {
            strand = new StringBuffer();
        }
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
     * @return true if a match, false otherwised
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



}

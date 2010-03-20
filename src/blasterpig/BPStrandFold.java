/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig;

/**
 *
 * Offset by default is 1 since most things in RNA are one indexed and 0 is the
 * code for not paired
 *
 * @author blackMamba
 */

import java.util.*;

public class BPStrandFold {
    int offset; // The offset of the first base in the fold
    int[] basePairs; //Which base the queried base is paired with.

    public BPStrandFold(int off, int[] pairs)
    {
        offset = off;
        basePairs = new int[pairs.length];

        System.arraycopy(pairs, 0, basePairs, 0, pairs.length);
    }

    public BPStrandFold(int off, ArrayList<Integer> pairs)
    {
        offset = off;
        basePairs = new int[pairs.size()];
        for (int i=0; i<pairs.size(); i++)
        {
            basePairs[i] = pairs.get(i);
        }
    }

    public BPStrandFold(ArrayList<Integer> pairs)
    {
        this(1,pairs);
    }
    
    public BPStrandFold(int[] pairs)
    {
        this(1,pairs);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset()
    {
        return offset;
    }

    public int getLength()
    {
        return basePairs.length;
    }

    public int getLastBaseNumber()
    {
        return offset + basePairs.length - 1;
    }

    public void setPairingAt(int i, int paired)
    {
        basePairs[i - offset] = paired;
        basePairs[paired] = i - offset;
    }

    public int getPairingAt(int i)
    {
        return basePairs[i - offset];
    }

    public String parenRepresentation()
    {
        char [] retData = new char[getLength()];

        for(int ndx = 0; ndx < getLength(); ndx++)
        {
            if(basePairs[ndx] == 0)
            {
                retData[ndx] = '.';
            }
            else if(basePairs[ndx] > ndx) //If it's paired with someting further along the line
            {
                retData[ndx] = '(';
            }
            else
            {
                retData[ndx] = ')';
            }
        }
        String retString = new String(retData);
        return retString;
    }
}

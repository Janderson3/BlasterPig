/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig;

import java.util.*;
/**
 *
 * @author blackMamba
 */
public class BPStrandFoldWithBases extends BPStrandFold {

   private String bases;
   private String name;

    public BPStrandFoldWithBases (String name,
            int off,
            ArrayList<Integer> pairs,
            ArrayList<Character> bases)
    {
        super(off, pairs);
        char[] buffer = new char[bases.size()];
        for(int i=0; i<bases.size(); i++)
        {
            buffer[i] = bases.get(i);
        }
        this.bases = new String(buffer);

        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    public BPStrandFoldWithBases(String name,
            ArrayList<Integer> pairs,
            ArrayList<Character> bases)
    {
        this(name, 1, pairs, bases);
    }

    public char getBaseAt(int baseNumber)
    {
        return bases.charAt(baseNumber-offset);
    }

    public String stringBases()
    {
        return bases;
    }
}

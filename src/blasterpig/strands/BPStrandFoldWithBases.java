
package blasterpig.strands;

import java.util.*;
/**
 *
 * @author blackMamba
 */
public class BPStrandFoldWithBases extends BPStrandFold {

   private String bases;

    public BPStrandFoldWithBases (String name,
            int off,
            ArrayList<Integer> pairs,
            ArrayList<Character> bases)
    {
        super(off, pairs, name);
        char[] buffer = new char[bases.size()];
        for(int i=0; i<bases.size(); i++)
        {
            buffer[i] = bases.get(i);
        }
        this.bases = new String(buffer);
    }

    public BPStrandFoldWithBases(String name,
            ArrayList<Integer> pairs,
            ArrayList<Character> bases)
    {
        this(name, 1, pairs, bases);
    }

    public char getBaseAt(int baseNumber)
    {
        return bases.charAt(baseNumber-this.getOffset());
    }

    public String stringBases()
    {
        return bases;
    }
}

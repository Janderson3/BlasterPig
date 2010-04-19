package blasterpig.strands;

/**
 *
 * Offset by default is 1 since most things in RNA are one indexed and 0 is the
 * code for not paired
 *
 * @author blackMamba
 */
import java.util.*;

public class BPStrandFold implements Comparable {

    private int offset; // The offset of the first base in the fold
    private int[] basePairs; //Which base the queried base is paired with.
    private String name;
    private int order = 0;

    public BPStrandFold(int off, int[] pairs, String name) {
        offset = off;
        basePairs = new int[pairs.length];

        System.arraycopy(pairs, 0, basePairs, 0, pairs.length);
        this.name = name;
    }

    public BPStrandFold(int off, ArrayList<Integer> pairs, String name) {
        offset = off;
        basePairs = new int[pairs.size()];
        for (int i = 0; i < pairs.size(); i++) {
            basePairs[i] = pairs.get(i);
        }
        this.name = name;
    }

    public BPStrandFold(ArrayList<Integer> pairs, String name) {
        this(1, pairs, name);
    }

    public BPStrandFold(int[] pairs, String name) {
        this(1, pairs, name);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return basePairs.length;
    }

    public int getLastBaseNumber() {
        return offset + basePairs.length - 1;
    }

    public void setPairingAt(int i, int paired) {
        basePairs[i - offset] = paired;
        basePairs[paired] = i - offset;
    }

    public int getPairingAt(int i) {
        if (i > (basePairs.length + offset - 1) || i < offset) {
            return 0;
        } else {
            return basePairs[i - offset];
        }
    }

    public String parenRepresentation() {
        char[] retData = new char[getLength()];

        for (int ndx = 0; ndx < getLength(); ndx++) {
            if (basePairs[ndx] == 0) {
                retData[ndx] = '.';
            } else if (basePairs[ndx] > ndx){ //If it's paired with someting further along the line
                retData[ndx] = '(';
            } else {
                retData[ndx] = ')';
            }
        }
        String retString = new String(retData);
        return retString;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int compareTo(Object fold) {
        return order - ((BPStrandFold) fold).getOrder();
    }
}

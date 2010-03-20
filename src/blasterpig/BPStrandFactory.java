/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig;

/**
 *
 * @author blackMamba
 */

import java.io.*;
import java.util.*;

public class BPStrandFactory {


    public static BPStrandFoldWithBases createFromCTFile(File ctFile) throws FileNotFoundException
    {
        ArrayList<Character> strandList = new ArrayList<Character>();
        ArrayList<Integer> pairsList = new ArrayList<Integer>();


        FileInputStream fstream = new FileInputStream(ctFile);
        Scanner fileScanner = new Scanner(fstream);
        fileScanner.nextLine();
        while(fileScanner.hasNext()){
            fileScanner.next(); //Chuck index
            String tempString = fileScanner.next();
            strandList.add(tempString.charAt(0)); //get Base letter
            fileScanner.next();
            fileScanner.next(); //chuck next two columns
            pairsList.add(fileScanner.nextInt()); //get paired
            fileScanner.nextLine(); //the rest of the columns are irrelevant.
        }

        BPStrandFoldWithBases retFold = new BPStrandFoldWithBases(ctFile.getName(), pairsList, strandList);
        return retFold;
    }
}
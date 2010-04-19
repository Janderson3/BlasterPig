/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig.strands;

/**
 *
 * @author blackMamba
 */

import java.io.*;
import java.util.*;

public class BPStrandFactory {

    public static BPStrandFoldWithBases createFromFile(File inputFile) throws FileNotFoundException
    {
        BPStrandFoldWithBases retFold;
        String[] nameArr = inputFile.getName().split("\\.");
        String fileExtention = nameArr[nameArr.length - 1];
        fileExtention = fileExtention.toLowerCase();

        if(fileExtention.compareTo("ct") == 0)
        {
            retFold = createFromCTFile(inputFile);
        }
        else if(fileExtention.compareTo("bpseq") == 0)
        {
            retFold = createFromBPSEQFile(inputFile);
        }
        else
        {
            retFold = null;
            /*Should probably have better error checking*/

        }
        return retFold;
    }

    private static BPStrandFoldWithBases createFromBPSEQFile(File bpFile) throws FileNotFoundException
    {
        ArrayList<Character> strandList = new ArrayList<Character>();
        ArrayList<Integer> pairsList = new ArrayList<Integer>();


        FileInputStream fstream = new FileInputStream(bpFile);
        Scanner fileScanner = new Scanner(fstream);
        Scanner lineScanner;
        for(int i=0; i<4; i++){
            fileScanner.nextLine(); //Throw out first four lines
        }   
        boolean first = true;
        int offset = 1;

        while(fileScanner.hasNext()){

            lineScanner = new Scanner(fileScanner.nextLine());
            if(first)
            {
                offset = lineScanner.nextInt();
                first = false;
            }
            else
            {
                lineScanner.nextInt();
            }

            String tempString = lineScanner.next();
            strandList.add(tempString.charAt(0)); //get Base letter
            pairsList.add(lineScanner.nextInt()); //get paired
        }

        BPStrandFoldWithBases retFold = new BPStrandFoldWithBases(bpFile.getName(), offset, pairsList, strandList);
        return retFold;
    }


    private static BPStrandFoldWithBases createFromCTFile(File ctFile) throws FileNotFoundException
    {
        ArrayList<Character> strandList = new ArrayList<Character>();
        ArrayList<Integer> pairsList = new ArrayList<Integer>();


        FileInputStream fstream = new FileInputStream(ctFile);
        Scanner fileScanner = new Scanner(fstream);
        Scanner lineScanner;
        fileScanner.nextLine(); //Throw out first line
        boolean first = true;
        int offset = 1;

        while(fileScanner.hasNext()){

            lineScanner = new Scanner(fileScanner.nextLine());
            if(first)
            {
                offset = lineScanner.nextInt();
                first = false;
            }
            else
            {
                lineScanner.nextInt();
            }

            String tempString = lineScanner.next();
            strandList.add(tempString.charAt(0)); //get Base letter
            lineScanner.next();
            lineScanner.next(); //chuck next two columns
            pairsList.add(lineScanner.nextInt()); //get paired
        }

        BPStrandFoldWithBases retFold = new BPStrandFoldWithBases(ctFile.getName(), offset, pairsList, strandList);
        return retFold;
    }
}
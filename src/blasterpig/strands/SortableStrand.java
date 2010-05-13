/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig.strands;


import java.util.Collections;

/**
 *
 * @author josh
 */
public class SortableStrand extends BPStrand{

    boolean sorted;

    public SortableStrand(){
        super();
        sorted = false;
    }


    public SortableStrand(String name){
        super(name);
        sorted = false;
    }





    /**
     * Activate or disable prefix sorting.
     * @param active True if sorting is desired, false otherwise.
     * @return error code
     */
    public int prefixSort(boolean active)
    {
        if (active) {
            if (folds.size() <= 1) {
                sorted = true;
                return 0;
            }

            String prefix = getPrefix();

            for (int i = 0; i < folds.size(); i++) {
                BPStrandFold theFold = folds.get(i);
                theFold.setOrder(getNumberFromFold(theFold.getName(), prefix));
            }
            Collections.sort(folds);
            listModel.clear();
            for (int i = 0; i < folds.size(); i++) {
                listModel.addElement(folds.get(i).getName());
            }
            sorted = true;
            return 0;
        } else {
            sorted = false;
            return 0;
        }
    }

    private int getNumberFromFold(String name, String prefix)
    {
        String workName = name.substring(prefix.length());
        boolean notDone = true;
        int index=0;
        while(notDone)
        {
            char testChar = workName.charAt(index);
            if (testChar < '0' || testChar > '9') {
                notDone = false;
            }
            else if(index == workName.length())
            {
                notDone=false;
            }
            else{
                index += 1;
            }
        }
        return Integer.parseInt(workName.substring(0,index));

    }

    /**
     * Gives the prefix of the folds in the strand. Returns null if there is only one
     * Fold or all fold names are the same. The prefix stops at a the begining of the
     * unique number
     * @return
     */
    private String getPrefix()
    {
        BPStrandFold firstFold = folds.get(0);
        if(folds.size() == 1)
        {
            return null;
        }
        int i = 1;
        boolean done = false;
        while(!done)
        {
            // if the first fold and the current fold name are the same
            if(firstFold.getName().compareTo(folds.get(i).getName()) == 0)
            {
                //just keep on
                i += 1;
                //if there's no more, stop
                if(i >= folds.size())
                    return null;
            }
            else
            {
               String firstName = firstFold.getName();
               String secondName = folds.get(i).getName();
               for(int dex=0; dex < firstName.length(); dex++)
               {
                   //If the characters dont match,
                   if(firstName.charAt(dex) != secondName.charAt(dex)){
                       //We assume it's a number, and check to see if the previous one is.
                       boolean foundFirstNotNum = false;
                       int endPrefix = dex - 1;
                       while(!foundFirstNotNum)
                       {
                           if(endPrefix == 0)
                           {
                               foundFirstNotNum = true;
                           }
                           else
                           {
                               char testChar = firstName.charAt(endPrefix);
                               if(testChar < '0' || testChar > '9')
                               {
                                   foundFirstNotNum = true;
                               }
                               else
                               {
                                   endPrefix -= 1;
                               }
                           }
                       }
                       String prefix = firstName.substring(0, endPrefix + 1);
                       return prefix;
                   }

               }
               done = true;
            }

        }
        return null;
    }

    @Override
    public boolean moveFoldUp(int i){
        boolean retVal = super.moveFoldUp(i);
        sorted = false;
        return retVal;
    }

    @Override
    public boolean moveFoldDown(int i){
        boolean retVal = super.moveFoldDown(i);
        sorted = false;
        return retVal;
    }
}

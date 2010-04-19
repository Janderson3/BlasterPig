/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig.drawing;

import blasterpig.strands.BPStrand;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.*;
import java.awt.RenderingHints;

/**
 *
 * @author blackMamba
 */
public class StrandScribbler{

    final static int imageHeight = 600;
    final static int imageWidth = 600;
    final static int radius = (imageHeight - 2)/2;
    final static Color bothColor = Color.black;
    final static Color firstColor = Color.red;
    final static Color secondColor = Color.blue;


    public static BufferedImage drawCircleFold(BPStrand theStrand, int firstDex, int secondDex){
       int strandLength = theStrand.getLength();
        BufferedImage retImage =
                new BufferedImage(imageHeight, imageWidth,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = retImage.createGraphics();
        g.setColor(Color.black);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawOval(1, 1, imageWidth -2 , imageHeight -2);


        int pairingOne, pairingTwo;
        for(int i=1; i<=strandLength; i++)
        {
            pairingOne = theStrand.getPairingOfStrandAtIndex(firstDex, i);
            pairingTwo = theStrand.getPairingOfStrandAtIndex(secondDex, i);


            if(pairingOne > i && pairingOne == pairingTwo)
            {
                g.setColor(bothColor);
                drawBaseArc(i, pairingOne, strandLength, g);

            }
            else{
                if(pairingOne>i){
                    g.setColor(firstColor);
                   drawBaseArc(i, pairingOne, strandLength, g);
                }
                if(pairingTwo>i){
                    g.setColor(secondColor);
                    drawBaseArc(i, pairingTwo, strandLength, g);
                }
            }

        }

        return retImage;
    }


    public static BufferedImage drawCircleFold(BPStrand theStrand, int index)
    {
        int strandLength = theStrand.getLength();
        BufferedImage retImage =
                new BufferedImage(imageHeight, imageWidth,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = retImage.createGraphics();
        g.setColor(Color.black);
        g.drawOval(1, 1, imageWidth -2 , imageHeight -2);

        int pairing;
        for(int i=1; i<=strandLength; i++)
        {
            pairing = theStrand.getPairingOfStrandAtIndex(index, i);

            
            if(pairing > i)
            {
                drawBaseArc(i, pairing, strandLength, g);
            }
        }

        return retImage;
    }

    private static void drawBaseArc(int baseOne, int baseTwo, int strandLength, Graphics2D g) {
        double thetaFirst = (baseOne / ((double) strandLength)) * 2 * Math.PI;
        double thetaLast = (baseTwo / ((double) strandLength)) * 2 * Math.PI;


        Point firstBase =
                new Point((int) (Math.round(radius * Math.cos(thetaFirst)) + radius),
                (int) (Math.round(radius * Math.sin(thetaFirst)) + radius));
        Point secondBase =
                new Point((int) (Math.round(radius * Math.cos(thetaLast) + radius)),
                (int) (Math.round(radius * Math.sin(thetaLast) + radius)));

        //If they're straight across, just draw a line
        if ((baseTwo - baseOne) == strandLength / 2) {
            g.drawLine(firstBase.x, firstBase.y, secondBase.x, secondBase.y);
        } else {

            double scaleFactor = Math.min(Math.abs(thetaFirst - thetaLast),
                    Math.PI * 2 - Math.abs(thetaFirst - thetaLast));
            scaleFactor = 1 - (scaleFactor / Math.PI);

            double thetaMiddle = (thetaFirst + thetaLast) / (double) 2;


            /* We want the minor arc, so make sure the middle point
             * is between the shortest arc of First and Last
             */

            if (Math.abs(thetaFirst - thetaLast) > Math.PI) {
                thetaMiddle += Math.PI;
                /*           if(thetaMiddle > 2 * Math.PI)
                {
                thetaMiddle -= Math.PI*2;
                }*/
            }



            Point middlePoint =
                    new Point((int) (Math.round(scaleFactor * radius * Math.cos(thetaMiddle) + radius)),
                    (int) (Math.round(scaleFactor * radius * Math.sin(thetaMiddle) + radius)));

            CircleArc tempArc = new CircleArc(firstBase, middlePoint, secondBase);

            Arc2D.Double a = new Arc2D.Double(tempArc.getBoundingBoxXCoordinate(),
                    tempArc.getBoundingBoxYCoordinate(),
                    tempArc.getBoundingBoxWidth(),
                    tempArc.getBoundingBoxHeight(),
                    tempArc.getStartTheta(),
                    tempArc.getArcSize(),
                    Arc2D.OPEN);

            g.draw(a);
        }
    }
}

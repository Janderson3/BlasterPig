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
import java.awt.Font;
import java.awt.FontMetrics;

/**
 *
 * @author blackMamba
 */
public class StrandScribbler{

    final static int imageHeight = 700;
    final static int imageWidth = 700;
    final static double buffer = 0.10;
    final static double radius = (imageHeight*(1-buffer))/2;
    final static Color bothColor = Color.black;
    final static Color firstColor = Color.green;
    final static Color secondColor = Color.red;
    final static float degreeGap = 10;
    final static float origin = -90;
    final static int approxFreqOfNumbers = 10;
    final static int tickLength = 5;
    final static int fontdistaneFromTic = 2;

    private static void circleSetup(Graphics2D g, int length) {
        Arc2D.Double a = new Arc2D.Double((buffer/2)*imageWidth,(buffer/2)*imageHeight,
                imageWidth*(1-buffer),
                imageHeight*(1-buffer),
                origin + degreeGap/2,
                360 - degreeGap,
                Arc2D.OPEN);
        g.draw(a);

        int nearestInt = (int)(length / ((360 - degreeGap) / approxFreqOfNumbers));
        int nearestFive;
        int mod = nearestInt % 5;
        if (mod < 0){
            mod = mod+5;
        }
        if(mod >= 3){
            nearestFive = nearestInt - mod;
        }
        else{
            nearestFive = nearestInt + (5 - mod);
        }

       
        for(int i = nearestFive; i<length; i = i + nearestFive){
            double theta = radiansForBaseNumber(i, length);
            Point start = new Point((int)Math.round(radius* Math.cos(theta)) + getCenter(),
                    (int)Math.round(radius * Math.sin(theta))+getCenter());
            Point end = new Point((int)Math.round((radius + tickLength)* Math.cos(theta)) + getCenter(),
                    (int)Math.round((radius + tickLength) * Math.sin(theta)) + getCenter()) ;
            g.drawLine(start.x, start.y, end.x, end.y);
        }

        Font font = new Font("Serif", Font.PLAIN, 12);
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics();

       g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        for(int i=nearestFive; i<length; i = i + nearestFive){
            double theta = radiansForBaseNumber(i, length);
            int offset = fontMetrics.getHeight() +fontdistaneFromTic + tickLength;
            Point center = new Point((int)Math.round((radius +offset)* Math.cos(theta)) + getCenter(),
                    (int)Math.round((radius + offset) * Math.sin(theta)) + getCenter());
            String daString = String.valueOf(i);
            g.drawString(daString, center.x, center.y);
        }


      g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
    }

    public static BufferedImage drawCircleFold(BPStrand theStrand, int firstDex, int secondDex){
       int strandLength = theStrand.getLength();
        BufferedImage retImage =
                new BufferedImage(imageHeight, imageWidth,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = retImage.createGraphics();
        g.setColor(Color.black);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        

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
        g.setColor(Color.black);
        circleSetup(g, strandLength);

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

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        

        circleSetup(g, strandLength);
        
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
        double thetaFirst = radiansForBaseNumber(baseOne, strandLength);
        double thetaLast = radiansForBaseNumber(baseTwo, strandLength);


        Point firstBase =
                new Point((int) (Math.round(radius * Math.cos(thetaFirst)) + getCenter()),
                (int) (Math.round(radius * Math.sin(thetaFirst)) + getCenter()));
        Point secondBase =
                new Point((int) (Math.round(radius * Math.cos(thetaLast) + getCenter() )),
                (int) (Math.round(radius * Math.sin(thetaLast) + getCenter())));

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
                    new Point((int) (Math.round(scaleFactor * radius * Math.cos(thetaMiddle) + getCenter())),
                    (int) (Math.round(scaleFactor * radius * Math.sin(thetaMiddle) + getCenter())));

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
    private static double radiansForBaseNumber(int base, int strandLength){
        return (base / ((double) strandLength)) *
                (2 * Math.PI - (degreeGap * Math.PI / (180f)))
                + (degreeGap/2 - origin) * (Math.PI / (180f));
    }

    /**
     * X and Y coordinates are the same, so this just returns the x or y coordinate.
     * @return
     */
    private static int getCenter(){
        return  (int)(radius + imageHeight*buffer/2);
    }

}

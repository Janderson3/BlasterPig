/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.*;

/**
 *
 * @author blackMamba
 */
public class DrawingStrand extends BPStrand{

    final int imageHeight = 600;
    final int imageWidth = 600;
    final int radius = (imageHeight - 2)/2;

    public BufferedImage drawFold(int index)
    {
        BPStrandFold drawingStrand = folds.get(index);
        int strandLength = strand.length();
        BufferedImage retImage =
                new BufferedImage(imageHeight, imageWidth,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = retImage.createGraphics();
        g.setColor(Color.black);
        g.drawOval(1, 1, imageWidth -2 , imageHeight -2);

        CircleArc tempArc;
        Point firstBase, middlePoint, secondBase;
        double thetaFirst, thetaLast, thetaMiddle, scaleFactor;
        int pairing;
        for(int i=1; i<=drawingStrand.getLength(); i++)
        {
            pairing = drawingStrand.getPairingAt(i);

            if(pairing > i)
            {
                thetaFirst = (i/((double)strandLength)) * 2* Math.PI;
                thetaLast  = (pairing/((double)strandLength)) * 2 * Math.PI;



                firstBase = 
                        new Point((int)(Math.round(radius*Math.cos(thetaFirst)) + radius),
                        (int)(Math.round(radius * Math.sin(thetaFirst)) + radius));
                secondBase = 
                        new Point((int)(Math.round(radius*Math.cos(thetaLast) + radius)),
                        (int)(Math.round(radius*Math.sin(thetaLast) + radius)));

                //If they're straight across, just draw a line
                if((pairing - i) == strandLength/2)
                {
                    g.drawLine(firstBase.x, firstBase.y, secondBase.x, secondBase.y);
                }
                else
                {

                    scaleFactor = Math.min(Math.abs(thetaFirst - thetaLast),
                            Math.PI*2 - Math.abs(thetaFirst - thetaLast));
                    scaleFactor = 1 - (scaleFactor/Math.PI);

                    thetaMiddle = (thetaFirst + thetaLast)/(double)2;


                    /* We want the minor arc, so make sure the middle point
                     * is between the shortest arc of First and Last
                     */

                    if(Math.abs(thetaFirst - thetaLast) > Math.PI)
                    {
                        thetaMiddle += Math.PI;
             /*           if(thetaMiddle > 2 * Math.PI)
                        {
                            thetaMiddle -= Math.PI*2;
                        }*/
                    }



                    middlePoint =
                            new Point((int)(Math.round(scaleFactor*radius*Math.cos(thetaMiddle) + radius)),
                            (int)(Math.round(scaleFactor*radius*Math.sin(thetaMiddle) + radius)));

                    tempArc = new CircleArc(firstBase, middlePoint, secondBase);

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

        return retImage;
    }
}

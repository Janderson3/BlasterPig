
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


public class StrandScribbler{

    private int imageHeight = 700;
    private int imageWidth = 700;
    private double buffer = 0.15;
    private Color bothColor = Color.black;
    private Color firstColor = Color.green;
    private Color secondColor = Color.red;
    private float degreeGap = 10;
    private float origin = -90;
    private int approxFreqOfNumbers = 10;
    private int tickLength = 5;
    private int fontdistaneFromTic = 2;
    private int effectiveImageHeight = imageHeight;
    private int effectiveImageWidth = imageWidth;
    private int offsetX=0;
    private int offsetY=0;
    private double zoomFactor = 1;


    public StrandScribbler(){
    }


    public BufferedImage drawCircleFold(BPStrand theStrand, int firstDex, int secondDex){
        BufferedImage fullImage = drawZoomedCircleFold(theStrand,firstDex,secondDex);
        BufferedImage retImage = 
                new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = retImage.createGraphics();
        g.drawRect(0, 0, imageWidth, imageHeight);
        g.drawImage(fullImage, 0, 0, imageWidth, imageHeight,
                offsetX, offsetY,
                offsetX + imageWidth,
                offsetY + imageHeight,
                null);

        return retImage;
    }

   public BufferedImage drawCircleFold(BPStrand theStrand, int index){
        BufferedImage fullImage = drawZoomedCircleFold(theStrand,index);
        BufferedImage retImage =
                new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = retImage.createGraphics();
        g.drawImage(fullImage, 0, 0, imageWidth, imageHeight,
                offsetX, offsetY,
                offsetX + imageWidth,
                offsetY + imageHeight,
                null);

        return retImage;
    }


    private  BufferedImage drawZoomedCircleFold(BPStrand theStrand, int index)
    {
        int strandLength = theStrand.getLength();
        BufferedImage retImage =
                new BufferedImage(effectiveImageHeight, effectiveImageWidth,
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

    public void deltaOffset(int deltaX, int deltaY){
        offsetX += deltaX;
        offsetY += deltaY;
    }

    public void resetDisplay(){
        setZoomFactor(1);
        offsetX = 0;
        offsetY = 0;
    }

    public void zoomIn(){
        setZoomFactor(zoomFactor + 0.1);
    }


    public void zoomOut(){
        if(zoomFactor > 0.5){
            setZoomFactor(zoomFactor - 0.1);
        }

    }


    private void setZoomFactor(double zoom){
        zoomFactor = zoom;
        float xRatioOfCenter = (offsetX + imageWidth/2)/((float)effectiveImageWidth);
        float yRatioOfCenter = (offsetY + imageHeight/2)/((float)effectiveImageHeight);
        effectiveImageHeight = (int)(imageHeight * zoom);
        effectiveImageWidth = (int)(imageWidth * zoom);
        offsetX = (int)(xRatioOfCenter*effectiveImageWidth) - imageWidth/2;
        offsetY = (int)(yRatioOfCenter*effectiveImageHeight) - imageHeight/2;
    }

    private void circleSetup(Graphics2D g, int length) {
        Arc2D.Double a = new Arc2D.Double((buffer/2)*effectiveImageWidth,(buffer/2)*effectiveImageHeight,
                2*getRadius(),
                2*getRadius(),
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
            Point start = new Point((int)Math.round(getRadius()* Math.cos(theta)) + getCenterX(),
                    (int)Math.round(getRadius() * Math.sin(theta))+getCenterY());
            Point end = new Point((int)Math.round((getRadius() + tickLength)* Math.cos(theta)) + getCenterX(),
                    (int)Math.round((getRadius() + tickLength) * Math.sin(theta)) + getCenterY()) ;
            g.drawLine(start.x, start.y, end.x, end.y);
        }

        Font font = new Font("Serif", Font.PLAIN, 12);
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics();

       g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        for(int i=nearestFive; i<length; i = i + nearestFive){
            double theta = radiansForBaseNumber(i, length);
            int offset = fontMetrics.getHeight() +fontdistaneFromTic + tickLength;
            Point center = new Point((int)Math.round((getRadius() +offset)* Math.cos(theta)) + getCenterX(),
                    (int)Math.round((getRadius() + offset) * Math.sin(theta)) + getCenterY());
            String daString = String.valueOf(i);
            g.drawString(daString, center.x, center.y);
        }


      g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
    }

     private  BufferedImage drawZoomedCircleFold(BPStrand theStrand, int firstDex, int secondDex){
       int strandLength = theStrand.getLength();
        BufferedImage retImage =
                new BufferedImage(effectiveImageHeight, effectiveImageWidth,
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

    private void drawBaseArc(int baseOne, int baseTwo, int strandLength, Graphics2D g) {
        double thetaFirst = radiansForBaseNumber(baseOne, strandLength);
        double thetaLast = radiansForBaseNumber(baseTwo, strandLength);


        Point firstBase =
                new Point((int) (Math.round(getRadius() * Math.cos(thetaFirst)) + getCenterX()),
                (int) (Math.round(getRadius() * Math.sin(thetaFirst)) + getCenterY()));
        Point secondBase =
                new Point((int) (Math.round(getRadius() * Math.cos(thetaLast) + getCenterX() )),
                (int) (Math.round(getRadius() * Math.sin(thetaLast) + getCenterY())));

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
            }



            Point middlePoint =
                    new Point((int) (Math.round(scaleFactor * getRadius() * Math.cos(thetaMiddle) + getCenterX())),
                    (int) (Math.round(scaleFactor * getRadius() * Math.sin(thetaMiddle) + getCenterY())));

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


    private double radiansForBaseNumber(int base, int strandLength){
        return (base / ((double) strandLength)) *
                (2 * Math.PI - (degreeGap * Math.PI / (180f)))
                + (degreeGap/2 - origin) * (Math.PI / (180f));
    }

    private int getCenterX(){
        return  (int)(getRadius() + effectiveImageWidth*buffer/2);
    }

    private int getCenterY(){
        return  (int)(getRadius() + effectiveImageHeight*buffer/2);
    }

    private double getRadius(){
        if(effectiveImageWidth > effectiveImageHeight){
            return (effectiveImageHeight*(1-buffer))/2;
        }
        else{
            return (effectiveImageWidth*(1-buffer))/2;
        }
    }

    public void setApproxFreqOfNumbers(int approxFreqOfNumbers) {
        this.approxFreqOfNumbers = approxFreqOfNumbers;
    }

    public void setBothColor(Color bothColor) {
        this.bothColor = bothColor;
    }

    public void setBuffer(double buffer) {
        this.buffer = buffer;
    }

    public void setDegreeGap(float degreeGap) {
        this.degreeGap = degreeGap;
    }

    public void setFirstColor(Color firstColor) {
        this.firstColor = firstColor;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public void setSecondColor(Color secondColor) {
        this.secondColor = secondColor;
    }

    public void setOrigin(float origin) {
        this.origin = origin;
    }




}

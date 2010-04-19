

package blasterpig.drawing;


import java.awt.Point;

/**
 *
 * @author blackMamba
 */
public class CircleArc {

    private double centerk;
    private double centerh;
    private double radius;
    private double startTheta;
    private double endTheta;
    private double arcLength;

    public CircleArc(Point firstPoint, Point secondPoint, Point thirdPoint)
    {
        /**
         * We need to define an arc based on three points. The arc starts on the
         * first point, passes through the second, and ends at the third.
         *
         * First we need to find the center and radius of the circle. So we need
         * an h,k, and r that satisfies the following:
         *
         * (x1-h)^2 + (y1-k)^2 = r^2
         * (x2-h)^2 + (y2-k)^2 = r^2
         * (x3-h)^2 + (y3-k)^2 = r^2
         *
         * Subtracting the third from the other two gives us:
         *
         * (x1-h)^2 - (x3-h)^2 + (y1-k)^2 - (y3-k)^2 = 0
         * (x2-h)^2 - (x3-h)^2 + (y2-k)^2 - (y3-k)^2 = 0
         * 
         * (1): x1^2  - 2(x1)h - (x3^2 - 2(x3)h)
         *      + y1^2  - 2(y1)k - (y3^2 - 2(y3)k)
         *
         * (2): x2^2  - 2(x2)h - (x3^2 - 2(x3)h)
         *     +  y2^2  - 2(y2)k - (y3^2 - 2(y3)k)
         *
         * Group constants
         * C1 = x1^2 - x3^2 + y1^2 -y3^2
         * H1 = -2(x1) + 2(x3)
         * K1 = -2(y1) + 2(y3)
         *
         * C2 = x2^2 - x3^2 + y2^2 -y3^2
         * H2 = -2(x2) + 2(x3)
         * K2 = -2(y2) + 2(y3)
         *
         * Now we have
         * C1 + H1h +K1k = 0
         * C2 + H2h +K2k = 0
         *
         * Multiply second equation by H1/H2 and subtract from the first.
         *
         * (C1 - (C2*H1)/H2) + (K1k - k(K2*H1)/H2) =0
         * k = -(C1 - (C2*H1/H2)) / (K1 - ((K2 * H1)/H2))
         */


        double C1 = (firstPoint.x * firstPoint.x) -
                (thirdPoint.x * thirdPoint.x) + (firstPoint.y * firstPoint.y) -
                (thirdPoint.y * thirdPoint.y);
        double C2 = (secondPoint.x * secondPoint.x) -
                (thirdPoint.x * thirdPoint.x) + (secondPoint.y * secondPoint.y) -
                (thirdPoint.y * thirdPoint.y);
        double H1 = -2*(firstPoint.x - thirdPoint.x);
        double H2 = -2*(secondPoint.x - thirdPoint.x);
        double K1 = -2 * (firstPoint.y - thirdPoint.y);
        double K2 = -2 * (secondPoint.y - thirdPoint.y);



        if(H2 != 0 && K1 != ((K2 * H1)/ H2))
        {
            centerk = -(C1 - (C2*H1/H2)) / (K1 - ((K2 * H1)/ H2));
                    //((K2*H1)/H2 - (C1 - (C2*H1)/H2)) / K1;
        }
        else if(H2 == 0)
        {
            centerk = -(C2/K2);
        }
        else //TODO K1 == ((K2*H1)/H2)Something to deal with later...
        {/*
            centerh =  -(C1/H1);
            if(K2 == 0)
            {
                //Then K1 and K2 are both 0 and we have another problem.
                //The points are co-linear.
                //TODO same as above
            }
            else
            {
             centerk = -(C2 + H2 * centerh) / K2;
            }*/
        }

        if(H1 != 0)
        {
            centerh = -(K1 * centerk + C1) / H1;
        }
        else if(H2 != 0)
        {
            centerh = -(K2* centerk + C2) / H2;
        }
        else
        {
            //Here's another problem.
            //TODO, again, invalid points exception.
        }

        radius = Math.sqrt((firstPoint.x - centerh) * (firstPoint.x - centerh)
                + (firstPoint.y - centerk) * (firstPoint.y - centerk));

        startTheta = Math.atan2(-(firstPoint.y - centerk) , firstPoint.x - centerh);
        double midPointTheta = Math.atan2(-(secondPoint.y - centerk), secondPoint.x - centerh);
        endTheta = Math.atan2(-(thirdPoint.y - centerk), thirdPoint.x - centerh);
    
        startTheta = rangeCorrect(startTheta);

        midPointTheta = rangeCorrect(midPointTheta);
   
        endTheta = rangeCorrect(endTheta);

        /*There are only three possible "good" Orientations
         * Origin Start Middle End
         * Start Origin Middle End
         * Start Middle End Origin
         *
         * All others need to have start end end flipped
         */

         if(!((endTheta > midPointTheta && midPointTheta > startTheta) //Origin start middle end
          || (endTheta > midPointTheta && startTheta > endTheta)
          || (startTheta > endTheta && midPointTheta > startTheta)) )
         {
            double temp = startTheta;
            startTheta = endTheta;
            endTheta = temp;
         }

        /* Now if it passes through the origin we need to calculate the arc angle differently*/

        if(endTheta < startTheta)
        {
            arcLength = (360 - getStartTheta()) + getEndTheta();
        }
        else
        {
            arcLength = getEndTheta() - getStartTheta();
        }
    }

    public double getCenterXcoordinate()
    {
        return centerh;
    }

    public double getCenterYCoordinate()
    {
        return centerk;
    }

    public double getRadius()
    {
        return radius;
    }

    public double getBoundingBoxXCoordinate()
    {
        return centerh - radius;
    }

    public double getBoundingBoxYCoordinate()
    {
        return centerk - radius;
    }

    public double getBoundingBoxHeight()
    {
        return 2*radius;
    }

    public double getBoundingBoxWidth()
    {
        return 2*radius;
    }

    public double getStartTheta()
    {
        return startTheta * (180/Math.PI);
    }

    public double getEndTheta()
    {
        return endTheta * (180/Math.PI);
    }

    public double getArcSize()
    {
        return arcLength;
    }

    private double rangeCorrect(double angle)
    {
        if(angle < 0)
        {
            return angle + 2 * Math.PI;
        }
        else if(angle > 2*Math.PI)
        {
            return angle - 2*Math.PI;
        }
        else
        {
            return angle;
        }
    }
/*
    public static void main(String [] args)
    {
        CircleArc newCircle = new CircleArc(new Point(-1,0), new Point (0,1), new Point(0, -1));
        System.out.println("Center: (" + newCircle.getCenterXcoordinate() + " , " + newCircle.getCenterYCoordinate() + ")");
        System.out.println("Radius: " + newCircle.getRadius());
    }
 */
}

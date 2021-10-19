import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class Main {
    public static void main (String[] args) {
        Quadratic fk = new Quadratic(2,2,-4);
        fk.printStd();
        fk.printVertex();
        fk.printFactored();
        System.out.println("delta = "+ fk.delta());
        fk.printSolutions();
        fk.saveToPNG("test.png", 500, 500);
       
    }
}
class Quadratic{
    public double a,b,c;

    Quadratic(double argA, double argB, double argC){
        a = argA;
        b = argB;
        c = argC;
    }

    public void printStd(){
        System.out.println("f(x)="+a+"x^2 + " + b+"x + " +c);
    }

    public double delta(){
        return b*b - 4.0*a*c;

    }

    public double[] solve(){
        double [] tab;
        if (a == 0)
        {
            tab = new double[2];
            tab[0] = -c/b;
            tab[1] = -c/b;
            return tab;
        }
        else if (delta() >= 0.0)
        {
            tab = new double[2];
            tab[0] = (-b+Math.sqrt(delta()))/(2.0*a) ;
            tab[1] = (-b-Math.sqrt(delta()))/(2.0*a);
            return tab;
        } 
        else{
            return null;
        }

    }

    public void printSolutions(){
        if( delta() == 0 || a==0){
            System.out.println("One solution: "+ solve()[0]);
        }

        else if( delta() < 0 ){
            System.out.println("Delta <0 no solution in real numbers: ");
        }

        else{
            System.out.println("solutions: " + solve()[0] +" and "+ solve()[1]);
        }
    }

    public void printVertex(){
        if (a != 0.0){
            double p = -b/(2.0*a), q = -delta()/(4.0*a);
            System.out.println("f(x)="+a+"(x-"+p+")^2 + "+q);
        }
        else{
            System.out.println("Linear function, no vertex form of the equation.");
        }

    }

    public void printFactored(){
        if (delta() > 0.0 && a != 0.0)
        {
            double tab[] = solve();
            System.out.println("f(x)="+a+"(x-"+tab[0]+")(x-"+tab[1]+")");
        }
        else if (delta() == 0.0 && a != 0.0)
        {
            double tab[] = solve();
            System.out.println("f(x)="+a+"(x-"+tab[0]+")^2");
        }
        else{
            System.out.println("Delta <0 or linear function, no factored form of the equation.");
        }
    }

    public double f(double x){
        return  a*x*x + b*x + c; 
    }

    public void saveToPNG(String name, int sizeX, int sizeY){      
        BufferedImage myPicture = new BufferedImage(sizeX,sizeY,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = myPicture.createGraphics();

        g.setBackground(Color.WHITE);
        g.setColor(Color.BLACK);
        g.drawLine(0, sizeY/2, sizeX, sizeY/2);
        g.drawLine(sizeX/2, 0, sizeX/2, sizeY);

        double start = -(double)(sizeX/20), stop = (double)(sizeX/20); 
        double step = 0.1;
        
        g.setColor(Color.BLUE);
        while(start<stop){
            g.drawOval(sizeX/2+(int)start, (int)(-f(start))+sizeY/2, 2, 2);
            start += step;
        }

        try {
            File outputfile = new File(name);
            ImageIO.write(myPicture, "png", outputfile);
        } catch (IOException e) {
            System.out.println("I/O error while saving " + name);
        }

    }

    }



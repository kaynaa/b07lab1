//import java.io.File;

public class Driver {
    public static void main(String [] args) {
    
    
//    File f = new File("C:\\Users\\---CSCB07\\testcases.txt");
//    Polynomial p = new Polynomial(f);
    	
    Polynomial p = new Polynomial();
    p.saveToFile("saveToFileResults.txt");
    System.out.println(3);
    
    int[] arr = {0, 2, 1, 3};
    double[] arr2 = {1, -3.5, -1.9, 3};
    Polynomial p2 = new Polynomial(arr2, arr);
    
    Polynomial p3 = new Polynomial();
    
    p2.multiply(p3).saveToFile("saveToFileResults.txt");
    p2.add(p3).saveToFile("saveToFileResults.txt");
    p.multiply(p2).saveToFile("saveToFileResults.txt");
    p.add(p2).saveToFile("saveToFileResults.txt");
    System.out.println(p.hasRoot(-2));  
    
// 	  Tests for Lab 1:
//    Polynomial p1 = new Polynomial();
//    Polynomial p1 = new Polynomial(c1);
//    double [] c2 = {0,-2,0,0,-9};
//    Polynomial p2 = new Polynomial(c2);
//    Polynomial s = p1.add(p2);
//    System.out.println("s(0.1) = " + s.evaluate(0.1));
//    if(s.hasRoot(1))
//        System.out.println("1 is a root of s");
//    else
//        System.out.println("1 is not a root of s");
    }
}
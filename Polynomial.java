import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

class Polynomial {
      double[] coef;
      int[] expo;

      public Polynomial() {       	 
      }

      public Polynomial(double[] newCoef, int[] newExp){
    	  if(newCoef.length != 0) {
    		  coef = copyDb(newCoef, newCoef.length);
              expo = copyInt(newExp, newExp.length);  
    	  }
      }
      
      public Polynomial(File arg) {
    	  String poly = "";
    	  String regex ="(?=[+-])";
    	  try(Scanner readFile = new Scanner(arg)) {
    		  poly = readFile.nextLine();
    	  } catch(FileNotFoundException e){
    		  System.out.println("Error");
    		  e.printStackTrace();
    	  }
    	  
    	  if(poly == "" || poly == "0") {
     		 coef = null;
     		 expo = null;
     		 return;
     	  }
    	  
		  String[] terms = poly.split(regex);
    	  int len = terms.length;
    	  expo = new int[len];
    	  coef = new double[len];    	 
    	  
    	  for(int i=0; i<len; i++) {
    		 String[] splitted = terms[i].replace("+", "").split("(?=[x])");
    		 if(splitted.length == 1) {
    			 if(terms[i].indexOf('x')!=-1) {
    				 coef[i] = 1;
    				 if(splitted[0].replace("x", "").length() == 0) {
    					 expo[i] = 1;
    				 }
    				 else {    					 
    					 expo[i] = Integer.parseInt(splitted[0].replace("x", ""));
    				 }
    			 }
    			 else {
    				 coef[i] = Double.parseDouble(splitted[0]);
    				 expo[i]=0;
    			 }
    		 }
    		 else {
    			 if(terms[i].indexOf("-x") != -1) {
    				 coef[i] = -1;
    			 }
    			 else {
    				 coef[i] = Double.parseDouble(splitted[0]);
    			 }    			 
    			 if(splitted[1].length() == 1) { 
    				 expo[i] = 1;
    			 } 
    			 else {
    				 expo[i] = Integer.parseInt(splitted[1].replace("x", ""));
    			 }
    		 }    		 		
    	  }
      }
      
      public int[] copyInt(int[] a, int len) {
    	  int[] b = new int[len];
    	  int arrLen = a.length;
    	  for(int i = 0; i < len; i++) {
    		  if(i >= arrLen) b[i] = 0;
    		  else b[i] = a[i];
    	  }
    	  return b;
      }
      
      public double[] copyDb(double[] a, int len) {
    	  double[] b = new double[len];
    	  int arrLen = a.length;
    	  for(int i = 0; i < len; i++) {
    		  if(i >= arrLen) b[i] = 0;
    		  else b[i] = a[i];
    	  }
    	  return b;
      }
      
      public int find(int a, int[] arr) {
    	  int len = arr.length;
    	  for(int i=0; i<len; i++) {
    		  if(arr[i] == a) return i;
    	  }
    	  return -1;
      }
      
      public Polynomial add(Polynomial arg) {
    	  if(this.coef == null && arg.coef == null) {
    		  return new Polynomial();
    	  }
    	  else if(this.coef == null) {    	  		
	  		  return new Polynomial(arg.coef, arg.expo);
	  	  }
    	  else if(arg.coef == null) {
    		  return new Polynomial(this.coef, this.expo);
    	  }
	      int thisLen = this.expo.length;
	      int argLen = arg.expo.length;
	      int resExpo[] = copyInt(this.expo, thisLen + argLen);
	      double resCoef[] = copyDb(this.coef, thisLen + argLen);
	      int idx = thisLen;
	      for(int i=0; i<argLen; i++) {
	    	  if(find(arg.expo[i], resExpo) != -1) {
	    		  resCoef[find(arg.expo[i], resExpo)] += arg.coef[i];
	    	  }
	    	  else {
	    		  resCoef[idx] = arg.coef[i];
	    		  resExpo[idx] = arg.expo[i];
	    		  idx++;
	    	  }
	      }
	      resCoef = copyDb(resCoef, idx);
	      resExpo = copyInt(resExpo, idx);
	      return new Polynomial(resCoef, resExpo);
      }

      public double evaluate(double arg) {
    	  if(this.coef == null) return 0;
          int len = this.coef.length;
          double result = 0.0;
          for(int i=0; i<len; i++){
                result += Math.pow(arg, this.expo[i]) * this.coef[i];
          }
          return result;
      }

      public boolean hasRoot(double arg) {
            return evaluate(arg) == 0;
      }
      
      public Polynomial multiply(Polynomial arg) {
    	  if(this.coef == null || arg.coef == null) return new Polynomial();
    	  int thisLen = this.expo.length;
          int argLen = arg.expo.length;
          int resExpo[] = new int[thisLen * argLen];
          double resCoef[] = new double[thisLen * argLen];
          for(int i=0; i<thisLen * argLen; i++) {
        	  resExpo[i] = -1;
          }
          int idx = 0;
          for(int i=0; i<thisLen; i++) {
        	  for(int j=0; j<argLen; j++) {
        		 int found = find(this.expo[i] + arg.expo[j], resExpo);
        		 if(found != -1) {
        			 resCoef[found] += this.coef[i] * arg.coef[j];
        		 }
        		 else {
        			 resExpo[idx] = this.expo[i] + arg.expo[j];
        			 resCoef[idx] = this.coef[i] * arg.coef[j];
        			 idx++;
        		 }        		 
        	  }
          }
          resCoef = copyDb(resCoef, idx);
          resExpo = copyInt(resExpo, idx);
          return new Polynomial(resCoef, resExpo);
      }
      
      public void saveToFile(String filename) {
    	  String result = "";
    	  if(this.expo != null) {
    		  int len = this.expo.length;    	  
        	  for(int i=0; i<len; i++) {
        		  if(this.coef[i]>0 && i>0) result += "+";
        		  result += Double.toString(coef[i]);    				  
        		  if(expo[i] == 1) result += "x";
        		  else if (expo[i] > 1) result += "x" + Integer.toString(expo[i]);
        	  }
    	  }
//    	  System.out.println(result);
    	  try {
        	  FileWriter writeFile = new FileWriter(filename);
        	  writeFile.write(result);
        	  writeFile.close();    		  
    	  } catch (IOException e) {
    		  System.out.println("Error");
    		  e.printStackTrace();
    	  }
      }
      
      
}
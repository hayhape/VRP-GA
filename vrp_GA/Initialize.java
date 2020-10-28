package vrp_GA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


import static vrp_GA.Parameter.*;

public class Initialize {
	
	 static double[][] disMat;
	
	 public static void input() throws Exception {
		 for(int i=0;i<totalNumber;i++) {   
			 customers[i]=new Customer();
		 }
		 
    	 String path="T:\\javaÏà¹Ø\\lib\\c102_1.txt";
	  	 String line=null;
    	 String[] str=null;
    	 Scanner cin=new Scanner(new BufferedReader(new FileReader(path)));
    	 for(int i=0;i<totalNumber;i++) {
    		 line=cin.nextLine();
    		 line.trim();
    		 str=line.split("\\s+");
    		 customers[i].id=Integer.parseInt(str[1]);
    		 customers[i].x=Double.parseDouble(str[2]);
    		 customers[i].y=Double.parseDouble(str[3]);
    		 customers[i].demand=Double.parseDouble(str[4]);
    		 customers[i].begin=Double.parseDouble(str[5]);
    		 customers[i].end=Double.parseDouble(str[6]);
    		 customers[i].service=Double.parseDouble(str[7]);
    	 }
    	 cin.close();
    	
    	disMat=new double[totalNumber][totalNumber];
    	 for(int i=1;i<totalNumber;i++) {
    		 for(int j=1;j<totalNumber;j++) {
    			 disMat[i-1][j-1]=Math.sqrt(((customers[i-1].x-customers[i].x)
							*(customers[i-1].x-customers[i].x)+
					(customers[i-1].y-customers[i].y)
					*(customers[i-1].y-customers[i].y)));
    			 disMat[i][j]=double_truncate(disMat[i][j]);
    		 }
    	 } 
     }
     
     public static double double_truncate(double v){
 		int iv = (int) v;
 		if(iv+1 - v <= 1e-6)
 			return iv+1;
 		double dv = (v - iv) * 10;
 		int idv = (int) dv;
 		double rv = iv + idv / 10.0;
 		return rv;
 	}	
}

package vrp_GA;


import static vrp_GA.Parameter.*;

import java.util.ArrayList;
import java.util.Random;

import static vrp_GA.Initialize.*;

public class EvaluatePath {
	
	 public static boolean checkIfNotLegal(Individual species) {
		 double perLoad=0;
		 double timesNow=0;
		 System.out.println("enter check");
		 for(int i=1;i<species.path.size();i++) {
			 if(species.path.get(i).id==0) {
				 perLoad=0;
				 continue;
			 }
			 while(species.path.get(i).id!=0) {
				 perLoad=perLoad+species.path.get(i).demand;
			     if(perLoad>vehicleCapacity) {
				     return true;
			     }
			 }
		 }
		 for(int i=1;i<species.path.size();i++) {
			 while(species.path.get(i).id==0) {
				 timesNow=0;
				 continue;
			 }
			 timesNow+=disMat[species.path.get(i-1).id][species.path.get(i).id]/speedVehicle;
			 if(timesNow>species.path.get(i).end) {
				 return true;
			 }
			 timesNow+=1;
		 }
		 
		 return false;
		 
	 }
	 
	 
	 public static <V> ArrayList<V> randomList(ArrayList<V> sourceList){   //生成随机的arraylist
	    	if (isEmpty(sourceList)) {
	            return sourceList;
	        }
	    	
	    	ArrayList<V> randomList = new ArrayList<V>( sourceList.size( ) );
	    	do{
	    		int randomIndex = Math.abs( new Random( ).nextInt( sourceList.size() ) );
	        	randomList.add( sourceList.remove( randomIndex ) );
	    	}while( sourceList.size( ) > 0 );
	    	
	    	return randomList;
	 }
	    
	 public static <V> boolean isEmpty(ArrayList<V> sourceList) {
	        return (sourceList == null || sourceList.size() == 0);
	 }
	 
    
}

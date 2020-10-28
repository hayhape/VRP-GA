package vrp_GA;

import static vrp_GA.Parameter.*;
import static vrp_GA.Initialize.*;


import java.util.ArrayList;



public class Individual {
    ArrayList<Customer> path=new ArrayList<Customer>();
    ArrayList<Double> times=new ArrayList<Double>();
	double fitness;
	double distance;
	float rate;
	double Loss;  //超出配送时间的惩罚
	Individual next;
	
	Individual() {
		this.next=null;
		this.fitness=0;
		this.distance=0;
		this.rate=0.0f;
	}
    
	 public  void RandomCreat() {
		 System.out.println(disMat[0][1]);
	        int num=totalNumber-1;
	        if(path.isEmpty()==true)
	        	path.add(customers[0]);
	        
	        boolean[] customerIsArranged=new boolean[totalNumber];
	        for(int i=1;i<totalNumber;i++) {
	        	customerIsArranged[i]=false;
	        }
	        
	        
	        
	        for(int i=0;i<vehicleNumber;i++) {
	        	ArrayList<Customer> route=new ArrayList<Customer>();
	        	ArrayList<Double> serve=new ArrayList<Double>();
	        	if(serve.isEmpty()==true)
		        	serve.add(0.0);
	        	int load=0;
	        	double timeNow=0;
	        	int last_point=0;
	        	
	        	for(int j=1;j<totalNumber;j++) {
	        		if(customerIsArranged[j]==false) {
	        			if(load+customers[j].demand<vehicleCapacity&&timeNow+disMat[last_point][j]/speedVehicle<customers[j].end) {
	        				route.add(customers[j]);
	        				customerIsArranged[j]=true;
	        				load+=customers[j].demand;
	        				customers[i].Route_id=i;
	        				timeNow+=disMat[last_point][j]/speedVehicle;
	        				last_point=customers[j].id;
	        				
	        				serve.add(timeNow);
	        				timeNow+=1;
	        				num--;
	        			}else {
	        				break;
	        			}
	        		}
	        	}
	        	route.add(customers[0]);
	        	serve.add(0.0);
	        	path.addAll(route);
	        	times.addAll(serve);
	        	if(num==0)
	        		break;
	        }
	    	
	    		/*for(int i=0;i<path.size();i++) {
	    		    System.out.println("the "+i+" route:");
	    		    for(int j=0;j<path.get(i).size();j++) {
	    		    	System.out.print(path.get(i).get(j).id+"->");
	    		    }
	    		    System.out.println();
	    		    
	    		}
	    		*/
	    }
	 

	public void getFitness() {   //计算适应度
		getDistance();
		getLoss();
		this.fitness=(1.0f/(distance*perDistanceCost+path.size()*perVehicleCost+Loss*perLoss));
	}
	
	public void getLoss() {   //计算损失
		double totalLoss=0;
		for(int i=1;i<times.size();i++) {
			if(times.get(i)>15) 
				totalLoss+=times.get(i)-15;
		}
		this.Loss=totalLoss;
	}
	
	public void getDistance() {    //计算距离
		float totalDistance=0;
		for(int i=1;i<path.size();i++) {
			totalDistance+=(float)disMat[path.get(i-1).id][path.get(i).id];
		}
		this.distance=totalDistance;
	}
	
	 public void updateForMutation() {   //更新时间链表
    	 times.clear();
    	 times.add(0.0);
    	 double timesNow=0;
    	 for(int i=1;i<path.size();i++) {
    		 while(path.get(i).id==0) {
    			 timesNow=0;
    			 times.add(0.0);
    			 continue;
    		 }
    		 timesNow+=disMat[path.get(i-1).id][path.get(i).id]/speedVehicle;
    		 times.add(timesNow);
    		 timesNow+=1;
    	 } 
     }
	
	
	public Individual cloneSpecies() {
		Individual path=new Individual();
	    path.distance=this.distance;
	    path.fitness=this.fitness;
	    path.Loss=this.Loss;
	    path.path.addAll(this.path);
		
		return path;
	}
	
	void printRate()
	{
		System.out.print("路线：");
		for(int i=0;i<path.size();i++) {
		    System.out.print(path.get(i).id+"->");
		}
		System.out.print("长度：" + distance);
	}
	

	
	
}

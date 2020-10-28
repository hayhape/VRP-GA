package vrp_GA;

import java.util.ArrayList;
import java.util.Random;


import static vrp_GA.EvaluatePath.*;
import static vrp_GA.Parameter.*;


public class GeneticProcess {
	Individual   run(Population list) {
		
		creat(list);

		//for(int i=0;i<iteration_number;i++) {
			//select(list);
			Crossover(list);
		//}
		
		 return getBest(list);
		
	}
	
	public void  creat(Population list) {
	    for(int i=0;i<speciesNum;i++) {
	    	Individual species=new Individual();
	    	species.RandomCreat();
	    	list.add(species);
	    }
	    
	    
	}
	
	public void select(Population list) {     //根据适应度选择个体进入下一个种群
		int copyNum=(int)(speciesNum/2);
		Rate(list);
		Individual talentSpecies=new Individual();
		Individual point=list.head.next;
		float talentDis=Float.MAX_VALUE;
		while(point!=null) {
			if(talentDis>point.distance) {
				talentDis=(float)point.distance;
				talentSpecies=point;
			}
		}
		
		
		//将最大适应度的物种复制到新种群里
		Population newList=new Population();
		for(int i=0;i<copyNum;i++) {
			Individual newSpecies=talentSpecies.cloneSpecies();
			newList.add(newSpecies);
		}
		
		//轮盘赌剩余的
		int randomNum=speciesNum-copyNum;
	    for(int i=0;i<randomNum;i++) {
	    	double select_rate=Math.random();
	    	Individual Otherpoint=list.head.next;
	    	while(Otherpoint!=null&&Otherpoint!=talentSpecies) {
	    		
	    		if(select_rate<=Otherpoint.rate) {
	    			Individual newSpecies=new Individual();
	    			newSpecies=Otherpoint.cloneSpecies();
	    			newList.add(newSpecies);
	    			break;
	    		}
	    		else {
	    			select_rate=select_rate-Otherpoint.rate;
	    		}
	    		Otherpoint=Otherpoint.next;
	    	}
	    	if(Otherpoint==null||Otherpoint==talentSpecies) {
	    		point=list.head;
	    		while(point.next!=null) {
	    			point=point.next;
	    		}
	    		Individual newSpecies=point.cloneSpecies();
	    		newList.add(newSpecies);
	    	}
	    }
	    list.head=newList.head;
	}
	
	
	public void selectByTwoOpt(Population list) {
		Population newList=new Population();
		Individual point=list.head.next;
		int selectNum=0;
		Rate(list);
	    while(selectNum<speciesNum) {
	    	Random rand=new Random();
	    	int random=rand.nextInt(speciesNum-1);
	    	for(int i=0;i<random-1;i++) {
	    		point=point.next;
	    	}
	    	if(point.fitness>point.next.fitness) {
	    		Individual newSpecies=new Individual();
	    		newSpecies=point.cloneSpecies();
	    		newList.add(newSpecies);
	    	}
	    	else {
	    		Individual newSpecies=new Individual();
	    		newSpecies=point.next.cloneSpecies();
	    		newList.add(newSpecies);
	    	}
	    	selectNum++;
	    }
	    list.head=newList.head;
	}
	
	public void Mutation(Population list) {
		int count=0;
		int i=0;
		Individual point=list.head.next;
		//Rate(list);
		float talentFitness=0.0f;
		while(point!=null) {
			i++;
			if(talentFitness<point.fitness) {
				talentFitness=(float)point.fitness;
				count=i;
			}
		}
		point=list.head.next;
		for(int j=1;j<count;j++) {
			point=point.next;
		}
		do {
	
			double rand=Math.random();
			if(rand>mutation_rate) {
				Random random=new Random();
				int left=0;
				int right=0;
			    while(left==0||right==0) {
						left=random.nextInt(point.path.size());
						right=random.nextInt(point.path.size());
				   if(left>right) {
					  int temp=right;
					  right=left;
					  left=temp;
				   }
			    }
			    Customer temp=point.path.get(left);
			    point.path.remove(left);
			    point.path.add(left,point.path.get(right+1));
			    point.path.remove(right);
			    point.path.add(right,temp);
			}
		}while(checkIfNotLegal(point));
	}
	
	public void Crossover(Population list) {    //交换操作
		Individual point=list.head;
		double crossover_rate=Math.random();
		
		if(crossover_rate>crossoverRate_lower){
			Random rand=new Random();
			int random=rand.nextInt(speciesNum-1);
		    while(random!=0) {
		    	point=point.next;
		    	random--;
		    }
		    if(point.next!=null) {
		    	do {
		    	Random random1=new Random();
				int left=0;
				int right=0;
			    while(left==0||right==0) {
						left=random1.nextInt(point.path.size()-1);
						right=random1.nextInt(point.path.size()-1);
				   if(left>right) {
					  int temp=right;
					  right=left;
					  left=temp;
				   }
			    }
			    
			    ArrayList<Customer> sourceList=new ArrayList<Customer>();
			    for(int i=1;i<left;i++) {
			    	sourceList.add(point.path.get(i));
			    }
			    for(int i=right;i<point.path.size()-1;i++) {
			    	sourceList.add(point.path.get(i));
			    }
			    
			  
			    ArrayList<Customer> randomList=new ArrayList<Customer>();
			    randomList=randomList(sourceList);
			    
			   
			    
			    int count=0;
			    for(int i=1;i<left;i++) {
			    	point.path.remove(i);
			    	point.path.add(i,randomList.get(count));
			    	count++;
			    }
			    for(int i=right+1;i<point.path.size()-1;i++) {
			    	point.path.remove(i);
			    	point.path.add(i,randomList.get(count));
			    	count++;
			    }
			    
			    }while(checkIfNotLegal(point));
		    	System.out.println("finish");
		    }
		    
		}
	
		
	}
	
	
	
	
	public void Rate(Population list) {    //计算物种被选中的概率
		Individual point=list.head.next;
		int totalFitness=0;
		while(point!=null) {
			point.getFitness();
			totalFitness+=point.fitness;
			point=point.next;
		}
		
		point=list.head.next;
		while(point!=null) {
			point.rate=(float)point.fitness/totalFitness;
			point=point.next;
		}
	}
	
	
	Individual getBest(Population list) {
		
			float distance=Float.MAX_VALUE;
			Individual bestSpecies=null;
			Individual point=list.head.next;//游标
			while(point != null)//寻找表尾结点
			{
				if(distance > point.distance)
				{
					bestSpecies=point;
					distance=(float)point.distance;
				}

				point=point.next;
			}
			
	        return bestSpecies; 
		

	}
	

	
	
	

   
}

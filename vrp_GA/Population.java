package vrp_GA;

public class Population {
    Individual head;
	

	Population(){
		head=new Individual();
		
	}
	
	public void add(Individual species) {
		Individual point=head;
		while(point.next!=null) {
			point=point.next;
		}
		point.next=species;
	}
}

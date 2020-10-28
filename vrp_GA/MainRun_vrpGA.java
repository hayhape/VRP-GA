package vrp_GA;



public class MainRun_vrpGA {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
        Initialize.input();
        Population list=new Population();
        GeneticProcess run=new GeneticProcess();
        Individual bestRoute=run.run(list);
        bestRoute.printRate();
	}

}



/*while(!depot[point].isArranged) {
	System.out.println(disMat[point-1][point]);
	if(depot[point].demand+vehicle[i].load<vehicle[i].capacity
	   &&timeNow+disMat[point-1][point]>depot[point].begin
	   &&timeNow+disMat[point-1][point]<depot[point].end
	   &&timeNow+disMat[point-1][point]+depot[point].service+disMat[point][0]<depot[0].end) {

			vehicle[i].path.add(depot[point]);
			vehicle[i].load+=depot[point].demand;
			vehicle[i].time.add(timeNow);
			timeNow+=disMat[point-1][point]+depot[point].service;
			point++;
		
	}else {
		vehicle[i].path.add(depot[0]);
		vehicleIsEmpty=true;
		break;
	}
}
*/
package vrp_GA;

public class Customer {

     int id;   //�ڵ���
     int Route_id;   //�ڵ���������·�����
     double x,y; //����
     double demand;  //����
     double begin,end,service;
     
     
     
     public Customer() {
    	 this.id=0;
    	 this.Route_id=0;
    	 this.x=0;
    	 this.y=0;
    	 this.demand=0;
    	 this.begin=0;
    	 this.end=0;
    	 this.service=0;
     }
}

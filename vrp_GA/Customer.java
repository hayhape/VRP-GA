package vrp_GA;

public class Customer {

     int id;   //节点编号
     int Route_id;   //节点所属车辆路径编号
     double x,y; //坐标
     double demand;  //需求
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

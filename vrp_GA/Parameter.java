package vrp_GA;



public class Parameter {
	
    static int customerNumber=100;
    static int totalNumber=101;
    
    static int vehicleNumber=12;   
    static int vehicleCapacity=200;
    static int perVehicleCost=50;   //单位车辆固定成本
    static double perDistanceCost=0.002;   //单位配送成本
    static double perLoss=0.5;  //单位新鲜度损失
    static int speedVehicle=300;     //车辆行驶速度
    
	static Customer[] customers=new Customer[totalNumber];
	
	static Population[] route=new Population[vehicleNumber];
	
	 //迭代参数设置
	 static int speciesNum=200;
	 static int iteration_number=1000;  //迭代次数
	 static double crossoverRate_upper=0.9;
	 static double crossoverRate_lower=0.1;
	 static double mutation_rate=0.4;
	
}

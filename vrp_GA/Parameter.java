package vrp_GA;



public class Parameter {
	
    static int customerNumber=100;
    static int totalNumber=101;
    
    static int vehicleNumber=12;   
    static int vehicleCapacity=200;
    static int perVehicleCost=50;   //��λ�����̶��ɱ�
    static double perDistanceCost=0.002;   //��λ���ͳɱ�
    static double perLoss=0.5;  //��λ���ʶ���ʧ
    static int speedVehicle=300;     //������ʻ�ٶ�
    
	static Customer[] customers=new Customer[totalNumber];
	
	static Population[] route=new Population[vehicleNumber];
	
	 //������������
	 static int speciesNum=200;
	 static int iteration_number=1000;  //��������
	 static double crossoverRate_upper=0.9;
	 static double crossoverRate_lower=0.1;
	 static double mutation_rate=0.4;
	
}

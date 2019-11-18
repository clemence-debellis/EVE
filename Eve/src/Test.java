import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Test {
	Avancer aa;
	LAvue vue;

	public static void main(String[] args) {
		Test w = new Test();
		//while(Button.ENTER.isUp()){
			
			//info pour moi pour afficher en continue 
			//Delay.msDelay(500);
			//System.out.println(vue.getDistance());
			
			w.Detection();
	//		   }
		
		
}
	//constructeur
	public Test() {
		aa = new Avancer(MotorPort.B,MotorPort.C);
		vue = new LAvue(SensorPort.S3);
	}
	
	public void AvancerTantQueT(double f){
		
		while (vue.getDistance()>=f && Button.ENTER.isUp()){
    		aa.avancer();
    	}
		aa.stop();
	}
	
	public boolean PALET() {
		aa.avancer();
		Delay.msDelay(1000);
		
		if(vue.getDistance() == 0.37) {
			return true;
		}
		
		return false;
		
	}
	
	public void Detection() {
	 aa.tcsD(100);
	 if(vue.getDistance()>=0.37){
		 aa.stop();
	 

	 if(this.PALET()) {
		System.out.print("Ok on le voit les gars");
	 }
	 
	 else {
		 System.out.print("MDR t'as cru j'étais qui");
	 }
	 }
	}

}
	


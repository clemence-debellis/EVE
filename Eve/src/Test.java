import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Test {
	Avancer roues;
	LAvue vue;
	
	public static void main(String[] args) {
		Test w = new Test();
		w.DetectionDunObjet();
	}
	//constructeur
	public Test() {
		roues = new Avancer(MotorPort.B,MotorPort.C);
		vue = new LAvue(SensorPort.S3);
	}
	
	
	public void AvancerTantQue(double f){

		roues.setspeed(300);
		while (vue.getDistance()>=f && Button.ENTER.isUp()){
			roues.avancer();
		}
		roues.stop();
	}

	public void AvancerTantQue(boolean b){
		if (b==true)
			this.roues.avancer();
	}


	public boolean PALET(){
		float dd = vue.getDistance();
		roues.setspeed(50);
		roues.avancer();
		Delay.msDelay(2000);
		roues.stop();
		float gg = vue.getDistance();
		

		if((gg - dd) > 0) {
			return true;
		}

		return false;

	}

	public void DetectionDunObjet() {
		roues.setspeed(50);
		int i = 0;
		boolean b;

		while( Button.ENTER.isUp() && i==0){

			roues.rotateAsynch(1000);
			if(vue.getDistance()>0){
				roues.stop();
				this.AvancerTantQue(0.37);
			}
			
			b = this.PALET();
			if(b) {
				roues.stop();
				i=1;
			}
		}
	}

}



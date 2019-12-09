import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Test {
	Avancer roues;
	LAvue vue;
	
	public static void main(String[] args) {
	}
	//constructeur
	public Test() {
		roues = new Avancer();
		vue = new LAvue(SensorPort.S3);
	}
	
	
	public void AvancerTantQue(double f, LAvue vue){

		roues.setspeed(300);
		while (vue.getDistance()>= f && vue.getDistance()<=0.2){
			roues.avancer();
		}
		roues.stop();
	}

	
	public void ReculerTantQue(double f){

		roues.setspeed(300);
		while (vue.getDistance()>=f && Button.ENTER.isUp()){
			roues.reculer();
		}
		roues.stop();
	}

	public void AvancerTantQue(boolean b){
		if (b==true)
			this.roues.avancer();
	}


	public boolean PALET(){
		float dd = vue.getDistance();
		System.out.println(dd);
		Delay.msDelay(1000);
		roues.setspeed(200);
		roues.avancer();
		Delay.msDelay(1500);
		roues.stop();
		float gg = vue.getDistance();
		System.out.println(gg);
		Delay.msDelay(1000);
		

		if((gg - dd) > 0) {
			return true;
		}

		return false;

	}
	
	

	public boolean DetectionDunObjetG(CaptTactile capt,char cotes) {
		roues.setspeed(100);
		int j = 0;
		boolean b;

			roues.setspeed(40);
			roues.rotateAsynchG(1000,2000);
			
			while(j==0) {
			if(vue.getDistance() < 1){
				roues.stop();
				roues.rotateAsynchG(-50,500);
				Delay.msDelay(1000);
				this.AvancerTantQue(0.34,vue);
				j++;
			}
			}
			j=0;
			b = this.PALET();
			if(b) {
				capt.avancerJusquePalet(roues,cotes);
				j=5;
			}
			
			else {
				roues.reculerTemps(2000);
				Delay.msDelay(2000);
				roues.stop();
				j=10;
			}
		return (j==5);
	}
	
	public boolean DetectionDunObjetD(CaptTactile capt,char cotes) {
		roues.setspeed(100);
		int j = 0;
		boolean b;

			roues.setspeed(40);
			roues.rotateAsynchD(1000,2000);
			
			while(j==0) {
			if(vue.getDistance() < 1){
				roues.stop();
				roues.rotateAsynchD(-50,500);
				Delay.msDelay(1000);
				this.AvancerTantQue(0.34,vue);
				j++;
			}
			}
			j=0;
			b = this.PALET();
			if(b) {
				return capt.avancerJusquePalet(roues,cotes);
			}
			
			else {
				roues.reculerTemps(2000);
				Delay.msDelay(2000);
				roues.stop();
				return false;
			}
	}

}



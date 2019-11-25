import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class LAvue extends EV3UltrasonicSensor{
	public static void main(String[] args) {
			
			//info pour moi pour afficher en continue 
			//Delay.msDelay(500);
			//System.out.println(vue.getDistance());
		}
	
	
	public LAvue(Port port){
		super(port);
	}
	
	public float getDistance() {
		SampleProvider distance= this.getMode("Distance");
		float[] sample = new float[distance.sampleSize()];
		distance.fetchSample(sample, 0);
		if(sample[0] == 0)
			return 100;
		
		else
		 return sample[0];
	}
}
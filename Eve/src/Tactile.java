import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Tactile {

	public static void main(String[]args) {

   EV3TouchSensor uTouch = new EV3TouchSensor(SensorPort.S1);
   SampleProvider touche =uTouch.getMode(0);
   float[]sample= new float[touche.sampleSize()];
   
   while(Button.ENTER.isUp()) {
	   touche.fetchSample(sample, 0);
	   Delay.msDelay(500);
	   System.out.println(sample[0]);
   }
	}
}

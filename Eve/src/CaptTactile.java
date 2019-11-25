import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.Touch;
import lejos.utility.Delay;
import lejos.robotics.TouchAdapter;

public class CaptTactile extends EV3TouchSensor{

	Avancer aa = new Avancer(MotorPort.B,MotorPort.C);
	RegulatedMotor pinces= new EV3LargeRegulatedMotor (MotorPort.D);

	public CaptTactile(Port port){
		super(port);
	}

	public boolean isPressed(){
		float[] sample = new float[1];
		fetchSample(sample, 0);

		return sample[0] != 0;
	}

	public void avancerJusquePalet() {
		aa.avancer();
		while (this.isPressed()==false) {
			Delay.msDelay(100);
		}
		aa.stop();
		pinces.backward();
		Delay.msDelay(250);
		pinces.stop();
	}
}



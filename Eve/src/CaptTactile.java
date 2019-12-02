import java.io.IOException;
import java.util.Properties;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
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

	 RegulatedMotor pinces= new EV3MediumRegulatedMotor(MotorPort.D);
	 public int SPEED =800;

	public CaptTactile(){
		super(SensorPort.S1);
	}

	public boolean isPressed(){
		float[] sample = new float[1];
		fetchSample(sample, 0);
		return sample[0] != 0;
	}
	
	public void OuvertureDesPinces(){
		pinces.forward();
		Delay.msDelay(2000);
		pinces.stop();
	}

	public void FermetureDesPinces(){
		pinces.backward();
		Delay.msDelay(2000);
		pinces.stop();

	}

	public void avancerJusquePalet(Avancer aa) {
		OuvertureDesPinces();
		aa.setspeed(500);
		aa.avancer();
		while (this.isPressed()==false) {
			Delay.msDelay(100);
		}
		aa.stop();
		FermetureDesPinces();
	}
	public void recupPremierPalet(Properties prop, Test t, TestColor tc, CaptTactile capt) throws IOException {
		//Test t =new Test();
		
		t.AvancerTantQue(0.36);
		avancerJusquePalet(t.roues);
		t.roues.rotateAsynch(-185);
		t.AvancerTantQue(0.09);
		t.roues.rotateAsynch(187);
		//trouverOuest();
		tc.posePaletCamp(prop,t,capt);
	}
}

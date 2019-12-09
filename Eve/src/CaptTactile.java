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

	static RegulatedMotor pinces= new EV3MediumRegulatedMotor (MotorPort.D);
	public int SPEED =500;

	public CaptTactile(){
		super(SensorPort.S1);
	}

	public boolean isPressed(){
		float[] sample = new float[1];
		fetchSample(sample, 0);

		return sample[0] != 0;
	}

	public static void OuvertureDesPinces(){
		pinces.forward();
		Delay.msDelay(2000);
		pinces.stop();
	}

	public static void FermetureDesPinces(){
		pinces.backward();
		Delay.msDelay(2000);
		pinces.stop();

	}

	public boolean avancerJusquePalet(Avancer aa,char cotes) {
		
		this.OuvertureDesPinces();
		aa.setspeed(500);
		aa.avancer();
		int i=0;
		while (this.isPressed()==false && i<300) {
			Delay.msDelay(100);
			i++;
		}
		aa.stop();
		this.FermetureDesPinces();
		if (i>400) {
			return false;
		}
		return true;
	}

	public void recupPremierPalet(Properties prop, Test t, TestColor tc, CaptTactile capt, LAvue yeux,char cotes,Boussole boussole) throws IOException {
		//Test t =new Test();

		t.AvancerTantQue(0.35, yeux);
		avancerJusquePalet(t.roues,cotes);

		if(cotes=='d') {
			boussole.trouverSud(t.roues);
			t.AvancerTantQue(0.1, yeux);
			boussole.trouverEst(t.roues);
		}
		else {
			boussole.trouverSud(t.roues);
			t.AvancerTantQue(0.1, yeux);
			boussole.trouverOuest(t.roues);
		}
	}
}



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

	public void avancerJusquePalet(Avancer aa) {
		this.OuvertureDesPinces();
		aa.setspeed(500);
		aa.avancer();
		while (this.isPressed()==false) {
			Delay.msDelay(100);
		}
		aa.stop();
		this.FermetureDesPinces();
	}

	public void recupPremierPalet(Properties prop, Test t, TestColor tc, CaptTactile capt, LAvue yeux) throws IOException {
		//Test t =new Test();

		t.AvancerTantQue(0.35, yeux);
		avancerJusquePalet(t.roues);
		//il faut le faire tourner de l'autre cot� puis le faire avancer un peu puis le recadrer avec la boussole poour aller au camp !
		t.roues.rotateAsynchG(-185,1500);
		t.AvancerTantQue(0.1, yeux);
		t.roues.rotateAsynchG(187,1500);
		//trouverOuest();
		tc.posePaletCamp(prop,t,capt,yeux);
	}
}



import java.io.IOException;
import java.util.Properties;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class CaptTactile extends EV3TouchSensor{

	static RegulatedMotor pinces= new EV3MediumRegulatedMotor (MotorPort.D);
	public int SPEED =500;

	/**
	 * @author margaux
	 * Constructeur de la classe CaptTactile
	 */
	public CaptTactile(){
		super(SensorPort.S1);
	}

	/**
	 * @author margaux
	 * @return true si  le capteur est presse, false sinon
	 * Return si la capteur est presse ou non
	 */
	public boolean isPressed(){
		float[] sample = new float[1];
		fetchSample(sample, 0);

		return sample[0] != 0;
	}

	/**
	 * @author mathieu
	 * Ouvre les pinces
	 */
	public void OuvertureDesPinces(){
		pinces.forward();
		Delay.msDelay(2000);
		pinces.stop();
	}

	/**
	 * @author mathieu
	 * Ferme les pinces
	 */
	public void FermetureDesPinces(){
		pinces.backward();
		Delay.msDelay(2000);
		pinces.stop();
	}

	/**
	 * @author margaux
	 * @param roues duo de toues synchronisé
	 * @param cotes
	 * @return
	 */
	public boolean avancerJusquePalet(DuoDeRouesSynchro roues,char cotes) {
		
		this.OuvertureDesPinces();
		roues.setspeed(500);
		roues.avancer();
		int i=0;
		while (this.isPressed()==false && i<300) {
			Delay.msDelay(100);
			i++;
		}
		roues.stop();
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
			boussole.trouverNord(t.roues);
			t.AvancerTantQue(0.22, yeux);
			boussole.trouverOuest(t.roues);
		}
		else {
			boussole.trouverSud(t.roues);
			t.AvancerTantQue(0.22, yeux);
			boussole.trouverEst(t.roues);
		}
	}
}



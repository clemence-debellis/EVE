import java.io.IOException;
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
	 * @param cotes cotes duquel se trouve le robot
	 * @return true si un un palet a ete recupere false sinon
	 * Avance jusqu a un palet et return si il a bien eu un palet ou si il y en avait pas
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

	/**
	 * @author margaux
	 * @param vehicule duo de roues synchro
	 * @param cotes cotes de départ du robot
	 * @param boussole la boussole du robot
	 * @throws IOException
	 * Recupere le palet et le rammène dans le camp adverse (pour le premier)
	 */
	public void recupPremierPalet(Vehicule vehicule,char cotes,Boussole boussole) throws IOException {

		vehicule.AvancerTantQue(0.35, vehicule.vue);
		avancerJusquePalet(vehicule.roues,cotes);

		if(cotes=='d') {
			boussole.trouverNord(vehicule.roues);
			vehicule.AvancerTantQue(0.22, vehicule.vue);
			boussole.trouverOuest(vehicule.roues);
		}
		else {
			boussole.trouverSud(vehicule.roues);
			vehicule.AvancerTantQue(0.22, vehicule.vue);
			boussole.trouverEst(vehicule.roues);
		}
	}
}



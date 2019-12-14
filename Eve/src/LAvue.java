import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
public class LAvue extends EV3UltrasonicSensor{

	/**
	 * @author mathieu
	 * @param port port ou est branche le capteur ultrason
	 * Constructeur de la classe LAvue
	 */
	public LAvue(Port port){
		super(port);
	}

	/**
	 * @author mathieu
	 * @return retourne la distance à laquelle est situé l'obstacle le plus proche
	 * Objectif : Recuperer la distance a laquelle est l'objet le plus proche
	 * Utilisee par posePaletCamp(Properties prop,Vehicule vehicule, CaptTactile capt,char cotes,Boussole boussole),
	 * 				AvancerTantQue(double f, LAvue vue), PALET(),
	 * 				DetectionDunObjetG(CaptTactile capt,char cotes),DetectionDunObjetD(CaptTactile capt,char cotes)
	 */
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
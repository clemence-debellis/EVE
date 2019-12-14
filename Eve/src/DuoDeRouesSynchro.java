import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class DuoDeRouesSynchro{

	private EV3LargeRegulatedMotor mLeftMotor;
	private EV3LargeRegulatedMotor mRightMotor;
	public int SPEED =00;

	/**
	 * @author mathieu
	 * Constructeur de la classe DuoDeRoues
	 */
	public DuoDeRouesSynchro(){
		mLeftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		mRightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
		mLeftMotor.synchronizeWith(new RegulatedMotor[]{mRightMotor});

		mLeftMotor.setSpeed(SPEED);
		mRightMotor.setSpeed(SPEED);
	}

	/**
	 * @author mathieu
	 * @param vitesse Nouvelles vitesse des moteurs
	 * Objectif: Parametre une nouvelle vitesse pour les deux moteurs
	 * Utilisee par: trouverNord (DuoDeRouesSynchro roues), trouverEst (DuoDeRouesSynchro roues),trouverOuest(DuoDeRouesSynchro roues),trouverSud(DuoDeRouesSynchro roues)
	 * 				avancerJusquePalet(DuoDeRouesSynchro roues,char cotes), posePaletCamp(Properties prop,Vehicule vehicule, CaptTactile capt,char cotes,Boussole boussole)
	 * 				AvancerTantQue(double f, LAvue vue), PALET(),DetectionDunObjetG(CaptTactile capt,char cotes),
	 * 				DetectionDunObjetD(CaptTactile capt,char cotes)
	 * Utilise la methode predefinie setSpeed(int speed)
	 */
	public void setspeed(int vitesse) {
		mLeftMotor.setSpeed(vitesse);
		mRightMotor.setSpeed(vitesse);
	}

	/**
	 * @author mathieu
	 * Objectif: Faire tourner les moteurs sans fin afin de faire avancer le robot sans condition d'arret
	 * Utilisee par: avancerJusquePalet(DuoDeRouesSynchro roues,char cotes), posePaletCamp(Properties prop,Vehicule vehicule, CaptTactile capt,char cotes,Boussole boussole)
	 * 				AvancerTantQue(double f, LAvue vue),AvancerTantQue(boolean b), PALET()
	 */
	public void avancer(){
		mLeftMotor.startSynchronization();
		mLeftMotor.forward();
		mRightMotor.forward();
		mLeftMotor.endSynchronization();
	}

	/**
	 * @author mathieu
	 * Objectif: Arreter les moteurs des roues au meme moment
	 * Utilisee par: avancerJusquePalet(DuoDeRouesSynchro roues,char cotes), posePaletCamp(Properties prop,Vehicule vehicule, CaptTactile capt,char cotes,Boussole boussole)
	 * 				AvancerTantQue(double f, LAvue vue), PALET()
	 */
	public void stop(){
		mLeftMotor.startSynchronization();
		mLeftMotor.stop();
		mRightMotor.stop();
		mLeftMotor.endSynchronization();
	}
	/**
	 * @author mathieu
	 * Objectif: Faire reculer les véhicules sans fin et de maniere synchronisee
	 * Utilisee par: posePaletCamp(Properties prop,Vehicule vehicule, CaptTactile capt,char cotes,Boussole boussole)
	 */
	public void reculer(){
		mLeftMotor.startSynchronization();
		mLeftMotor.backward();
		mRightMotor.backward();
		mLeftMotor.endSynchronization();
	}

	/**
	 * @author mathieu
	 * @param temps temps que prendra le recul
	 * Objectif: Reculer durant un temps donne
	 * Utilisee par: DetectionDunObjetD(CaptTactile capt,char cotes),DetectionDunObjetG(CaptTactile capt,char cotes)
	 */
	public void reculerTemps(int temps){
		mLeftMotor.startSynchronization();
		mLeftMotor.backward();
		mRightMotor.backward();
		mLeftMotor.endSynchronization();

		Delay.msDelay(temps);

		mLeftMotor.startSynchronization();
		mLeftMotor.stop();
		mRightMotor.stop();
		mLeftMotor.endSynchronization();
	}

	/**
	 * @author mathieu
	 * @param angle angle duquel la rotation va être faite
	 * @param temps temps de rotation
	 * Objectif: Tourner selon un angle donne pendant un temps donne vers la gauche avec les roues synchronisées
	 * Utilisee par: posePaletCamp(Properties prop,Vehicule vehicule, CaptTactile capt,char cotes,Boussole boussole),
	 * 				DetectionDunObjetG(CaptTactile capt,char cotes)
	 */
	public void rotateAsynchG(int angle,int temps) {

		mLeftMotor.startSynchronization();
		mLeftMotor.rotate(angle, true);
		mRightMotor.rotate(-angle, true);
		mLeftMotor.endSynchronization();

		Delay.msDelay(temps);

		mLeftMotor.startSynchronization();
		mLeftMotor.stop();
		mRightMotor.stop();
		mLeftMotor.endSynchronization();
	}

	/**
	 * @author mathieu
	 * @param angle angle duquel la rotation va être faite
	 * @param temps temps de rotation
	 * Objectif: Tourner selon un angle donne pendant un temps donne vers la droite avec les roues synchronisées
	 * Utilisee par: DetectionDunObjetD(CaptTactile capt,char cotes)
	 */
	public void rotateAsynchD(int angle,int temps) {

		mLeftMotor.startSynchronization();
		mLeftMotor.rotate(-angle, true);
		mRightMotor.rotate(angle, true);
		mLeftMotor.endSynchronization();

		Delay.msDelay(temps);

		mLeftMotor.startSynchronization();
		mLeftMotor.stop();
		mRightMotor.stop();
		mLeftMotor.endSynchronization();

	}
}
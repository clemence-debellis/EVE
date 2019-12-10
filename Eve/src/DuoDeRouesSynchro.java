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
	public DuoDeRouesSynchro()
	{
		mLeftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		mRightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
		mLeftMotor.synchronizeWith(new RegulatedMotor[]{mRightMotor});

		mLeftMotor.setSpeed(SPEED);
		mRightMotor.setSpeed(SPEED);
	}

	/**
	 * @author mathieu
	 * @param vitesse Nouvelles vitesse des moteurs
	 * Parametre une nouvelle vitesse pour les deux moteurs
	 */
	public void setspeed(int vitesse) {
		mLeftMotor.setSpeed(vitesse);
		mRightMotor.setSpeed(vitesse);
	}

	/**
	 * @author mathieu
	 * Fait avancer les moteurs sans fin
	 */
	public void avancer(){
		mLeftMotor.startSynchronization();
		mLeftMotor.forward();
		mRightMotor.forward();
		mLeftMotor.endSynchronization();
	}

	/**
	 * @author mathieu
	 * Arrete les moteurs
	 */
	public void stop(){
		mLeftMotor.startSynchronization();
		mLeftMotor.stop();
		mRightMotor.stop();
		mLeftMotor.endSynchronization();
	}
	/**
	 * @author mathieu
	 * Fais reculer les véhicules sans fin
	 */
	public void reculer()
	{
		mLeftMotor.startSynchronization();
		mLeftMotor.backward();
		mRightMotor.backward();
		mLeftMotor.endSynchronization();
	}

	/**
	 * @author mathieu
	 * @param temps temps que prendra le recul
	 * Recul durant un temps donne
	 */
	public void reculerTemps(int temps)
	{
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
	 * Tourne sur un angle donne pendant un temps donne vers la gauche, roues synchronisées
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
	 * Tourne sur un angle donne pendant un temps donne vers la droite, roues synchronisées
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
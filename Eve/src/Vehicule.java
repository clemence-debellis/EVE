import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Vehicule {
	
	DuoDeRouesSynchro roues;
	LAvue vue;

	/**
	 * @author mathieu
	 * Constructeur de la classe vehicule
	 */
	public Vehicule() {
		roues = new DuoDeRouesSynchro();
		vue = new LAvue(SensorPort.S3);
	}
	
	/**
	 * @author mathieu
	 * @param distance: distance entre l'obstacle et le robot
	 * @param vue: capteur a ultrason
	 * Objectif: Faire avancer le robot jusqu'a ce que la distance separant le robot de l'obstacle soit egale a distance
	 * Utilisee par :DetectionDunObjetG(CaptTactile capt,char cotes), DetectionDunObjetD(CaptTactile capt,char cotes),
	 * 				recupPremierPalet(Vehicule vehicule,char cotes,Boussole boussole)
	 * Utilise : setspeed(int vitesse), getDistance(), avancer(), stop()
	 */
	public void AvancerTantQue(double distance, LAvue vue){

		roues.setspeed(300);
		while (vue.getDistance()>= distance || vue.getDistance()<=0.2){
			roues.avancer();
		}
		roues.stop();
	}

	/**
	 * @author mathieu
	 * @return true si l'objet detecte est un palet, false sinon
	 * Objectif: Donner l'information sur ce qu'il a detecter (un palet ou pas un palet)
	 * Utilisee par: DetectionDunObjetG(CaptTactile capt,char cotes) et DetectionDunObjetD(CaptTactile capt,char cotes)
	 * Utilise : getDistance(), setspeed(int vitesse), avancer(), stop(); 
	 * 
	 */
	public boolean PALET(){
		float ancienneD = vue.getDistance();
		System.out.println(ancienneD);
		Delay.msDelay(1000);
		roues.setspeed(200);
		roues.avancer();
		Delay.msDelay(1500);
		roues.stop();
		float nouvelleD = vue.getDistance();
		System.out.println(nouvelleD);
		Delay.msDelay(1000);

		if((nouvelleD - ancienneD) > 0) 
			return true;

		return false;
	}
	
	/**
	 * @author mathieu
	 * @param capt : capteur tactile
	 * @param cotes : caractere indiquant le cote de la table ou a debute le robot
	 * @return true s'il a recupere un palet, false sinon
	 * Objectif: Dire s'il a recuperer ou non le palet sachant qu'il a debute la partie a gauche de la table de jeu
	 * Utilise: setspeed(int vitesse), rotateAsynchG(int angle, int temps), getDistance(), AvancerTantQue(double distance, LAvue vue),
	 * 			PALET(), avancerJusquePalet(DuosDeRouesSynchro roues, char cotes), teculerTemps(int temps), stop()
	 */
	
	public boolean DetectionDunObjetG(CaptTactile capt,char cotes) {
		roues.setspeed(100);
		int j = 0;
		boolean b;

			roues.setspeed(40);
			roues.rotateAsynchG(1000,2000);
			
			while(j==0) {
			if(vue.getDistance() < 1){
				roues.stop();
				roues.rotateAsynchG(-50,500);
				Delay.msDelay(1000);
				this.AvancerTantQue(0.34,vue);
				j++;
			}
			}
			j=0;
			b = this.PALET();
			if(b) {
				capt.avancerJusquePalet(roues,cotes);
			}
			
			else {
				roues.reculerTemps(2000);
				Delay.msDelay(2000);
				roues.stop();
			}
		return (b);
	}
	
	/**
	 * @author mathieu
	 * @param capt : capteur tactile
	 * @param cotes : caractere indiquant le cote de la table ou a debute le robot
	 * @return true s'il a recupere un palet, false sinon
	 * Objectif: Dire s'il a recuperer ou non le palet sachant qu'il a debute la partie a gauche de la table de jeu
	 * Utilise: setspeed(int vitesse), rotateAsynchD(int angle, int temps), getDistance(), AvancerTantQue(double distance, LAvue vue),
	 * 			PALET(), avancerJusquePalet(DuosDeRouesSynchro roues, char cotes), teculerTemps(int temps), stop()
	 */

	public boolean DetectionDunObjetD(CaptTactile capt,char cotes) {
		roues.setspeed(100);
		int j = 0;
		boolean b;

			roues.setspeed(40);
			roues.rotateAsynchD(1000,2000);
			
			while(j==0) {
			if(vue.getDistance() < 1){
				roues.stop();
				roues.rotateAsynchD(-50,500);
				Delay.msDelay(1000);
				this.AvancerTantQue(0.34,vue);
				j++;
			}
			}
			j=0;
			b = this.PALET();
			if(b) {
				return capt.avancerJusquePalet(roues,cotes);
			}
			
			else {
				roues.reculerTemps(2000);
				Delay.msDelay(2000);
				roues.stop();
				return false;
			}
	}

}



public class Boussole {
	int orientation;
	int ouest=0;	//tourner de 90 pour robot = 200  
	int nord=90;	//tourner de 180 pour robot = 400 
	int est=180;	//tourner de 270 pour robot = 600 
	int sud=270;	//tourner de 360 pour robot = 800 
	public Boussole (int orientation) {
		this.orientation=orientation;
	}

	/**
	 * @author clémence
	 * Constructeur de la classe Boussole
	 */
	public Boussole () {
		orientation=0;
	}

	/**
	 * @author clémence
	 * @return l'orientation dans lequel il se trouve
	 * Permet de savoir l'angle dans lequel se trouve le robot
	 */
	public int getAngle () {
		return orientation;
	}

	/**
	 * @author clémence
	 * @param angle angle de la nouvelle position
	 * Entre la nouvelle position que doit avoir le robot et s'oriente vers lui
	 */
	public void orienter (int angle) { 
		if(angle<0) {
			orientation=((360+orientation)+angle)%360;
		}
		else{
			orientation=(orientation+angle)%360;
		}
	}

	/**
	 * @author clémence
	 * @param angle
	 * @return retourne la différence entre l'ancien angle et le nouvel angle
	 * Donne à orientation un nouvel angle
	 */
	public int nouvelAngle (int angle) { 
		int orien2=orientation;
		orientation=angle;
		int res = orien2-angle;
		return -res;
	}

	/**
	 * @author clémence
	 * @param t représente les deux roues qui vont tourner
	 * Oriente le robot vers le nord
	 */
	public void trouverNord (DuoDeRouesSynchro t) {
		int res = nouvelAngle(nord);
		t.setspeed(350);
		t.rotateAsynchG(-res*200/90,2000);
	}

	/**
	 * @author clémence
	 * @param t représente les deux roues qui vont tourner
	 * Oriente le robot vers le sud
	 */
	public void trouverSud (DuoDeRouesSynchro t) {
		int res = nouvelAngle(sud);
		t.setspeed(350);
		t.rotateAsynchG(-res*200/90,2000);
	}

	/**
	 * @author clémence
	 * @param t représente les deux roues qui vont tourner
	 * Oriente le robot vers l'est
	 */
	public void trouverEst (DuoDeRouesSynchro t) {
		int res = nouvelAngle(est);
		t.setspeed(350);
		t.rotateAsynchG(-res*200/90,2000);
	}

	/**
	 * @author clémence
	 * @param t représente les deux roues qui vont tourner
	 * Oriente le robot vers l'ouest
	 */
	public void trouverOuest (DuoDeRouesSynchro t) {
		int res = nouvelAngle(ouest);
		t.setspeed(350);
		t.rotateAsynchG(-res*200/90,2000);
	}
}
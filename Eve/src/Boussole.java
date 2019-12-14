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
	 * @author clemence
	 * Constructeur de la classe Boussole
	 */
	public Boussole (){
		orientation=0;
	}

	/**
	 * @author clemence
	 * @param angle angle de la nouvelle position
	 * Objectif de la methode : Entre la nouvelle position que doit avoir le robot et s'oriente vers celle-ci
	 */
	public void orienter (int angle){ 
		if(angle<0) {
			orientation=((360+orientation)+angle)%360;
		}
		else{
			orientation=(orientation+angle)%360;
		}
	}

	/**
	 * @author clemence
	 * @param angle Position actulle
	 * @return Retourne la difference entre l'orientation actuelle et le nouvel angle entre en parametre
	 * Objectif : actualiser l'orientation et retourner de combien il a besoin de tourner pour se trouver d'un l'angle mis parametre
	 * Utilisee par : trouverNord(DuoDeRouesSynchro roues), trouverSud(DuoDeRouesSynchro roues), trouverEst(DuoDeRouesSynchro roues), trouverOuest(DuoDeRouesSynchro roues)
	 */
	public int nouvelAngle(int angle){ 
		int orien2=orientation;
		orientation=angle;
		int res = orien2-angle;
		return -res;
	}

	/**
	 * @author clemence
	 * @param roues les deux-roues synchronisee
	 * Objectif : quelle que soit l'orientation du robot, l'oriente vers le nord
	 * Utilisee par : recupPremierPalet(Vehicule vehicule,char cotes,Boussole boussole)
	 * Utilise : nouvelAngle(int angle),setspeed(int vitesse),rotateAsynchG(int angle,int temps)
	 */
	public void trouverNord (DuoDeRouesSynchro roues){
		int res = nouvelAngle(nord);
		roues.setspeed(350);
		roues.rotateAsynchG(-res*200/90,2000);
	}

	/**
	 * @author clemence
	 * @param roues les deux-roues synchronisee
	 * Objectif : quelle que soit l'orientation du robot, l'oriente vers le sud
	 * Utilisee par : recupPremierPalet(Vehicule vehicule,char cotes,Boussole boussole)
	 * Utilise : nouvelAngle(int angle),setspeed(int vitesse),rotateAsynchG(int angle,int temps) 
	 */
	public void trouverSud (DuoDeRouesSynchro roues){
		int res = nouvelAngle(sud);
		roues.setspeed(350);
		roues.rotateAsynchG(-res*200/90,2000);
	}

	/**
	 * @author clemence
	 * @param roues les deux-roues synchronisee
	 * Objectif : quelle que soit l'orientation du robot, l'oriente vers l'est
	 * Utilisee par : recupPremierPalet(Vehicule vehicule,char cotes,Boussole boussole)
	 * Utilise : nouvelAngle(int angle),setspeed(int vitesse),rotateAsynchG(int angle,int temps) 
	 */
	public void trouverEst (DuoDeRouesSynchro roues){
		int res = nouvelAngle(est);
		roues.setspeed(350);
		roues.rotateAsynchG(-res*200/90,2000);
	}

	/**
	 * @author clemence
	 * @param roues les deux-roues synchronisee
	 * Objectif : quelle que soit l'orientation du robot, l'oriente vers l'ouest
	 * Utilisee par : recupPremierPalet(Vehicule vehicule,char cotes,Boussole boussole)
	 * Utilise : nouvelAngle(int angle),setspeed(int vitesse),rotateAsynchG(int angle,int temps) 
	 */
	public void trouverOuest (DuoDeRouesSynchro roues){
		int res = nouvelAngle(ouest);
		roues.setspeed(350);
		roues.rotateAsynchG(-res*200/90,2000);
	}
}
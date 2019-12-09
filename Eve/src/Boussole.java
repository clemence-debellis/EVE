import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.LocalEV3;

public class Boussole {
	int orientation;
	int ouest=0;	//tourner de 90 pour robot = 200  
	int nord=90;	//tourner de 180 pour robot = 400 
	int est=180;	//tourner de 270 pour robot = 600 
	int sud=270;	//tourner de 360 pour robot = 800 
	public Boussole (int orientation) {
		this.orientation=orientation;
	}
	
	public Boussole () {
		orientation=0;
	}
	
	public int getAngle () {
		return orientation;
	}
	
	public void orienter (int angle) { // additionne les 2 angles 
		if(angle<0) {					// par exemple, s'il est à 90 et qu'on veut le faire tourner de 90, l'orientation va se mettre à 180
			orientation=((360+orientation)+angle)%360;
		}
		else{
			orientation=(orientation+angle)%360;
		}
	}
	
	public int nouvelAngle (int angle) { // met la valeur de l'ancien angle à la valeur du nouvel angle
		int orien2=orientation;			// et retourne la valeur de l'écart entre l'angle de base et le nouvel angle
		orientation=angle;
		int res = orien2-angle;
		return -res;
	}
	
	public void trouverNord (Avancer t) {
		int res = nouvelAngle(nord);
		t.setspeed(350);
		t.rotateAsynchD(-res*200/90,2000);
		}
	
	public void trouverSud (Avancer t) {
		int res = nouvelAngle(sud);
		t.setspeed(350);
		t.rotateAsynchG(-res*200/90,2000);
	}
	
	public void trouverEst (Avancer t) {
		int res = nouvelAngle(est);
		t.setspeed(350);
		t.rotateAsynchG(-res*200/90,2000);
	}
	
	public void trouverOuest (Avancer t) {
		int res = nouvelAngle(ouest);
		t.setspeed(350);
		t.rotateAsynchG(-res*200/90,2000);
	}
	
	// vitesse = distance / temps
	// distance = vitesse * temps 

}
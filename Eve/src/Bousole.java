import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.LocalEV3;

public class Bousole {
	int orientation;
	int ouest=0;	//tourner de 90 pour robot = 200  
	int nord=90;	//tourner de 180 pour robot = 400 
	int est=180;	//tourner de 270 pour robot = 600 
	int sud=270;	//tourner de 360 pour robot = 800 
	int SPEED=350;
	
	Avancer roues = new Avancer(MotorPort.B, MotorPort.C);
	private static Port port=LocalEV3.get().getPort("S4");
	private static Port port2=LocalEV3.get().getPort("S3");
	EV3ColorSensor colorSensor = new EV3ColorSensor(port);
	EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(port2);
	
	public Bousole (int orientation) {
		this.orientation=orientation;
	}
	
	public Bousole () {
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
		int orien2=orientation;			//retourne la différence entre l'angle de base et le nouvel angle 
		orientation=angle;				// par exemple, s'il était à 0 et qu'il passe à 90, il met orientation à 90 et retourne 90
		int res = orien2-angle;
		return -res;
	}
	
	public void trouverNord () {
		int res = nouvelAngle(nord);
		roues.setspeed(SPEED);
		roues.rotateAsynch(-res*200/90,2000);
		}
	
	public void trouverSud () {
		int res = nouvelAngle(sud);
		roues.setspeed(SPEED);
		roues.rotateAsynch(-res*200/90,2000);
	}
	
	public void trouverEst () {
		int res = nouvelAngle(est);
		roues.setspeed(SPEED);
		roues.rotateAsynch(-res*200/90,2000);
	}
	
	public void trouverOuest () {
		int res = nouvelAngle(ouest);
		roues.setspeed(SPEED);
		roues.rotateAsynch(-res*200/90,2000);
	}
	
}

	
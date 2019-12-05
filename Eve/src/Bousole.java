import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.BrickFinder;

public class Bousole {
	int orientation;
	int ouest=0;	//tourner de 90 pour robot = 400  --> 90+310
	int nord=90;	//tourner de 180 pour robot = 800 --> 180+2*310
	int est=180;	//tourner de 270 pour robot = 1200 --> 270+3*310
	int sud=270;	//tourner de 360 pour robot = 1600 --> 360+4*310
	int SPEED=500;
	
	RegulatedMotor m = new EV3LargeRegulatedMotor (MotorPort.B);
	RegulatedMotor m1 = new EV3LargeRegulatedMotor (MotorPort.C);
	
	public Bousole (int orientation) {
		this.orientation=orientation;
	}
	
	public Bousole () {
		orientation=0;
	}
	
	public void orienter (int angle) { // additionne les 2 angles 
		if(angle<0) {
			orientation=((360+orientation)+angle)%360;
		}
		else{
			orientation=(orientation+angle)%360;
		}
	}
	
	public int nouvelAngle (int angle) { // met la valeur de l'ancien angle à la valeur du nouvel angle
		int orien2=orientation;			//retourne la différence entre l'angle de base et le nouvel angle 
		orientation=angle;
		int res = orien2-angle;
		return -res;
	}
	
	public void trouverNord () {
		int res = nouvelAngle(nord);
		m.setSpeed(SPEED);
		m.rotate(res);
	}
	
}

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.hardware.BrickFinder;

public class TestBousole {
	public static void main(String [] args) {
		
		//Avancer roues = new Avancer (MotorPort.B, MotorPort.C);
		
		Bousole B = new Bousole(0);
		//System.out.println(B.nouvelAngle(270));
		
		//B.orienter(90);
		//System.out.println(B.orientation);
		
		B.trouverEst();
		System.out.println(B.getAngle());
		Delay.msDelay(2000);
		B.trouverNord();
		System.out.println(B.getAngle());
		Delay.msDelay(2000);
		B.trouverSud();
		System.out.println(B.getAngle());
		Delay.msDelay(2000);
		B.trouverOuest();
		System.out.println(B.getAngle());
		Delay.msDelay(2000);
		
		//roues.setspeed(350);
		//roues.rotateAsynch(800,3000);
	}
}

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.BrickFinder;

public class TestBousole {
	public static void main(String [] args) {
		
		RegulatedMotor m = new EV3LargeRegulatedMotor (MotorPort.B);
		RegulatedMotor m1 = new EV3LargeRegulatedMotor (MotorPort.C);
		
		//Bousole B = new Bousole(0);
		//System.out.println(B.nouvelAngle(270));
		
		//B.orienter(90);
		//System.out.println(B.orientation);
		m.setSpeed(200);
		m.rotate(400);
		//B.trouverNord();
		//System.out.println(B.orientation);
	}
}

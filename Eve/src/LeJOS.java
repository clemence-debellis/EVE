import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;
import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.*;


public class LeJOS {

	public static void main(String[] args) {
	//	GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
		//g.drawString("Hello world", 0,0, GraphicsLCD.VCENTER | GraphicsLCD.LEFT);
	//	Delay.msDelay(5000);
		
		RegulatedMotor m = new EV3LargeRegulatedMotor (MotorPort.B);
		RegulatedMotor m1 = new EV3LargeRegulatedMotor (MotorPort.C);
		
		m.forward();
		m1.forward();
		Delay.msDelay(1000);
		m1.stop();
		m.stop();
		m.setSpeed(200);
		m.rotate(1600);
		//m.forward();
		//m1.forward();
		Delay.msDelay(2000);
		//m.backward();
		//m1.backward();
		//Delay.msDelay(2000);
		
		//EncoderMotor em1 = new UnregulatedMotor (MotorPort.B);
		//EncoderMotor em = new UnregulatedMotor (MotorPort.C);
		//em.setSpeed(200);
		//em1.setSpeed(200);
		//em1.forward();
		//em.forward();
		//Delay.msDelay(2000);
		//em1.stop();
		//em.stop();
		//em.rotate(400);
		//em.setPower(60);
		//em1.setPower(60);
		//em1.forward();
		//em.forward();
		//Delay.msDelay(2000);
		//em1.stop();
		//em.stop();
		//em.rotate(400);

	}

}

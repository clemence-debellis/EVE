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
		
		RegulatedMotor m = new EV3LargeRegulatedMotor (MotorPort.D);
		RegulatedMotor m1 = new EV3LargeRegulatedMotor (MotorPort.A);
		m.rotate(400);
		//m1.rotate(-400);
		
		//EncoderMotor em1 = new UnregulatedMotor (MotorPort.A);
		//EncoderMotor em = new UnregulatedMotor (MotorPort.D);
		m.setSpeed(200);
		m1.setSpeed(200);
		m1.forward();
		m.forward();
		Delay.msDelay(2000);
		m1.stop();
		m.stop();
		m.rotate(400);
		//m.setPower(60);
		//m1.setPower(60);
		m1.forward();
		m.forward();
		Delay.msDelay(2000);
		m1.stop();
		m.stop();
		m.rotate(400);

	}

}

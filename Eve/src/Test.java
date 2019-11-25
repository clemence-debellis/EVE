import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import lejos.utility.Delay;

public class Test {
	
	
	public static void main (String [] args) throws IOException {
		
		InputStream input= new FileInputStream("Couleurs");
		Properties colore = new Properties();
		colore.load(input);	
		/*Delay.msDelay(3000);
		System.out.println("Green"+colore.getProperty("Green"));
		Delay.msDelay(3000);
		System.out.println("Yellow"+colore.getProperty("Yellow"));
		Delay.msDelay(3000);
		System.out.println("Blue"+colore.getProperty("Blue"));
		Delay.msDelay(3000);
		System.out.println("Red"+colore.getProperty("Red"));
		Delay.msDelay(3000);
		System.out.println("White"+colore.getProperty("White"));
		Delay.msDelay(3000);
		System.out.println("Black"+colore.getProperty("Black"));
		Delay.msDelay(3000);
		System.out.println("Grey"+colore.getProperty("Grey"));	*/
		
		//TestColor.colorimetrie();	
			

			
			ArrayList couleur =new ArrayList();
			int i=0;	
		
		
			while(Button.ENTER.isUp()) {
				float[] tab= TestColor.getEchant();	
				couleur.add(TestColor.getColor(colore,tab));
				Delay.msDelay(2000);

				//System.out.println(couleur.get(i));
				//i++;
			}
			}
	}
	


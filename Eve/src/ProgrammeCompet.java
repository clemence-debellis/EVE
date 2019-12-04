import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class ProgrammeCompet {

	
	static public void main(String[]args) throws IOException  {
		InputStream input= new FileInputStream("Couleurs");
		Properties colore = new Properties();
		colore.load(input);
		
	/*	private Port port =LocalEV3.get().getPort("S4");
		private EV3ColorSensor colorSensor = new EV3ColorSensor(port);*/
		
		CaptTactile captTact=new CaptTactile();
		//Port port = new Port(LocalEV3.get().getPort("S4"));
		//EV3ColorSensor lePort = new ;
		TestColor captCouleur = new TestColor(SensorPort.S4, new EV3ColorSensor(SensorPort.S4));
		Test t=new Test();
		//	EV3UltrasonicSensor portYeux = new EV3UltrasonicSensor(LocalEV3.get().getPort("S3"));
		LAvue yeux=new LAvue(SensorPort.S3);
		//Avancer coupleRoues =new Avancer(MotorPort.B, MotorPort.C);
		captTact.recupPremierPalet(colore, t, captCouleur, captTact, yeux);
		//captCouleur.posePaletCamp(colore, t, captTact, yeux);
		//captTact.avancerJusquePalet(t.roues);
		
		
		
	}
}

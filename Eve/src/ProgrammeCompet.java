import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;

public class ProgrammeCompet {

	
	static public void main(String[]args) throws IOException  {
		InputStream input= new FileInputStream("Couleurs");
		Properties colore = new Properties();
		colore.load(input);
		
	/*	private Port port =LocalEV3.get().getPort("S4");
		private EV3ColorSensor colorSensor = new EV3ColorSensor(port);*/
		
		CaptTactile captTact=new CaptTactile();

		EV3ColorSensor lePort = new EV3ColorSensor(LocalEV3.get().getPort("S4"));
		TestColor captCouleur = new TestColor(LocalEV3.get().getPort("S4"), lePort);
		Test t = new Test();
		//Avancer coupleRoues =new Avancer(MotorPort.B, MotorPort.C);
		captTact.recupPremierPalet(colore ,t,captCouleur, captTact);
		
		
		
	}
}
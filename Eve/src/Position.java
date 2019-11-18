import java.io.FileOutputStream;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class Position {

	private String[] coul;
	private int angle;
	private static EV3ColorSensor sensor;

	public void TabCouleur() {
		
		sensor = new EV3ColorSensor(SensorPort.S2);
		coul=new String[60];
		int i=0;
		while(Button.ENTER.isUp()) {
			colors.store(new FileOutputStream("colors"));
			
		}
	}
}

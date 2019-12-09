import java.util.Properties;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;

import java.io.*;

public class TestColor {


	private Port port;//=LocalEV3.get().getPort("S4");
	private static EV3ColorSensor colorSensor;//= new EV3ColorSensor(port);

	public TestColor(Port port, EV3ColorSensor capteurCouleur) {
		this.port = port;
		colorSensor=capteurCouleur;
	}

	public static boolean goMessage() {

		GraphicsLCD g = LocalEV3.get().getGraphicsLCD();
		g.clear();
		g.drawString("Color Sensor", 5, 0, 0);
		g.setFont(Font.getSmallFont());

		g.drawString("The code for this sample     ", 2, 20, 0);
		g.drawString("shows how to work with the ", 2, 30, 0);
		g.drawString("Color Sensor ", 2, 40, 0);
		g.drawString("To run the ", 2, 60, 0);
		g.drawString("code one needs an EV3 ", 2, 70, 0);
		g.drawString("brick with a EV3 color sensor", 2, 80, 0); 
		g.drawString("attached to port 4.", 2, 90, 0);

		// Quit GUI button:
		g.setFont(Font.getSmallFont()); // can also get specific size using Font.getFont()
		int y_quit = 100;
		int width_quit = 45;
		int height_quit = width_quit/2;
		int arc_diam = 6;
		g.drawString("QUIT", 9, y_quit+7, 0);
		g.drawLine(0, y_quit,  45, y_quit); // top line
		g.drawLine(0, y_quit,  0, y_quit+height_quit-arc_diam/2); // left line
		g.drawLine(width_quit, y_quit,  width_quit, y_quit+height_quit/2); // right line
		g.drawLine(0+arc_diam/2, y_quit+height_quit,  width_quit-10, y_quit+height_quit); // bottom line
		g.drawLine(width_quit-10, y_quit+height_quit, width_quit, y_quit+height_quit/2); // diagonal
		g.drawArc(0, y_quit+height_quit-arc_diam, arc_diam, arc_diam, 180, 90);

		// Enter GUI button:
		g.fillRect(width_quit+10, y_quit, height_quit, height_quit);
		g.drawString("GO", width_quit+15, y_quit+7, 0,true);

		Button.waitForAnyPress();
		if(Button.ESCAPE.isDown()) {
			return false;
		}
		else {
			g.clear();
			return true;
		}
	}
	public static void colorimetrie() throws IOException {
		OutputStream fichier= new FileOutputStream("Couleurs");
		Properties couleur= new Properties();
		boolean again = true;

		if (!goMessage()) System.exit(0);

		//colorSensor = new EV3ColorSensor(port);
		SampleProvider average = new MeanFilter(colorSensor.getRGBMode(), 1);
		colorSensor.setFloodlight(Color.WHITE);

		System.out.println("Press enter to calibrate white...");
		Button.ENTER.waitForPressAndRelease();
		float[] white = new float[average.sampleSize()];
		average.fetchSample(white, 0);
		couleur.setProperty("White", white[0]+","+white[1]+","+white[2]);

		System.out.println("Press enter to calibrate grey...");
		Button.ENTER.waitForPressAndRelease();
		float[] grey = new float[average.sampleSize()];
		average.fetchSample(grey, 0);
		couleur.setProperty("Grey", grey[0]+","+grey[1]+","+grey[2]);

		System.out.println("Press enter to calibrate blue...");
		Button.ENTER.waitForPressAndRelease();
		float[] blue = new float[average.sampleSize()];
		average.fetchSample(blue, 0);
		couleur.setProperty("Blue", blue[0]+","+blue[1]+","+blue[2]);

		System.out.println("Press enter to calibrate yellow...");
		Button.ENTER.waitForPressAndRelease();
		float[] yellow = new float[average.sampleSize()];
		average.fetchSample(yellow, 0);
		couleur.setProperty("Yellow", yellow[0]+","+yellow[1]+","+yellow[2]);

		System.out.println("Press enter to calibrate red...");
		Button.ENTER.waitForPressAndRelease();
		float[] red = new float[average.sampleSize()];
		average.fetchSample(red, 0);
		couleur.setProperty("Red", red[0]+","+red[1]+","+red[2]);

		System.out.println("Press enter to calibrate green...");
		Button.ENTER.waitForPressAndRelease();
		float[] green = new float[average.sampleSize()];
		average.fetchSample(green, 0);
		couleur.setProperty("Green", green[0]+","+green[1]+","+green[2]);

		System.out.println("Press enter to calibrate black...");
		Button.ENTER.waitForPressAndRelease();
		float[] black = new float[average.sampleSize()];
		average.fetchSample(black, 0);
		System.out.println("Black calibrated");
		couleur.setProperty("Black",black[0]+","+black[1]+","+black[2]);

		couleur.store(fichier,"comment");
	}

	public static float[] getEchant() {
		SampleProvider average = new MeanFilter(colorSensor.getRGBMode(), 1);
		float[]tableau=new float[average.sampleSize()];
		average.fetchSample(tableau, 0);
		return tableau;
	}

	public static String getColor(Properties prop, float[] sample) throws IOException {

		//LocalEV3.get().getPort("S4");
		//colorSensor = new EV3ColorSensor(port);
		double minscal = Double.MAX_VALUE;
		String color = "";


		String[] tabBlue = prop.getProperty("Blue").split(",");
		float blue[]= new float[3];
		blue[0]=Float.parseFloat(tabBlue[0]);
		blue[1]=Float.parseFloat(tabBlue[1]);
		blue[2]=Float.parseFloat(tabBlue[2]);

		String[] tabRed = prop.getProperty("Red").split(",");
		float red[]= new float[3];
		red[0]=Float.parseFloat(tabRed[0]);
		red[1]=Float.parseFloat(tabRed[1]);
		red[2]=Float.parseFloat(tabRed[2]);

		String[] tabGreen = prop.getProperty("Green").split(",");
		float green[]= new float[3];
		green[0]=Float.parseFloat(tabGreen[0]);
		green[1]=Float.parseFloat(tabGreen[1]);
		green[2]=Float.parseFloat(tabGreen[2]);

		String[] tabYellow = prop.getProperty("Yellow").split(",");
		float yellow[]= new float[3];
		yellow[0]=Float.parseFloat(tabYellow[0]);
		yellow[1]=Float.parseFloat(tabYellow[1]);
		yellow[2]=Float.parseFloat(tabYellow[2]);

		String[] tabGrey = prop.getProperty("Grey").split(",");
		float grey[]= new float[3];
		grey[0]=Float.parseFloat(tabGrey[0]);
		grey[1]=Float.parseFloat(tabGrey[1]);
		grey[2]=Float.parseFloat(tabGrey[2]);

		String[] tabBlack = prop.getProperty("Black").split(",");
		float black[]= new float[3];
		black[0]=Float.parseFloat(tabBlack[0]);
		black[1]=Float.parseFloat(tabBlack[1]);
		black[2]=Float.parseFloat(tabBlack[2]);

		String[] tabWhite = prop.getProperty("White").split(",");
		float white[]= new float[3];
		white[0]=Float.parseFloat(tabWhite[0]);
		white[1]=Float.parseFloat(tabWhite[1]);
		white[2]=Float.parseFloat(tabWhite[2]);

		double scalaire = TestColor.scalaire(sample, blue);
		if (scalaire < minscal) {
			minscal = scalaire;
			color = "blue";
		}
		scalaire = TestColor.scalaire(sample, grey);
		if (scalaire < minscal) {
			minscal = scalaire;
			color = "grey";
		}

		scalaire = TestColor.scalaire(sample, white);
		if (scalaire < minscal) {
			minscal = scalaire;
			color = "white";
		}

		scalaire = TestColor.scalaire(sample, red);
		if (scalaire < minscal) {
			minscal = scalaire;
			color = "red";
		}
		scalaire = TestColor.scalaire(sample, yellow);
		if (scalaire < minscal) {
			minscal = scalaire;
			color = "yellow";
		}

		scalaire = TestColor.scalaire(sample, green);
		if (scalaire < minscal) {
			minscal = scalaire;
			color = "green";
		}

		scalaire = TestColor.scalaire(sample, black);
		if (scalaire < minscal) {
			minscal = scalaire;
			color = "black";
		}
		return color;
	}
	public static double scalaire(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}
	//Adapter avec avancer

	public void posePaletCamp(Properties prop,Test couple, CaptTactile capt, LAvue yeux, char cotes,Boussole boussole) throws IOException{

		float[] tab= TestColor.getEchant();	
		String couleur = TestColor.getColor(prop,tab);
		//	LAvue yeux = new LAvue(portYeux);
		couple.roues.avancer();
		while(couleur.equals("white")==false && Button.ENTER.isUp()) {
			System.out.println(couleur);
			Delay.msDelay(50);

			if(yeux.getDistance()<=0.2) {
				float vue1= yeux.getDistance();
				couple.roues.stop();
				Delay.msDelay(3000);
				float vue2 =yeux.getDistance();
				if (vue1-vue2<=0.02)
					//il faut trouver l'est ou l'ouest avec la boussole au lieu de le faire tourner comme ca
					couple.roues.rotateAsynchG(-90,1000);
				Delay.msDelay(1000);
				couple.roues.avancer();
			}

			tab= TestColor.getEchant();	
			couleur = TestColor.getColor(prop,tab);

		}

		couple.roues.stop();
		capt.OuvertureDesPinces();
		couple.roues.setspeed(200);
		couple.roues.reculer();
		Delay.msDelay(1000);
		couple.roues.stop();
		capt.FermetureDesPinces();
		
		if (cotes=='d') {
			boussole.trouverEst(couple.roues);
		}
		
		else {
			boussole.trouverOuest(couple.roues);
		}
	}

}
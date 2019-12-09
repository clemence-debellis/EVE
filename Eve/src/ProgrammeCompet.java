
import java.awt.RenderingHints.Key;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;

import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class ProgrammeCompet {


	static public void main(String[]args) throws IOException  {
		InputStream input= new FileInputStream("Couleurs");
		Properties colore = new Properties();
		colore.load(input);

		/*	private Port port =LocalEV3.get().getPort("S4");
		private EV3ColorSensor colorSensor = new EV3ColorSensor(port);*/

		CaptTactile captTact=new CaptTactile();
		Boussole boussole = new Boussole();
		//Port port = new Port(LocalEV3.get().getPort("S4"));
		//EV3ColorSensor lePort = new ;
		TestColor captCouleur = new TestColor(SensorPort.S4, new EV3ColorSensor(SensorPort.S4));
		Test vehicule = new Test();
		//	EV3UltrasonicSensor portYeux = new EV3UltrasonicSensor(LocalEV3.get().getPort("S3"));
		char cotes = 'd';
		Button.ESCAPE.waitForPress();
		int leMatchCommence = 0;
		int CompteurDePalet = 0;
		int CompteurDeMur = 0;

		if(leMatchCommence==0) {
			captTact.recupPremierPalet(colore, vehicule, captCouleur, captTact, vehicule.vue,cotes,boussole);
			captCouleur.posePaletCamp(colore, vehicule, captTact,vehicule.vue,cotes,boussole);
			//captTact.avancerJusquePalet(vehicule.roues);je sais pas pk y avait ca ici
			//ici la boussole permet au vehicule de  se remettre dans le bon sens (soit derrière lui)
			leMatchCommence++;
			CompteurDePalet++;
		}
		boolean b;
		while(Button.ENTER.isUp()){

			while(CompteurDePalet<4) {

				if(cotes == 'd') {
					CompteurDeMur =0;
					while(CompteurDeMur<3 || vehicule.vue.getDistance()<=0.2 ) {
						b=vehicule.DetectionDunObjetD(captTact,cotes);
						if(b){
							CompteurDePalet++;
						}
						else {
							CompteurDeMur++;
						}
					}
					if (CompteurDeMur==3) {
						CompteurDeMur =0;
						while(CompteurDeMur<3 || vehicule.vue.getDistance()<=0.2 ) {
							b=vehicule.DetectionDunObjetG(captTact,cotes);
							if(b){
								CompteurDePalet++;
							}
							else {
								CompteurDeMur++;
							}
						}
					}
				}


				else{
					CompteurDeMur=0;
					while(CompteurDeMur<3 || vehicule.vue.getDistance()<=0.2 ) {
						b= vehicule.DetectionDunObjetG(captTact,cotes);
						if(b){
							CompteurDePalet++;
						}
						else {
							CompteurDeMur++;
						}
					}
					if (CompteurDeMur==3) {
						CompteurDeMur=0;
						while(CompteurDeMur<3 || vehicule.vue.getDistance()<=0.2 ) {
							b= vehicule.DetectionDunObjetG(captTact,cotes);
							if(b){
								CompteurDePalet++;
							}
							else {
								CompteurDeMur++;
							}
						}
					}
				}

			}
			CompteurDePalet=0;
			while(CompteurDePalet<4) {

				if(cotes == 'd') {
					CompteurDeMur =0;
					while(CompteurDeMur<3 || vehicule.vue.getDistance()<=0.2 ) {
						b=vehicule.DetectionDunObjetG(captTact,cotes);
						if(b){
							CompteurDePalet++;
						}
						else {
							CompteurDeMur++;
						}
					}
					if (CompteurDeMur==3) {
						CompteurDeMur =0;
						while(CompteurDeMur<3 || vehicule.vue.getDistance()<=0.2 ) {
							b=vehicule.DetectionDunObjetD(captTact,cotes);
							if(b){
								CompteurDePalet++;
							}
							else {
								CompteurDeMur++;
							}
						}
					}
				}


				else{
					CompteurDeMur=0;
					while(CompteurDeMur<3 || vehicule.vue.getDistance()<=0.2 ) {
						b= vehicule.DetectionDunObjetD(captTact,cotes);
						if(b){
							CompteurDePalet++;
						}
						else {
							CompteurDeMur++;
						}
					}
					if (CompteurDeMur==3) {
						CompteurDeMur=0;
						while(CompteurDeMur<3 || vehicule.vue.getDistance()<=0.2 ) {
							b= vehicule.DetectionDunObjetG(captTact,cotes);
							if(b){
								CompteurDePalet++;
							}
							else {
								CompteurDeMur++;
							}
						}
					}
				}

			}

		}

	}
}

	
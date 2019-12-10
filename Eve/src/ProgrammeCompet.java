import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.Button;

public class ProgrammeCompet {

	static public void main(String[]args) throws IOException  {
		InputStream input= new FileInputStream("Couleurs");
		Properties colore = new Properties();
		colore.load(input);
		CaptTactile captTact=new CaptTactile();
		Boussole boussole = new Boussole();
		TestColor captCouleur = new TestColor(new EV3ColorSensor(SensorPort.S4));
		Vehicule vehicule = new Vehicule();
		char cotes = 'd';

		Button.ESCAPE.waitForPress();
		int leMatchCommence = 0;
		int CompteurDePalet = 0;
		int CompteurDeMur = 0;

		if(leMatchCommence==0) {
			captTact.recupPremierPalet(vehicule,cotes,boussole);
			captCouleur.posePaletCamp(colore, vehicule, captTact,cotes,boussole);
			leMatchCommence++;
			CompteurDePalet++;
		}
		boolean b;
		while(Button.ENTER.isUp()){

			while(CompteurDePalet<4) {

				if(cotes == 'd') {
					boussole.orientation=0;
					CompteurDeMur =0;
					while(CompteurDeMur<3 || vehicule.vue.getDistance()<=0.2 ) {
						b=vehicule.DetectionDunObjetD(captTact,cotes);
						if(b){
							CompteurDePalet++;
							boussole.trouverOuest(vehicule.roues);
							captCouleur.posePaletCamp(colore, vehicule, captTact,cotes,boussole);
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
								boussole.trouverOuest(vehicule.roues);
								captCouleur.posePaletCamp(colore, vehicule, captTact,cotes,boussole);
							}
							else {
								CompteurDeMur++;
							}
						}
					}
				}


				else{
					CompteurDeMur=0;
					boussole.orientation=180;
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
								boussole.trouverEst(vehicule.roues);
								captCouleur.posePaletCamp(colore, vehicule, captTact,cotes,boussole);
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
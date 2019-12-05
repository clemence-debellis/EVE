import java.rmi.RemoteException;

import lejos.hardware.Button;
import lejos.remote.ev3.RMISampleProvider;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;

public class Tests {
	
	public static void main(String[] args) {
		
		RMISampleProvider sampleProvider=null;{
			try {
				RemoteEV3 ev3=new RemoteEV3("10.0.1.1");
				sampleProvider=ev3.createSampleProvider("S4", "lejos.hardware.sensor.EV3ColorSensor", "RGB");
				float[] samples=new float[3];
				samples=sampleProvider.fetchSample();
				System.out.println("RGB="+samples[0]+" / "+samples[1]+" / "+samples[2]);

				while (Button.ENTER.isUp()) {
					samples=sampleProvider.fetchSample();

					if ((samples[0]>0.255f) && (samples[0]<0.265f) && (samples[1]>0.260f) && (samples[1]<0.270f) && (samples[2]>0.195f) && (samples[2]<0.205f)) { System.out.println("White"); } 

	                if ((samples[0]>0.175f) && (samples[0]<0.190f) && (samples[1]>0.040f) && (samples[1]<0.055f) && (samples[2]>0.025f) && (samples[2]<0.045f)) {
	                	System.out.println("Red");
	                }
	                Delay.msDelay(3000);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					sampleProvider.close();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}																								
	}
}
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.Touch;
import lejos.utility.Delay;
import lejos.robotics.TouchAdapter;

public class CaptTactile extends EV3TouchSensor{

	
	RegulatedMotor mLeft = new EV3LargeRegulatedMotor (MotorPort.C);
	RegulatedMotor mRight= new EV3LargeRegulatedMotor (MotorPort.B);
	RegulatedMotor pinces= new EV3LargeRegulatedMotor (MotorPort.D);
	
	public CaptTactile(Port port)
    {
        super(port);
    }

    public boolean isPressed()
    {
        float[] sample = new float[1];
        fetchSample(sample, 0);

        return sample[0] != 0;
    }
	
	public void avancerJusquePalet() {
			mLeft.forward();
			mRight.forward();
			while (this.isPressed()==false) {
				Delay.msDelay(100);
			}
			mLeft.stop();
			mRight.stop();
			pinces.backward();
			Delay.msDelay(250);
			pinces.stop();
	}
	
	public static void main (String[]args) {
	
		CaptTactile capt= new CaptTactile(SensorPort.S1);	
		capt.avancerJusquePalet();
		
		}
	}
	


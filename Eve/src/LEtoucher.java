import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;

public class LEtoucher extends EV3TouchSensor {

	  public LEtoucher(Port port)
	    {
	        super(port);
	    }

	  
	  
	    public boolean isPressed()
	    {
	        float[] sample = new float[1];
	        fetchSample(sample, 0);

	        return sample[0] != 0;
	    }
}


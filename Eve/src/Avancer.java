
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Avancer
{
    private EV3LargeRegulatedMotor mLeftMotor;
    private EV3LargeRegulatedMotor mRightMotor;
    public int SPEED =500;

    public Avancer(Port left_port, Port right_port)
    {
        mLeftMotor = new EV3LargeRegulatedMotor(left_port);
        mRightMotor = new EV3LargeRegulatedMotor(right_port);
        mLeftMotor.synchronizeWith(new RegulatedMotor[]{mRightMotor});

        mLeftMotor.setSpeed(SPEED);
        mRightMotor.setSpeed(SPEED);
    }


    public void setspeed(int i) {
    	 mLeftMotor.setSpeed(i);
         mRightMotor.setSpeed(i);
    }
    
    public void avancer(){
    	mLeftMotor.startSynchronization();
        mLeftMotor.forward();
        mRightMotor.forward();
        mLeftMotor.endSynchronization();
    }

    public void stop(){
    	mLeftMotor.startSynchronization();
        mLeftMotor.stop();
        mRightMotor.stop();
        mLeftMotor.endSynchronization();
    }
    
    public void reculer()
    {
    	mLeftMotor.startSynchronization();
        mLeftMotor.backward();
        mRightMotor.backward();
        mLeftMotor.endSynchronization();
    }
    
    public void reculerTemps(int i)
    {
    	mLeftMotor.startSynchronization();
        mLeftMotor.backward();
        mRightMotor.backward();
        mLeftMotor.endSynchronization();
        
        Delay.msDelay(2000);
        
        mLeftMotor.startSynchronization();
        mLeftMotor.stop();
        mRightMotor.stop();
        mLeftMotor.endSynchronization();
    }
    
    public void rotateAsynch(int angle, int temps) {

    	mLeftMotor.startSynchronization();
        mLeftMotor.rotate(angle, true);
        mRightMotor.rotate(-angle, true);
        mLeftMotor.endSynchronization();
        
        Delay.msDelay(temps);
        
        mLeftMotor.startSynchronization();
        mLeftMotor.stop();
        mRightMotor.stop();
        mLeftMotor.endSynchronization();
        
    	
    }
    
    public void rotation() {
    	
    	mLeftMotor.startSynchronization();
        mLeftMotor.forward();
        mRightMotor.backward();
        mLeftMotor.endSynchronization();
        
        Delay.msDelay(100);
        
        mLeftMotor.startSynchronization();
        mLeftMotor.stop();
        mRightMotor.stop();
        mLeftMotor.endSynchronization();
        
    	
    }
}
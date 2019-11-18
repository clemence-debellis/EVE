

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

    private int SPEED = 500; // a adapter on verra plus tard mdr

    public Avancer(Port left_port, Port right_port)
    {
        mLeftMotor = new EV3LargeRegulatedMotor(left_port);
        mRightMotor = new EV3LargeRegulatedMotor(right_port);
        mLeftMotor.synchronizeWith(new RegulatedMotor[]{mRightMotor});

        mLeftMotor.setSpeed(SPEED);
        mRightMotor.setSpeed(SPEED);
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
    
    public void tcsD(int  v) {
    
    	SPEED =v;
    	
    	mLeftMotor.startSynchronization();
        mLeftMotor.backward();
        mRightMotor.forward();
        mLeftMotor.endSynchronization();
        
        Delay.msDelay(1510);
        
        mLeftMotor.startSynchronization();
        mLeftMotor.stop();
        mRightMotor.stop();
        mLeftMotor.endSynchronization();
        
    	
    }
    public void tcsG(int v) {
    	
    	SPEED =v;
        
    	mLeftMotor.startSynchronization();
        mLeftMotor.forward();
        mRightMotor.backward();
        mLeftMotor.endSynchronization();
        
        Delay.msDelay(1510);
        
        mLeftMotor.startSynchronization();
        mLeftMotor.stop();
        mRightMotor.stop();
        mLeftMotor.endSynchronization();
        
    	
    }
}

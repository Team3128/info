package org.team3128.mechanisms;

import org.team3128.common.hardware.misc.Piston;
import org.team3128.common.util.units.Angle;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * This is a thing
 * 
 * @author Ronak
 *
 */
public class GearShovel {
	public enum State {
		GROUND(120 * Angle.DEGREES, -1),
		REST(30 * Angle.DEGREES, 0),
		BACK(0 * Angle.DEGREES, -1),
		FIRE(50 * Angle.DEGREES, 1);
		
		public double angle;
		public double speed;
		
		State(double angle, double speed) {
			this.angle = angle;
			this.speed = speed;
		}
	}
	
	CANTalon pivotMotor;
	VictorSP rollerMotor;
	Piston arbitraryPiston;
	
	State shovelState;
	
	public GearShovel(CANTalon pivotMotor, VictorSP rollerMotor, Piston arbitraryPiston) {
		this.pivotMotor = pivotMotor;
		this.rollerMotor = rollerMotor;
		this.arbitraryPiston = arbitraryPiston;
		
		pivotMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		pivotMotor.changeControlMode(TalonControlMode.Position);
		
	
		
		shovelState = State.REST;
	}
	
	public void set(State state) {
		shovelState = state;
		
		pivotMotor.set(shovelState.angle * 4096 / (360 * Angle.DEGREES));
		rollerMotor.set(shovelState.speed);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

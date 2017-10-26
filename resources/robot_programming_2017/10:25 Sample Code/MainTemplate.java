package org.team3128.main;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.hardware.misc.Piston;
import org.team3128.common.listener.ListenerManager;
import org.team3128.common.listener.POVValue;
import org.team3128.common.listener.controllers.ControllerExtreme3D;
import org.team3128.common.listener.controltypes.Button;
import org.team3128.common.util.GenericSendableChooser;
import org.team3128.common.util.units.Length;
import org.team3128.mechanisms.GearShovel;
import org.team3128.mechanisms.GearShovel.ShovelState;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is the "sample code" from 10/25
 * @author Ronak
 *
 */

public class MainTemplate extends NarwhalRobot 
{
	CANTalon rightMotor1, rightMotor2, leftMotor1, leftMotor2;
	Joystick rightJoystick;
	
	CANTalon pivotMotor;
	VictorSP rollerMotor;
	Piston arbitraryPiston;
	
	ListenerManager lmRight;
	
	Piston piston1;
	Compressor comp;
	
	SRXTankDrive drive;
	
	GearShovel shovel;
		
	@Override
	protected void constructHardware() 
	{		
		rightJoystick = new Joystick(0);
				
		lmRight = new ListenerManager(rightJoystick);
		
		piston1 = new Piston(3, 4);
		
		leftMotor1 = new CANTalon(0);
		leftMotor2 = new CANTalon(1);
		
		rightMotor1 = new CANTalon(2);
		rightMotor2 = new CANTalon(3);
		
		leftMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		leftMotor2.changeControlMode(TalonControlMode.Follower);
		leftMotor2.set(leftMotor1.getDeviceID());
		
		rightMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		rightMotor2.changeControlMode(TalonControlMode.Follower);
		rightMotor2.set(rightMotor1.getDeviceID());
		
		// Creating the drivetrain object
		drive = new SRXTankDrive(leftMotor1, rightMotor1, Math.PI * 4 * Length.in, 1, 36 * Length.in, 40 * Length.in, 400);
		
		pivotMotor = new CANTalon(5);		
		rollerMotor = new VictorSP(3);
		arbitraryPiston = new Piston(0,1);
		
		shovel = new GearShovel(pivotMotor, rollerMotor, arbitraryPiston);
	}

	@Override
	protected void setupListeners() {
		lmRight.nameControl(ControllerExtreme3D.POV, "GearShovelMove");
		lmRight.nameControl(new Button(1), "Fire");
		
		lmRight.addListener("GearShovelMove", (POVValue povValue) -> {
			int povState = povValue.getDirectionValue();
			
			switch (povState) {
			case 8:
				shovel.set(ShovelState.GROUND);
				break;
			case 4:
				shovel.set(ShovelState.BACK);
				break;
			default:
				shovel.set(ShovelState.REST);
			}
		});
		
		lmRight.addButtonDownListener("Fire", () -> {
			shovel.set(ShovelState.FIRE);
		});
		lmRight.addButtonUpListener("Fire", () -> {
			shovel.set(ShovelState.REST);
		});
	}

	
	@Override
	protected void teleopInit() {
	}
	
	@Override
	protected void teleopPeriodic()
	{
	}
	
	@Override
	protected void autonomousInit() {

	}
	
	protected void autonomousPeriodic() {
		
	}
	
	protected void constructAutoPrograms(GenericSendableChooser<CommandGroup> programChooser)
	{		

	}
	
	@Override
	protected void updateDashboard() {
		
	}

}

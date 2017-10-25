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

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is the combined "sample code" from our first two days of robot
 * code (10/13 and 10/17)
 * @author Ronak
 *
 */

public class MainTemplate extends NarwhalRobot 
{
	CANTalon rightMotor1, rightMotor2, leftMotor1, leftMotor2;
	Joystick rightJoystick;
	
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
		
		
		//shovel = new GearShovel()
	}

	@Override
	protected void setupListeners() {
		lmRight.nameControl(new Button(1), "Button1");
		lmRight.nameControl(new Button(2), "Button2");
		lmRight.nameControl(new Button(3), "Button3");
		
		lmRight.nameControl(new Button(4), "Button4");
		lmRight.nameControl(new Button(5), "Button5");
		lmRight.nameControl(new Button(6), "Button6");
		
		lmRight.nameControl(ControllerExtreme3D.POV, "POVValue");
		lmRight.addListener("POVValue", (POVValue pov) -> {
			int val = pov.getDirectionValue();
		});
		
		lmRight.addMultiListener(() -> {
			double one = lmRight.getButton("Button1") ? .75 : 0;
			double two = lmRight.getButton("Button2") ? .25 : 0;
			double three = lmRight.getButton("Button3") ? -1 : 1;
			
			double four = lmRight.getButton("Button4") ? .75 : 0;
			double five = lmRight.getButton("Button5") ? .25 : 0;
			double six = lmRight.getButton("Button6") ? -1 : 1;
			
			double joyX = (four + five) * six;
			double joyY = (one + two) * three;
			
			drive.arcadeDrive(joyX, joyY, 1, true);
			
			comp.start();
			comp.getPressureSwitchValue();
			
		}, "Button1", "Button2", "Button3", "Button4", "Button5", "Button6");
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

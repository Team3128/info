package org.team3128.main;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.listener.ListenerManager;
import org.team3128.common.listener.controllers.ControllerExtreme3D;
import org.team3128.common.listener.controltypes.Button;
import org.team3128.common.util.GenericSendableChooser;
import org.team3128.common.util.units.Length;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
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
	VictorSP auxiliaryMotor1, auxiliaryMotor2;
	Joystick leftJoystick, rightJoystick;
	
	ListenerManager lmRight, lmLeft;
	
	SRXTankDrive drive;
	
	double rYvalue = 0;
	double lYvalue = 0;
		
	@Override
	protected void constructHardware() 
	{		
		// Setting up our two ListenerManagers, each having one joystick
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
				
		lmLeft = new ListenerManager(leftJoystick);
		lmRight = new ListenerManager(rightJoystick);
		
		// Setting up our two auxiliary motors
		auxiliaryMotor1 = new VictorSP(0);
		auxiliaryMotor2 = new VictorSP(1);
		
		// Setting up our four drive motors
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
		
	}

	@Override
	protected void setupListeners() {
		// Other listener types that you should know
		lmLeft.nameControl(new Button(5), "ActivateAux1");
		
		lmLeft.addButtonDownListener("ActivateAux1", () -> {
			auxiliaryMotor1.set(1.0);
		});
		lmLeft.addButtonUpListener("ActivateAux1", () -> {
			auxiliaryMotor1.set(0.0);
		});
		
		lmLeft.nameControl(ControllerExtreme3D.JOYX, "ControlAux2");
		
		lmLeft.addListener("ControlAux2", (double joyX) -> {
			auxiliaryMotor2.set(joyX);
		});
		
		// Listeners for standard robot drive
		lmRight.nameControl(ControllerExtreme3D.JOYY, "MoveAxis");
		lmRight.nameControl(ControllerExtreme3D.TWIST, "TurnAxis");
		lmRight.nameControl(ControllerExtreme3D.THROTTLE, "Throttle");
		
		lmRight.addMultiListener(() -> {
			double move = lmRight.getAxis("MoveAxis");
			double turn = lmRight.getAxis("TurnAxis");
			double throttle = lmRight.getAxis("Throttle");
			
			drive.arcadeDrive(move, turn, throttle, true);
		}, "MoveAxis", "TurnAxis", "Throttle");
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

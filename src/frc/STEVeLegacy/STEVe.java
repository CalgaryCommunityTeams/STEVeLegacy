/*----------------------------------------------------------------------------*/
 /* Copyright (c) FIRST 2008. All Rights Reserved.                             */
 /* Open Source Software - may be modified and shared by FRC teams. The code   */
 /* must be accompanied by the FIRST BSD license file in the root directory of */
 /* the project.                                                               */
 /*----------------------------------------------------------------------------*/
package frc.STEVeLegacy;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class STEVe extends IterativeRobot {

	private static final double MINSPEED_1 = 0.35;
	private static final double MINSPEED_2 = 0.35;
	private static final double MAXSPEED_1 = 0.85;
	private static final double MAXSPEED_2 = 0.85;

	Joystick LStick;
	Joystick RStick;
	RobotDrive mainDrive;
	Talon aimX;
	Talon aimY;
	Talon fireWheel1;
	Talon fireWheel2;
	Pusher pusher;

	boolean fireHeldLastTime = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		LStick = new Joystick(1);
		RStick = new Joystick(2);
		mainDrive = new RobotDrive(1, 2, 3, 4);
		aimX = new Talon(5);
		aimY = new Talon(6);
		fireWheel1 = new Talon(7);
		fireWheel2 = new Talon(8);
		pusher = new Pusher(new Talon(9));
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		mainDrive.arcadeDrive(LStick.getRawAxis(2), RStick.getRawAxis(1));
		mainDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		mainDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		mainDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		mainDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

		if (LStick.getRawButton(2)) {
			aimY.set(1.0);
		} else if (LStick.getRawButton(3)) {
			aimY.set(-1.0);
		} else {
			aimY.set(0);
		}
		if (LStick.getRawButton(4)) {
			aimX.set(1.0);
		} else if (LStick.getRawButton(5)) {
			aimX.set(-1.0);
		} else {
			aimX.set(0);
		}

		if (RStick.getRawButton(3)) {
			fireWheel1.set(scaleBoundValue(LStick.getRawAxis(3), -1, 1, MAXSPEED_1, MINSPEED_1));
			fireWheel2.set(scaleBoundValue(RStick.getRawAxis(3), -1, 1, MAXSPEED_2, MINSPEED_2));

			System.out.print("fireWheel1:	");
			System.out.print(fireWheel1.get());
			System.out.print("	fireWheel2:	");
			System.out.println(fireWheel2.get());

			if (RStick.getRawButton(1) && !fireHeldLastTime) {
				pusher.doIt();
			}
			fireHeldLastTime = RStick.getRawButton(1);
		} else {
			fireWheel1.set(0);
			fireWheel2.set(0);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

	static public double scaleBoundValue(double value, double inmax, double inmin, double outmax, double outmin) {
		double scale = (outmax - outmin) / (inmax - inmin);

		return ((value - inmin) * scale) + outmin;
	}

}

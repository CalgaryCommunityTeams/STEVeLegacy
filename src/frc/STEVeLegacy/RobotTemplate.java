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
public class RobotTemplate extends IterativeRobot {

	Joystick driveStick;
	RobotDrive mainDrive;
	Joystick turretStick;
	Talon aimX;
	Talon aimY;
	Talon fireWheel1;
	Talon fireWheel2;
	Talon pusher;

	boolean firing;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		driveStick = new Joystick(0);
		mainDrive = new RobotDrive(0, 1, 2, 3);
		turretStick = new Joystick(1);
		aimX = new Talon(4);
		aimY = new Talon(5);
		fireWheel1 = new Talon(6);
		fireWheel2 = new Talon(7);
		pusher = new Talon(8);
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
		mainDrive.arcadeDrive(driveStick);

		aimX.set(turretStick.getRawAxis(0));
		aimY.set(turretStick.getRawAxis(1));

		if (turretStick.getRawButton(1)) {
			fireWheel1.set((turretStick.getRawAxis(3) + 1) / 4);
			fireWheel2.set((turretStick.getRawAxis(3) + 1) / 4);

			if (turretStick.getRawButton(0)) {
				pusher.set(0.5);
			}
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}

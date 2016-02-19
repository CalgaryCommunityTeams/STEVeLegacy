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

	Joystick driveGamepad;
	RobotDrive mainDrive;
	Joystick turretStick;
	Talon aimX;
	Talon aimY;
	Talon fireWheel1;
	Talon fireWheel2;
	Pusher pusher;

	Joystick calStick;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		driveGamepad = new Joystick(1);
		mainDrive = new RobotDrive(1, 2, 3, 4);
		turretStick = new Joystick(2);
		aimX = new Talon(5);
		aimY = new Talon(6);
		fireWheel1 = new Talon(7);
		fireWheel2 = new Talon(8);
		pusher = new Pusher(new Talon(9));

		calStick = new Joystick(3);
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
		mainDrive.tankDrive(driveGamepad.getRawAxis(2), driveGamepad.getRawAxis(5));

		aimX.set(turretStick.getRawAxis(1));
		aimY.set(turretStick.getRawAxis(2));

		if (turretStick.getRawButton(3)) {
			fireWheel1.set((-turretStick.getRawAxis(3) + 1) / 2);
			fireWheel2.set((-calStick.getRawAxis(3) + 1) / 2);

			System.out.print("fireWheel1:	");
			System.out.print(fireWheel1.get());
			System.out.print("	fireWheel2:	");
			System.out.println(fireWheel2.get());

			if (turretStick.getRawButton(1)) {
				pusher.doIt();
			}
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

}

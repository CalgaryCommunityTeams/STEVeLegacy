/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.STEVeLegacy;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author daniel
 */
public class Pusher {

	private static final double SPEED = 0.5;
	private static final long TIME_F = 200;
	private static final long TIME_B = 300;

	private PusherThread pusherThread;
	private SpeedController motor;

	public Pusher(SpeedController motor) {
		this.motor = motor;
	}

	public void doIt() {
		if (pusherThread == null || !pusherThread.isAlive()) {
			//Do It
			pusherThread = new PusherThread();
			pusherThread.start();
		}
	}

	private class PusherThread extends Thread {

		public void run() {
			try {
				motor.set(SPEED);
				Thread.sleep(TIME_F);
				motor.set(-SPEED);
				Thread.sleep(TIME_B);
			} catch (InterruptedException e) {
			}
			motor.set(0);//Motor should be set to zero even if interrupted
		}
	}
}

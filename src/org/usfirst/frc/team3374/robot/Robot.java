package org.usfirst.frc.team3374.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.PrintStream;
import org.usfirst.frc.team3374.robot.commands.Follow_Target;
import org.usfirst.frc.team3374.robot.commands.GearPickup;
import org.usfirst.frc.team3374.robot.commands.VisionGuidedWithTurnCommandGroup;
import org.usfirst.frc.team3374.robot.subsystems.DriveLine;

public class Robot extends IterativeRobot {
	Command autonomousCommand;
	/* 33 */ public static DriveLine driveLine = null;

	public static OI oi;

	/* 45 */ GearPickup GearPickup = new GearPickup();
	/* 46 */ Follow_Target Target = new Follow_Target();

	public SendableChooser<Command> autoChooser;

	public void robotInit() {
		/* 57 */ System.out.println("Robot Initializing...");

		/* 59 */ oi = new OI();
		/* 60 */ oi.init();
		/* 61 */ System.out.println("Chooser init");

		/* 65 */ UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();

		/* 69 */ camera.setResolution(320, 240);
		/* 70 */ camera.setExposureManual(1);
		/* 71 */ camera.setBrightness(1);
		/* 72 */ camera.setFPS(60);

		/* 74 */ System.out.println("Robot Completed Initialization...");
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
	}

	public void autonomousInit() {
		/* 106 */ Scheduler.getInstance().removeAll();
		/* 107 */ System.out.println("Auto Init Called");

		/* 128 */ this.autonomousCommand = new VisionGuidedWithTurnCommandGroup(false);

		/* 130 */ oi.reinit();
		/* 131 */ this.Target.start();
		/* 132 */ if (this.autonomousCommand != null) {
			/* 133 */ this.autonomousCommand.start();
		}

		/* 136 */ System.out.println("AUTOMODE!!");
	}

	public void autonomousPeriodic() {
		/* 145 */ Scheduler.getInstance().run();
		/* 146 */ SmartDashboard.putData(Scheduler.getInstance());
	}

	public void autonomousDisabled() {
		/* 150 */ Scheduler.getInstance().removeAll();
		/* 151 */ if (this.autonomousCommand != null) {
			/* 152 */ this.autonomousCommand.cancel();
		}
	}

	public void teleopInit() {
		/* 158 */ Scheduler.getInstance().removeAll();
		/* 159 */ driveLine = new DriveLine(1.0D, 0.0D, 0.0D);
		/* 160 */ this.Target.start();

		/* 166 */ if (this.autonomousCommand != null) {
			/* 167 */ this.autonomousCommand.cancel();
		}

		/* 170 */ System.out.println("Teleop Init Called");

		/* 172 */ this.GearPickup.start();
	}

	public void teleopPeriodic() {
		/* 181 */ Scheduler.getInstance().run();
	}

	public void teleopDisabled() {
		/* 185 */ Scheduler.getInstance().removeAll();
	}

	public void testPeriodic() {
	}
}

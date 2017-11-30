/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import com.ctre.CANTalon;
/*    */ import edu.wpi.first.wpilibj.RobotDrive;
/*    */ import edu.wpi.first.wpilibj.Timer;
/*    */ import edu.wpi.first.wpilibj.command.Command;
/*    */ import java.io.PrintStream;
/*    */ import org.usfirst.frc.team3374.robot.OI;
/*    */ import org.usfirst.frc.team3374.robot.Robot;
/*    */ import org.usfirst.frc.team3374.robot.RobotMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AutonomousDriveDeadReckon
/*    */   extends Command
/*    */ {
/*    */   protected void initialize() {}
/*    */   
/*    */   protected void execute()
/*    */   {
/* 26 */     OI oi = Robot.oi;
/* 27 */     System.out.println("STARTING ARM RAISE");
/* 28 */     Robot.oi.getGear().setPID(1.5D, 0.001D, 100.0D);
/*    */     
/* 30 */     oi.Target_Position = RobotMap.centergear;
/*    */     
/*    */ 
/* 33 */     System.out.println("STARTING BLIND DRIVE FORWARD");
/* 34 */     oi.Drive().arcadeDrive(0.0D, -0.6D);
/* 35 */     Timer.delay(1.0D);
/* 36 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/* 37 */     Timer.delay(2.0D);
/*    */     
/*    */ 
/* 40 */     System.out.println("STARTING BLIND ROTATE");
/* 41 */     oi.Drive().arcadeDrive(-0.6D, 0.0D);
/* 42 */     Timer.delay(1.0D);
/* 43 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/* 44 */     Timer.delay(2.0D);
/*    */     
/*    */ 
/* 47 */     System.out.println("BRING GEAR TO VERTICAL");
/*    */     
/* 49 */     oi.Target_Position = RobotMap.topgear;
/* 50 */     Timer.delay(1.0D);
/*    */     
/*    */ 
/* 53 */     System.out.println("STARTING BLIND DRIVE FORWARD");
/* 54 */     oi.Drive().arcadeDrive(0.0D, -0.6D);
/* 55 */     Timer.delay(1.0D);
/* 56 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/* 57 */     Timer.delay(2.0D);
/*    */     
/*    */ 
/* 60 */     System.out.println("STARTING TO PLACE THE GEAR");
/*    */     
/* 62 */     oi.Target_Position = RobotMap.placegear;
/* 63 */     Timer.delay(1.0D);
/*    */     
/*    */ 
/* 66 */     System.out.println("STARTING BLIND DRIVE BACKWARD");
/* 67 */     oi.Drive().arcadeDrive(0.0D, 0.7D);
/* 68 */     Timer.delay(1.0D);
/* 69 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/*    */   }
/*    */   
/*    */   protected boolean isFinished()
/*    */   {
/* 74 */     return true;
/*    */   }
/*    */   
/*    */   protected void end() {}
/*    */   
/*    */   protected void interrupted() {}
/*    */ }


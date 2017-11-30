/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.Joystick;
/*    */ import edu.wpi.first.wpilibj.RobotDrive;
/*    */ import edu.wpi.first.wpilibj.command.Command;
/*    */ import org.usfirst.frc.team3374.robot.OI;
/*    */ import org.usfirst.frc.team3374.robot.Robot;
/*    */ 
/*    */ public class Drivetrain
/*    */   extends Command
/*    */ {
/*    */   public Drivetrain()
/*    */   {
/* 14 */     requires(Robot.driveLine);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void initialize() {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected void execute()
/*    */   {
/* 26 */     OI oi = Robot.oi;
/* 27 */     oi.Drive().arcadeDrive(oi.getDriveStick().getX() * 0.95D, -oi.getDriveStick().getY() * 0.95D);
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean isFinished()
/*    */   {
/* 33 */     return false;
/*    */   }
/*    */   
/*    */   protected void end() {}
/*    */   
/*    */   protected void interrupted() {}
/*    */ }


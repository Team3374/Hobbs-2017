/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.RobotDrive;
/*    */ import edu.wpi.first.wpilibj.command.TimedCommand;
/*    */ import java.io.PrintStream;
/*    */ import org.usfirst.frc.team3374.robot.OI;
/*    */ import org.usfirst.frc.team3374.robot.Robot;
/*    */ 
/*    */ public class DriveForwardCommand extends TimedCommand
/*    */ {
/* 11 */   double drive_power = 0.0D;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DriveForwardCommand(double timeout, double power)
/*    */   {
/* 19 */     super(timeout);
/* 20 */     this.drive_power = power;
/*    */   }
/*    */   
/*    */   protected void initialize()
/*    */   {
/* 25 */     System.out.println(getClass().toString() + "::init");
/*    */   }
/*    */   
/*    */ 
/*    */   protected void execute()
/*    */   {
/* 31 */     Robot.oi.Drive().arcadeDrive(0.0D, -this.drive_power);
/*    */   }
/*    */   
/*    */   protected void end() {
/* 35 */     System.out.println(getClass().toString() + "::ending");
/* 36 */     Robot.oi.Drive().arcadeDrive(0.0D, 0.0D);
/*    */   }
/*    */ }


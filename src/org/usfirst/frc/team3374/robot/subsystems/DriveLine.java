/*    */ package org.usfirst.frc.team3374.robot.subsystems;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.command.PIDSubsystem;
/*    */ import org.usfirst.frc.team3374.robot.MultiSpeedController;
/*    */ import org.usfirst.frc.team3374.robot.OI;
/*    */ import org.usfirst.frc.team3374.robot.RoboPositioner;
/*    */ import org.usfirst.frc.team3374.robot.Robot;
/*    */ import org.usfirst.frc.team3374.robot.commands.Drivetrain;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DriveLine
/*    */   extends PIDSubsystem
/*    */ {
/*    */   RoboPositioner pid_source;
/*    */   
/*    */   public DriveLine(double p, double i, double d)
/*    */   {
/* 20 */     super(p, i, d);
/* 21 */     this.pid_source = new RoboPositioner();
/*    */   }
/*    */   
/*    */ 
/*    */   public void initDefaultCommand()
/*    */   {
/* 27 */     setDefaultCommand(new Drivetrain());
/*    */   }
/*    */   
/*    */   protected double returnPIDInput()
/*    */   {
/* 32 */     return this.pid_source.pidGet();
/*    */   }
/*    */   
/*    */ 
/*    */   protected void usePIDOutput(double output)
/*    */   {
/* 38 */     Robot.oi.left().pidWrite(output);
/* 39 */     Robot.oi.right().pidWrite(output);
/*    */   }
/*    */ }


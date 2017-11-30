/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.command.Command;
/*    */ import org.usfirst.frc.team3374.robot.MultiSpeedController;
/*    */ import org.usfirst.frc.team3374.robot.OI;
/*    */ import org.usfirst.frc.team3374.robot.Robot;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Climber
/*    */   extends Command
/*    */ {
/*    */   protected void initialize() {}
/*    */   
/*    */   protected void execute()
/*    */   {
/* 27 */     OI oi = Robot.oi;
/* 28 */     oi.getClimber().set(1.0D);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected boolean isFinished()
/*    */   {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   protected void end() {}
/*    */   
/*    */   protected void interrupted() {}
/*    */ }


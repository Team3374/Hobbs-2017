/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.command.Command;
/*    */ import edu.wpi.first.wpilibj.vision.VisionThread;
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
/*    */ public class GearProcessing
/*    */   extends Command
/*    */ {
/*    */   protected void initialize() {}
/*    */   
/*    */   protected void execute()
/*    */   {
/* 24 */     OI oi = Robot.oi;
/* 25 */     oi.getOutputVideo();
/* 26 */     oi.getVisionThread().start();
/*    */   }
/*    */   
/*    */   protected boolean isFinished()
/*    */   {
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   protected void end() {}
/*    */   
/*    */   protected void interrupted() {}
/*    */ }


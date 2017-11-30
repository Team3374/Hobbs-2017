/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.VictorSP;
/*    */ import edu.wpi.first.wpilibj.command.Command;
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
/*    */ 
/*    */ public class GearFeederReverse
/*    */   extends Command
/*    */ {
/*    */   protected void initialize() {}
/*    */   
/*    */   protected void execute()
/*    */   {
/* 28 */     OI oi = Robot.oi;
/* 29 */     oi.getGearIntake().set(1.0D);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected boolean isFinished()
/*    */   {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   protected void end() {}
/*    */   
/*    */   protected void interrupted() {}
/*    */ }


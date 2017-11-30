/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.VictorSP;
/*    */ import edu.wpi.first.wpilibj.command.Command;
/*    */ import java.io.PrintStream;
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
/*    */ public class CancelGearFeeder
/*    */   extends Command
/*    */ {
/*    */   protected void initialize() {}
/*    */   
/*    */   protected void execute()
/*    */   {
/* 27 */     System.out.println("cancelling gearfeeder");
/* 28 */     OI oi = Robot.oi;
/* 29 */     oi.getGearIntake().set(0.0D);
/* 30 */     oi.gearFeederCommand.cancel();
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean isFinished()
/*    */   {
/* 36 */     return true;
/*    */   }
/*    */   
/*    */   protected void end() {}
/*    */   
/*    */   protected void interrupted() {}
/*    */ }


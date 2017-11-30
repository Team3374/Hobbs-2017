/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import com.ctre.CANTalon;
/*    */ import edu.wpi.first.wpilibj.VictorSP;
/*    */ import edu.wpi.first.wpilibj.command.Command;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GearFeeder
/*    */   extends Command
/*    */ {
/*    */   protected void initialize() {}
/*    */   
/*    */   protected void execute()
/*    */   {
/* 28 */     OI oi = Robot.oi;
/* 29 */     oi.getGearIntake().set(-1.0D);
/* 30 */     oi.getGear().set(RobotMap.gndgear);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected boolean isFinished()
/*    */   {
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   protected void end() {}
/*    */   
/*    */   protected void interrupted() {}
/*    */ }


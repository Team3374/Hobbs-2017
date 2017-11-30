/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import com.ctre.CANTalon;
/*    */ import edu.wpi.first.wpilibj.Joystick;
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
/*    */ public class GearPickup
/*    */   extends Command
/*    */ {
/*    */   protected void initialize()
/*    */   {
/* 20 */     System.out.println("GEARPICKUP INIT");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void execute()
/*    */   {
/* 27 */     if (Robot.oi.getControlCenter().getRawButton(1) == true) {
/* 28 */       Robot.oi.Target_Position = RobotMap.centergear;
/*    */     }
/*    */     
/* 31 */     if (Robot.oi.getControlCenter().getRawButton(9) == true) {
/* 32 */       Robot.oi.getGear().setEncPosition(RobotMap.centergear);
/* 33 */       Robot.oi.Target_Position = RobotMap.centergear;
/* 34 */       Robot.oi.getGear().set(RobotMap.centergear);
/*    */     }
/*    */     
/* 37 */     if (Robot.oi.getControlCenter().getRawAxis(2) >= 0.05D) {
/* 38 */       Robot.oi.Target_Position -= 20.0D * Math.pow(Robot.oi.getControlCenter().getRawAxis(2), 2.0D);
/*    */     }
/*    */     
/* 41 */     if (Robot.oi.getControlCenter().getRawAxis(3) >= 0.05D) {
/* 42 */       Robot.oi.Target_Position += 20.0D * Math.pow(Robot.oi.getControlCenter().getRawAxis(3), 2.0D);
/*    */     }
/*    */     
/* 45 */     if (Robot.oi.getControlCenter().getRawButton(2) == true) {
/* 46 */       Robot.oi.Target_Position = RobotMap.gndgear;
/*    */     }
/*    */     
/* 49 */     if (Robot.oi.getControlCenter().getRawButton(3) == true) {
/* 50 */       Robot.oi.Target_Position = RobotMap.topgear;
/*    */     }
/* 52 */     if (Robot.oi.getControlCenter().getRawButton(4) == true) {
/* 53 */       Robot.oi.Target_Position = RobotMap.placegear;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected boolean isFinished()
/*    */   {
/* 61 */     return false;
/*    */   }
/*    */ }


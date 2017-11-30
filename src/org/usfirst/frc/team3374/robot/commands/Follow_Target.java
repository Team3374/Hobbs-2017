/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import com.ctre.CANTalon;
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
/*    */ public class Follow_Target
/*    */   extends Command
/*    */ {
/*    */   protected void initialize()
/*    */   {
/* 21 */     System.out.println("FOLLOW TARGET INIT");
/* 22 */     Robot.oi.getGear().set(0.0D);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void execute()
/*    */   {
/* 28 */     OI oi = Robot.oi;
/*    */     
/* 30 */     if (oi.Target_Position != Robot.oi.getGear().getSetpoint())
/*    */     {
/*    */ 
/* 33 */       if (Math.abs(oi.Target_Position - Robot.oi.getGear().getSetpoint()) <= RobotMap.Setpoint_Delta) {
/* 34 */         System.out.println("FOLLOW TARGET @LOCATION " + oi.Target_Position + " " + Robot.oi.getGear().getSetpoint());
/* 35 */         Robot.oi.getGear().set(oi.Target_Position);
/*    */       }
/* 37 */       else if (oi.Target_Position > Robot.oi.getGear().getSetpoint()) {
/* 38 */         System.out.println("FOLLOW TARGET ABV LOCATION: " + oi.Target_Position + " " + Robot.oi.getGear().getSetpoint());
/* 39 */         Robot.oi.getGear().set(Robot.oi.getGear().getSetpoint() + RobotMap.Setpoint_Delta);
/*    */       }
/*    */       else
/*    */       {
/* 43 */         Robot.oi.getGear().set(Robot.oi.getGear().getSetpoint() - RobotMap.Setpoint_Delta);
/* 44 */         System.out.println("FOLLOW TARGET BEL LOCATION: " + oi.Target_Position + " " + Robot.oi.getGear().getSetpoint());
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean isFinished()
/*    */   {
/* 52 */     return false;
/*    */   }
/*    */   
/*    */   protected void end()
/*    */   {
/* 57 */     System.out.println("Ending Target_Follow");
/*    */   }
/*    */   
/*    */   protected void interrupted() {}
/*    */ }


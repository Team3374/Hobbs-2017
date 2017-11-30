/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.command.TimedCommand;
/*    */ import java.io.PrintStream;
/*    */ import org.usfirst.frc.team3374.robot.OI;
/*    */ 
/*    */ public class TurnCommand extends TimedCommand
/*    */ {
/*  9 */   boolean turnLeft = false;
/* 10 */   double power = 0.0D;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public TurnCommand(double timeout, double power, boolean turnLeft)
/*    */   {
/* 19 */     super(timeout);
/* 20 */     this.turnLeft = turnLeft;
/* 21 */     this.power = power;
/*    */   }
/*    */   
/*    */   protected void initialize()
/*    */   {
/* 26 */     System.out.println(getClass().toString() + "::init");
/*    */   }
/*    */   
/*    */   public void execute() {
/* 30 */     double value = this.turnLeft ? -this.power : this.power;
/* 31 */     org.usfirst.frc.team3374.robot.Robot.oi.Drive().arcadeDrive(value, 0.0D);
/*    */   }
/*    */   
/*    */   protected void end() {
/* 35 */     System.out.println(getClass().toString() + "::ending");
/*    */   }
/*    */ }


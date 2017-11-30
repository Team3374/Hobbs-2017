/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.RobotDrive;
/*    */ import java.io.PrintStream;
/*    */ import org.usfirst.frc.team3374.robot.OI;
/*    */ import org.usfirst.frc.team3374.robot.Robot;
/*    */ 
/*    */ public class VisionGuidedCommand extends edu.wpi.first.wpilibj.command.TimedCommand
/*    */ {
/* 10 */   double maxSpeed = 0.6D;
/* 11 */   double minSpeed = 0.5D;
/* 12 */   double currentSpeed = this.maxSpeed;
/* 13 */   double angularError = 0.0D;
/* 14 */   double cameraCenter = 147.0D;
/*    */   
/* 16 */   double angularErrorScaleFactor = -0.01D;
/* 17 */   boolean reachedTarget = false;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public VisionGuidedCommand(double timeout)
/*    */   {
/* 24 */     super(timeout);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void execute()
/*    */   {
/* 34 */     double[] liftPositionInfo = Robot.oi.getLiftPosition();
/*    */     
/* 36 */     if ((liftPositionInfo[0] < 0.0D) || (liftPositionInfo[1] < 0.0D)) {
/* 37 */       return;
/*    */     }
/*    */     
/*    */ 
/* 41 */     double currentRange = liftPositionInfo[1];
/*    */     
/*    */ 
/* 44 */     if (currentRange <= 80.0D)
/*    */     {
/* 46 */       double newSpeed = this.maxSpeed / 80.0D * currentRange;
/* 47 */       newSpeed = Math.max(newSpeed, this.minSpeed);
/* 48 */       newSpeed = Math.min(newSpeed, this.currentSpeed);
/* 49 */       this.currentSpeed = newSpeed;
/*    */     }
/* 51 */     this.angularError = (this.cameraCenter - liftPositionInfo[0]);
/*    */     
/* 53 */     double angleDrive = Math.min(0.5D, Math.max(this.angularError * this.angularErrorScaleFactor, -0.5D));
/*    */     
/*    */ 
/*    */ 
/* 57 */     Robot.oi.Drive().arcadeDrive(angleDrive, -this.currentSpeed);
/*    */     
/* 59 */     System.out.print("Range: " + Math.floor(currentRange));
/* 60 */     System.out.print(" Speed: " + Math.floor(this.currentSpeed * 100.0D));
/* 61 */     System.out.println(" Turn: " + Math.floor(this.angularError * this.angularErrorScaleFactor * 100.0D));
/*    */     
/* 63 */     if (currentRange < 22.0D) {
/* 64 */       this.reachedTarget = true;
/* 65 */       System.out.println("End vision guided command");
/* 66 */       Robot.oi.Drive().arcadeDrive(0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isFinished()
/*    */   {
/* 72 */     return (isTimedOut()) || (this.reachedTarget);
/*    */   }
/*    */ }


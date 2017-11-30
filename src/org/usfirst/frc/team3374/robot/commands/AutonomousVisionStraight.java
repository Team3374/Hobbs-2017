/*     */ package org.usfirst.frc.team3374.robot.commands;
/*     */ 
/*     */ import com.ctre.CANTalon;
/*     */ import edu.wpi.first.wpilibj.RobotDrive;
/*     */ import edu.wpi.first.wpilibj.Timer;
/*     */ import edu.wpi.first.wpilibj.command.Command;
/*     */ import java.io.PrintStream;
/*     */ import org.usfirst.frc.team3374.robot.OI;
/*     */ import org.usfirst.frc.team3374.robot.Robot;
/*     */ import org.usfirst.frc.team3374.robot.RobotMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutonomousVisionStraight
/*     */   extends Command
/*     */ {
/*     */   protected void initialize() {}
/*     */   
/*     */   protected void execute()
/*     */   {
/*  29 */     OI oi = Robot.oi;
/*     */     
/*     */ 
/*  32 */     System.out.println("STARTING ARM RAISE");
/*  33 */     Robot.oi.getGear().setPID(1.5D, 0.001D, 100.0D);
/*     */     
/*  35 */     oi.Target_Position = RobotMap.centergear;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */     System.out.println("STARTING THE APPROACH");
/*  57 */     boolean approachedTarget = false;
/*     */     
/*  59 */     double maxSpeed = 0.4D;
/*  60 */     double minSpeed = 0.4D;
/*  61 */     double currentSpeed = maxSpeed;
/*  62 */     double angularError = 0.0D;
/*  63 */     double cameraCenter = 160.0D;
/*  64 */     double angularErrorScaleFactor = -0.01D;
/*  65 */     while (!approachedTarget) {
/*  66 */       double[] liftPositionInfo = oi.getLiftPosition();
/*     */       
/*  68 */       if ((liftPositionInfo[0] >= 0.0D) && (liftPositionInfo[1] >= 0.0D))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*  73 */         double currentRange = liftPositionInfo[1];
/*     */         
/*     */ 
/*     */ 
/*  77 */         if (currentRange <= 80.0D)
/*     */         {
/*  79 */           double newSpeed = maxSpeed / 80.0D * currentRange;
/*  80 */           newSpeed = Math.max(newSpeed, minSpeed);
/*  81 */           newSpeed = Math.min(newSpeed, currentSpeed);
/*  82 */           currentSpeed = newSpeed;
/*     */         }
/*  84 */         angularError = cameraCenter - liftPositionInfo[0];
/*     */         
/*  86 */         double angleDrive = Math.min(0.5D, Math.max(angularError * angularErrorScaleFactor, -0.5D));
/*     */         
/*     */ 
/*  89 */         oi.Drive().arcadeDrive(angleDrive, -currentSpeed);
/*     */         
/*  91 */         System.out.print("Range: " + Math.floor(currentRange));
/*  92 */         System.out.print(" Speed: " + Math.floor(currentSpeed * 100.0D));
/*  93 */         System.out.println(" Turn: " + Math.floor(angularError * angularErrorScaleFactor * 100.0D));
/*     */         
/*  95 */         if (currentRange < 15.0D) {
/*  96 */           approachedTarget = true;
/*  97 */           oi.Drive().arcadeDrive(0.0D, 0.0D);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 102 */     System.out.println("BRINGING GEAR TO VERTICAL");
/*     */     
/* 104 */     oi.Target_Position = RobotMap.topgear;
/* 105 */     Timer.delay(1.0D);
/*     */     
/*     */ 
/* 108 */     System.out.println("STARTING BLIND DRIVE FORWARD");
/* 109 */     oi.Drive().arcadeDrive(0.0D, -0.6D);
/* 110 */     Timer.delay(0.5D);
/* 111 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/* 112 */     Timer.delay(0.0D);
/*     */     
/*     */ 
/* 115 */     System.out.println("STARTING TO DROP THE GEAR");
/*     */     
/* 117 */     oi.Target_Position = RobotMap.placegear;
/* 118 */     Timer.delay(1.0D);
/*     */     
/*     */ 
/* 121 */     System.out.println("STARTING BLIND DRIVE BACKWARD");
/* 122 */     oi.Drive().arcadeDrive(0.0D, 0.7D);
/* 123 */     Timer.delay(1.0D);
/* 124 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isFinished()
/*     */   {
/* 132 */     return true;
/*     */   }
/*     */   
/*     */   protected void end() {}
/*     */   
/*     */   protected void interrupted() {}
/*     */ }


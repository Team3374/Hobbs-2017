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
/*     */ public class AutonomousVisionwithTurn
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
/*  38 */     System.out.println("STARTING BLIND DRIVE FORWARD");
/*  39 */     oi.Drive().arcadeDrive(0.0D, -0.6D);
/*  40 */     Timer.delay(0.75D);
/*  41 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/*  42 */     Timer.delay(1.0D);
/*     */     
/*     */ 
/*     */ 
/*  46 */     oi.Drive().arcadeDrive(-0.6D, 0.0D);
/*     */     
/*  48 */     Timer.delay(0.4D);
/*  49 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/*  50 */     Timer.delay(2.0D);
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
/* 104 */     System.out.println("STARTING THE APPROACH");
/* 105 */     boolean approachedTarget = false;
/*     */     
/* 107 */     double maxSpeed = 0.4D;
/* 108 */     double minSpeed = 0.4D;
/* 109 */     double currentSpeed = maxSpeed;
/* 110 */     double angularError = 0.0D;
/* 111 */     double cameraCenter = 160.0D;
/* 112 */     double angularErrorScaleFactor = -0.01D;
/* 113 */     while (!approachedTarget) {
/* 114 */       double[] liftPositionInfo = oi.getLiftPosition();
/*     */       
/* 116 */       if ((liftPositionInfo[0] >= 0.0D) && (liftPositionInfo[1] >= 0.0D))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 121 */         double currentRange = liftPositionInfo[1];
/*     */         
/*     */ 
/*     */ 
/* 125 */         if (currentRange <= 80.0D)
/*     */         {
/* 127 */           double newSpeed = maxSpeed / 80.0D * currentRange;
/* 128 */           newSpeed = Math.max(newSpeed, minSpeed);
/* 129 */           newSpeed = Math.min(newSpeed, currentSpeed);
/* 130 */           currentSpeed = newSpeed;
/*     */         }
/* 132 */         angularError = cameraCenter - liftPositionInfo[0];
/*     */         
/* 134 */         double angleDrive = Math.min(0.5D, Math.max(angularError * angularErrorScaleFactor, -0.5D));
/*     */         
/*     */ 
/* 137 */         oi.Drive().arcadeDrive(angleDrive, -currentSpeed);
/*     */         
/* 139 */         System.out.print("Range: " + Math.floor(currentRange));
/* 140 */         System.out.print(" Speed: " + Math.floor(currentSpeed * 100.0D));
/* 141 */         System.out.println(" Turn: " + Math.floor(angularError * angularErrorScaleFactor * 100.0D));
/*     */         
/* 143 */         if (currentRange < 15.0D) {
/* 144 */           approachedTarget = true;
/* 145 */           oi.Drive().arcadeDrive(0.0D, 0.0D);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 150 */     System.out.println("BRINGING GEAR TO VERTICAL");
/*     */     
/* 152 */     oi.Target_Position = RobotMap.topgear;
/* 153 */     Timer.delay(1.0D);
/*     */     
/*     */ 
/* 156 */     System.out.println("STARTING BLIND DRIVE FORWARD");
/* 157 */     oi.Drive().arcadeDrive(0.0D, -0.6D);
/* 158 */     Timer.delay(0.5D);
/* 159 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/* 160 */     Timer.delay(0.0D);
/*     */     
/*     */ 
/* 163 */     System.out.println("STARTING TO DROP THE GEAR");
/*     */     
/* 165 */     oi.Target_Position = RobotMap.placegear;
/* 166 */     Timer.delay(1.0D);
/*     */     
/*     */ 
/* 169 */     System.out.println("STARTING BLIND DRIVE BACKWARD");
/* 170 */     oi.Drive().arcadeDrive(0.0D, 0.7D);
/* 171 */     Timer.delay(1.0D);
/* 172 */     oi.Drive().arcadeDrive(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isFinished()
/*     */   {
/* 180 */     return true;
/*     */   }
/*     */   
/*     */   protected void end() {}
/*     */   
/*     */   protected void interrupted() {}
/*     */ }


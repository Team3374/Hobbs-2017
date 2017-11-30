/*     */ package org.usfirst.frc.team3374.robot;
/*     */ 
/*     */ import com.ctre.CANTalon;
/*     */ import com.ctre.CANTalon.FeedbackDevice;
/*     */ import com.ctre.CANTalon.TalonControlMode;
/*     */ import edu.wpi.cscore.CvSource;
/*     */ import edu.wpi.first.wpilibj.BuiltInAccelerometer;
/*     */ import edu.wpi.first.wpilibj.Joystick;
/*     */ import edu.wpi.first.wpilibj.RobotDrive;
/*     */ import edu.wpi.first.wpilibj.Ultrasonic;
/*     */ import edu.wpi.first.wpilibj.VictorSP;
/*     */ import edu.wpi.first.wpilibj.buttons.Button;
/*     */ import edu.wpi.first.wpilibj.buttons.JoystickButton;
/*     */ import edu.wpi.first.wpilibj.command.Command;
/*     */ import edu.wpi.first.wpilibj.interfaces.Accelerometer;
/*     */ import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
/*     */ import edu.wpi.first.wpilibj.networktables.NetworkTable;
/*     */ import edu.wpi.first.wpilibj.vision.VisionThread;
/*     */ import java.io.PrintStream;
/*     */ import org.usfirst.frc.team3374.robot.commands.CancelGearFeeder;
/*     */ import org.usfirst.frc.team3374.robot.commands.Climber;
/*     */ import org.usfirst.frc.team3374.robot.commands.ClimberCancel;
/*     */ import org.usfirst.frc.team3374.robot.commands.ClimberDown;
/*     */ import org.usfirst.frc.team3374.robot.commands.GearFeeder;
/*     */ import org.usfirst.frc.team3374.robot.commands.GearFeederReverse;
/*     */ import org.usfirst.frc.team3374.robot.commands.GearPickup;
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
/*     */ public class OI
/*     */ {
/*     */   private Joystick stick;
/*     */   private Joystick ControlCenter;
/*     */   public double[] autocenterX;
/*     */   public double[] autocenterY;
/*     */   public double[] autoarea;
/*     */   public double[] autosolid;
/*  69 */   public NetworkTable myautoTable = NetworkTable.getTable("GRIP/GearLines_report");
/*  70 */   public double[] defaultValue = new double[0];
/*     */   
/*     */   private JoystickButton gearIntakeButton;
/*     */   
/*     */   public JoystickButton gearPickupButtonCenter;
/*     */   
/*     */   public JoystickButton gearPickupButtonTop;
/*     */   
/*     */   public JoystickButton gearPickupButtonGround;
/*     */   
/*     */   private VictorSP leftFrontMotor;
/*     */   
/*     */   private VictorSP leftCenterMotor;
/*     */   
/*     */   private VictorSP leftRearMotor;
/*     */   
/*     */   private VictorSP rightFrontMotor;
/*     */   
/*     */   private VictorSP rightCenterMotor;
/*     */   
/*     */   private VictorSP rightRearMotor;
/*     */   
/*     */   private MultiSpeedController leftSide;
/*     */   
/*     */   private MultiSpeedController rightSide;
/*     */   
/*     */   private CANTalon gear;
/*     */   
/*     */   private Ultrasonic forward;
/*     */   
/*     */   private Accelerometer accel;
/*     */   
/*     */   private VictorSP gearIntake;
/*     */   
/*     */   private VictorSP rightclimber;
/*     */   
/*     */   private VictorSP leftclimber;
/*     */   
/*     */   private MultiSpeedController climber;
/*     */   private Button fireButton;
/*     */   private RobotDrive myDrive;
/*     */   private CvSource outputVideo;
/* 112 */   private final Object imgLock = new Object();
/*     */   
/*     */   private VisionThread visionThread;
/*     */   
/* 116 */   public double centerX = 0.0D;
/*     */   
/*     */   public double Target_Position;
/*     */   
/*     */   public double Setpoint;
/*     */   
/*     */   public double offset_from_center;
/*     */   
/* 124 */   public Command gearFeederCommand = new GearFeeder();
/* 125 */   public Command climberCommand = new Climber();
/*     */   
/*     */ 
/* 128 */   public GearPickup center = new GearPickup();
/*     */   
/*     */ 
/*     */   private JoystickButton climberbutton;
/*     */   
/*     */ 
/*     */   private JoystickButton climberbuttondown;
/*     */   
/*     */ 
/*     */   private JoystickButton gearOutTakeButton;
/*     */   
/* 139 */   Command Climberdown = new ClimberDown();
/* 140 */   Command gearFeederReverseCommand = new GearFeederReverse();
/*     */   
/*     */   public void reinit() {
/* 143 */     this.gear.setEncPosition(0);
/*     */     
/*     */ 
/* 146 */     this.gear.setPID(10.0D, 0.0D, 0.0D);
/* 147 */     this.gear.set(0.0D);
/* 148 */     this.Target_Position = 0.0D;
/*     */   }
/*     */   
/*     */   public void init() {
/* 152 */     this.stick = new Joystick(RobotMap.joystick);
/* 153 */     this.ControlCenter = new Joystick(RobotMap.controlcenter);
/*     */     
/*     */ 
/*     */ 
/* 157 */     this.gearIntakeButton = new JoystickButton(this.ControlCenter, 5);
/* 158 */     this.gearOutTakeButton = new JoystickButton(this.ControlCenter, 6);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 163 */     this.gearIntakeButton.whileHeld(this.gearFeederCommand);
/* 164 */     this.gearIntakeButton.whenReleased(new CancelGearFeeder());
/* 165 */     this.gearOutTakeButton.whileHeld(this.gearFeederReverseCommand);
/* 166 */     this.gearOutTakeButton.whenReleased(new CancelGearFeeder());
/*     */     
/* 168 */     this.climberbutton = new JoystickButton(this.ControlCenter, 8);
/* 169 */     this.climberbuttondown = new JoystickButton(this.ControlCenter, 7);
/*     */     
/* 171 */     this.climberbutton.whileHeld(this.climberCommand);
/* 172 */     this.climberbutton.whenReleased(new ClimberCancel());
/* 173 */     this.climberbuttondown.whileHeld(this.Climberdown);
/* 174 */     this.climberbuttondown.whenReleased(new ClimberCancel());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 180 */     this.accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
/*     */     
/* 182 */     this.leftFrontMotor = new VictorSP(RobotMap.frontLeftmotor);
/* 183 */     this.leftCenterMotor = new VictorSP(RobotMap.centerLeftmotor);
/* 184 */     this.leftRearMotor = new VictorSP(RobotMap.rearLeftmotor);
/*     */     
/* 186 */     this.leftSide = new MultiSpeedController(new VictorSP[] { this.leftFrontMotor, this.leftCenterMotor, this.leftRearMotor });
/*     */     
/* 188 */     this.rightFrontMotor = new VictorSP(RobotMap.frontRightmotor);
/* 189 */     this.rightFrontMotor.setInverted(true);
/* 190 */     this.rightCenterMotor = new VictorSP(RobotMap.centerRightmotor);
/* 191 */     this.rightCenterMotor.setInverted(true);
/* 192 */     this.rightRearMotor = new VictorSP(RobotMap.rearRightmotor);
/* 193 */     this.rightRearMotor.setInverted(true);
/*     */     
/* 195 */     this.gearIntake = new VictorSP(RobotMap.gearfeeder);
/*     */     
/* 197 */     this.rightSide = new MultiSpeedController(new VictorSP[] { this.rightFrontMotor, this.rightCenterMotor, this.rightRearMotor });
/*     */     
/* 199 */     this.rightclimber = new VictorSP(RobotMap.ropeclimbright);
/* 200 */     this.leftclimber = new VictorSP(RobotMap.ropeclimbleft);
/* 201 */     this.leftclimber.setInverted(true);
/*     */     
/* 203 */     this.climber = new MultiSpeedController(new VictorSP[] { this.leftclimber, this.rightclimber });
/*     */     
/* 205 */     this.myDrive = new RobotDrive(left(), right());
/*     */     
/* 207 */     this.myDrive.setSensitivity(0.8D);
/*     */     
/* 209 */     this.myDrive.setSafetyEnabled(false);
/*     */     
/* 211 */     this.gear = new CANTalon(RobotMap.gearpiece);
/*     */     
/* 213 */     this.gear.setAllowableClosedLoopErr(1);
/*     */     
/*     */ 
/* 216 */     this.gear.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
/* 217 */     this.gear.setControlMode(CANTalon.TalonControlMode.Position.value);
/*     */     
/* 219 */     this.gear.changeControlMode(CANTalon.TalonControlMode.Position);
/*     */     
/* 221 */     this.gear.enable();
/* 222 */     this.gear.enableControl();
/*     */     
/* 224 */     reinit();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 230 */     this.autocenterX = this.myautoTable.getNumberArray("centerX", this.defaultValue);
/* 231 */     this.autoarea = this.myautoTable.getNumberArray("area", this.defaultValue);
/*     */   }
/*     */   
/*     */   public MultiSpeedController left()
/*     */   {
/* 236 */     return this.leftSide;
/*     */   }
/*     */   
/*     */   public MultiSpeedController right() {
/* 240 */     return this.rightSide;
/*     */   }
/*     */   
/*     */   public RobotDrive Drive()
/*     */   {
/* 245 */     return this.myDrive;
/*     */   }
/*     */   
/*     */   public Button getFireButton() {
/* 249 */     return this.fireButton;
/*     */   }
/*     */   
/*     */   public VictorSP getLeftCenterMotor() {
/* 253 */     return this.leftCenterMotor;
/*     */   }
/*     */   
/*     */   public VictorSP getLeftRearMotor() {
/* 257 */     return this.leftRearMotor;
/*     */   }
/*     */   
/*     */   public VictorSP getRightCenterMotor() {
/* 261 */     return this.rightCenterMotor;
/*     */   }
/*     */   
/*     */   public VictorSP getRightRearMotor() {
/* 265 */     return this.rightRearMotor;
/*     */   }
/*     */   
/*     */   public CANTalon getGear()
/*     */   {
/* 270 */     return this.gear;
/*     */   }
/*     */   
/*     */   public Joystick getDriveStick()
/*     */   {
/* 275 */     return this.stick;
/*     */   }
/*     */   
/* 278 */   public VictorSP getGearIntake() { return this.gearIntake; }
/*     */   
/*     */   public VictorSP getRightClimber()
/*     */   {
/* 282 */     return this.rightclimber;
/*     */   }
/*     */   
/* 285 */   public VictorSP getLeftCLimber() { return this.leftclimber; }
/*     */   
/*     */   public MultiSpeedController getClimber() {
/* 288 */     return this.climber;
/*     */   }
/*     */   
/*     */   public JoystickButton getGearIntakeButton() {
/* 292 */     return this.gearIntakeButton;
/*     */   }
/*     */   
/*     */   public CvSource getOutputVideo() {
/* 296 */     return this.outputVideo;
/*     */   }
/*     */   
/* 299 */   public VisionThread getVisionThread() { return this.visionThread; }
/*     */   
/*     */   public Object getImgLock() {
/* 302 */     return this.imgLock;
/*     */   }
/*     */   
/*     */   public double getRange() {
/* 306 */     return this.forward.getRangeInches();
/*     */   }
/*     */   
/*     */ 
/* 310 */   public Accelerometer getAccelerometer() { return this.accel; }
/*     */   
/*     */   public double[] getLiftPosition() {
/* 313 */     double centerofMass = 0.0D;
/*     */     
/*     */ 
/*     */ 
/* 317 */     double[] returnValues = new double[2];
/* 318 */     this.autocenterX = this.myautoTable.getNumberArray("centerX", this.defaultValue);
/* 319 */     this.autoarea = this.myautoTable.getNumberArray("area", this.defaultValue);
/*     */     //double rangeTotarget;
/* 321 */     double rangeTotarget; if (this.autoarea.length == this.autocenterX.length)
/*     */     {
/* 323 */       int ind = 0;
/* 324 */       double sumOfi = 0.0D;
/* 325 */       double sumOfAt = 0.0D;
/*     */       
/* 327 */       for (ind = 0; ind < this.autocenterX.length; ind++) {
/* 328 */         sumOfi += this.autocenterX[ind] * this.autoarea[ind];
/* 329 */         sumOfAt += this.autoarea[ind];
/*     */       }
/*     */       
/* 332 */       centerofMass = sumOfi / sumOfAt;
/* 333 */       rangeTotarget = 1000.0D / Math.pow(sumOfAt, 0.25D) * 0.4649D - 55.118D - 4.0D;
/*     */     }
/*     */     else {
/* 336 */       System.out.print("Range: NaN");
/* 337 */       centerofMass = -1.0D;
/* 338 */       rangeTotarget = -1.0D;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 346 */     returnValues[0] = centerofMass;
/* 347 */     returnValues[1] = rangeTotarget;
/* 348 */     return returnValues;
/*     */   }
/*     */   
/* 351 */   public Joystick getControlCenter() { return this.ControlCenter; }
/*     */ }


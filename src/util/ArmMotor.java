package util;

import lejos.robotics.Servo;

/**
 * Obal nad tøídou Servo, který umožòuje specifikovat nìkolik dodateèných parametrù, jako je: smìr rotace, mechanický pøevod atd..
 */
public class ArmMotor {
	private Servo servo;
	private boolean positiveDirection;
	
	/**
	 * @param servo - Servomotor, který tøída obaluje
	 * @param positiveDirection - specifikuje, zda se motor otáèí ve smìru hodinových ruèièek 
	 */
	public ArmMotor(Servo servo, boolean positiveDirection) {
		assert(servo != null);
		this.servo = servo;
		this.positiveDirection = positiveDirection;
	}
	
	public void setAngle(float angle) {
		servo.setAngle(angle);
	}
	
	public float getAngle() {
		 if(positiveDirection) return 360 - servo.getAngle();
		 return servo.getAngle();
	}
}

package util;

import lejos.nxt.NXTRegulatedMotor;
/**
 * Obal nad tøídou Servo, který umožòuje specifikovat nìkolik dodateèných parametrù, jako je: smìr rotace, mechanický pøevod atd..
 */
public class ArmMotor {
	private NXTRegulatedMotor servo;
	private boolean positiveDirection;
	
	/**
	 * @param servo - Servomotor, který tøída obaluje
	 * @param positiveDirection - specifikuje, zda se motor otáèí ve smìru hodinových ruèièek 
	 */
	public ArmMotor(NXTRegulatedMotor servo, boolean positiveDirection) {
		assert(servo != null);
		this.servo = servo;
		this.positiveDirection = positiveDirection;
	}
	
	public void setAngle(int angle) {
		servo.rotateTo(angle);
	}
	
	public float getAngle() {
		 if(positiveDirection) return 360 - servo.getTachoCount() % 360;
		 return servo.getTachoCount() % 360;
	}
}

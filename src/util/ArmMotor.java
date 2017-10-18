package util;

import lejos.robotics.Servo;

/**
 * Obal nad t��dou Servo, kter� umo��uje specifikovat n�kolik dodate�n�ch parametr�, jako je: sm�r rotace, mechanick� p�evod atd..
 */
public class ArmMotor {
	private Servo servo;
	private boolean positiveDirection;
	
	/**
	 * @param servo - Servomotor, kter� t��da obaluje
	 * @param positiveDirection - specifikuje, zda se motor ot��� ve sm�ru hodinov�ch ru�i�ek 
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

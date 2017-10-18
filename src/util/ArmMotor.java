package util;

import lejos.nxt.NXTRegulatedMotor;
/**
 * Obal nad t��dou Servo, kter� umo��uje specifikovat n�kolik dodate�n�ch parametr�, jako je: sm�r rotace, mechanick� p�evod atd..
 */
public class ArmMotor {
	private NXTRegulatedMotor servo;
	private boolean positiveDirection;
	
	/**
	 * @param servo - Servomotor, kter� t��da obaluje
	 * @param positiveDirection - specifikuje, zda se motor ot��� ve sm�ru hodinov�ch ru�i�ek 
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

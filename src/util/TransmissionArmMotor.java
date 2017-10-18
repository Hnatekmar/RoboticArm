package util;

import lejos.nxt.NXTRegulatedMotor;

/**
 * Reprezentuje motor s p�evodem
 */
public class TransmissionArmMotor extends ArmMotor {
	private float transmissionNumber;
	public TransmissionArmMotor(NXTRegulatedMotor servo, boolean positiveDirection, float transmissionNumber) {
		// Servo ot��� motorem v opa�n�m sm�ru
		super(servo, !positiveDirection);
		this.transmissionNumber = transmissionNumber;
	}
	
	@Override
	public float getAngle() {
		// TODO Auto-generated method stub
		return super.getAngle() * transmissionNumber;
	}
}

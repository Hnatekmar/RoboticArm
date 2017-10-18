package com.mendelu.mt;

import util.Point3D;

public class Arm {
	
	private Joint arm;
	
	public Arm(Joint arm) {
		assert(arm != null);
		this.arm = arm;
	}
	
	/**
	 * Provede p��mou �lohu kinematiky a vr�t� v�slednou pozici afektoru
	 * @return pozice afektoru - Point3D
	 */
	public Point3D getAffectorPosition() {
		return this.arm.affectorPosition();
	}
}

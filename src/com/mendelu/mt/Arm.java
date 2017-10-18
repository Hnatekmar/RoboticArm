package com.mendelu.mt;

import util.Point3D;

public class Arm {
	
	private Joint arm;
	
	public Arm(Joint arm) {
		assert(arm != null);
		this.arm = arm;
	}
	
	/**
	 * Provede pøímou úlohu kinematiky a vrátí výslednou pozici afektoru
	 * @return pozice afektoru - Point3D
	 */
	public Point3D getAffectorPosition() {
		return this.arm.affectorPosition();
	}
}

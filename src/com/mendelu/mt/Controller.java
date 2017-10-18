package com.mendelu.mt;

import lejos.nxt.Motor;
import util.ArmMotor;
import util.Axis;

public class Controller {
	public static void main(String[] args) {
		// Motor je tøeba nahradit za správný (nepamatuji si jaký port, patøí kterému motoru)
		Joint b = new Joint(new ArmMotor(Motor.B, true), Axis.Z, 12.5f, null, "Joint C");
		Joint a = new Joint(new ArmMotor(Motor.A, true), Axis.Z, 6.5f, b, "Joint B");
		Joint base = new Joint(70, Axis.Z, 15.5f, a, "Constant rotation");
		Joint c = new Joint(new ArmMotor(Motor.C, true), Axis.Y, 0.0f, base, "Joint A");
		Arm arm = new Arm(base);
	}
}

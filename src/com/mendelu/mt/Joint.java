package com.mendelu.mt;

import java.util.Queue;
import java.util.Stack;

import lejos.util.Matrix;
import util.ArmMotor;
import util.Axis;
import util.Point3D;

/**
 * Pøedstavuje kloub, který je otáèen motorem a pohybuje s dalším kloubem. 
 * Pokud je další kloub null, tak se jedná o afektor
 */
public class Joint {
	private ArmMotor motor;
	private Joint nextJoint;
	private float distance;
	private Axis axis;
	private String name;
	private double rotation;
	
	/**
	 * @param motor - Motor, který rotoje kloubem
	 * @param axis - Osa rotace
	 * @param distance - Vzdálenost od dalšího bodu
	 * @param next - Další kloub
	 * @param name - Název kloubu
	 */
	public Joint(ArmMotor motor, Axis axis, float distance, Joint next, String name) {
		assert(motor != null);
		assert(distance > 0.0f);
		this.motor = motor;
		this.axis = axis;
		this.distance = distance;
		this.nextJoint = next;
		this.name = name;
	}
	
	/**
	 * Zkonstruuje kloub, který natáèí bod o nemìnný úhel (hodí se pro reprezentaci báze robotické ruky)
	 * @param constantRotation Rotace
	 * @param axis - Osa rotace
	 * @param distance - Vzdálenost od dalšího bodu
	 * @param next - Další kloub
	 * @param name - Název kloubu
	 */
	public Joint(double constantRotation, Axis axis, float distance, Joint next, String name) {
		this.rotation = constantRotation;
		this.motor = null;
		this.axis = axis;
		this.distance = distance;
		this.nextJoint = next;
		this.name = name;
	}

	
	/**
	 * Vrací následující kloub
	 * @return Joint - kloub, který navazuje na tento kloub
	 */
	public Joint nextJoint() {
		return nextJoint;
	}
	
	/**
	 * Vrací rotaèní matici na základì osy, kterou kloub otáèí
	 * @return Matrix - rotaèní matice
	 */
	private Matrix getRotationMatrix() {
		double angle = motor != null ? Math.toRadians((double)motor.getAngle()) : rotation;
		double[][] matrixArr = null;
		double sine = Math.sin(angle);
		double cosine = Math.cos(angle);
		if(axis == Axis.X) {
			matrixArr = new double[][] {
				{1.0, 0.0, 0.0, 0.0},
				{0.0, cosine, -sine, 0.0},
				{0.0, sine, cosine, 0.0},
				{0.0, 0.0, 0.0, 1.0}
			};
		} else if(axis == Axis.Y) {
			matrixArr = new double[][] {
				{cosine, 0.0, sine, 0.0},
				{0.0, 1.0, 0.0, 0.0},
				{-sine, 0.0, cosine, 0.0},
				{0.0, 0.0, 0.0, 1.0}
			};
		} else if(axis == Axis.Z) {
			matrixArr = new double[][] {
				{cosine, -sine, 0.0, 0.0},
				{sine, cosine, 0.0, 0.0},
				{0.0, 0.0, 1.0, 0.0},
				{0.0, 0.0, 0.0, 1.0}
			};
		}
		assert(matrixArr != null);
		return new Matrix(matrixArr);
	}
	
	/**
	 * Vrátí matici, která posune bod o délku v xové ose
	 * @return Matrix - transformaèní matice
	 */
	private Matrix getMoveMatrix() {
		double[][] matrixArr = new double[][] {
			{1.0, 0.0, 0.0, this.distance},
			{0.0, 1.0, 0.0, 0.0},
			{0.0, 0.0, 1.0, 0.0},
			{0.0, 0.0, 0.0, 1.0}
		};
		return new Matrix(matrixArr);
	}
	
	/**
	 * Provede pøímou úlohu kinematiky
	 * @return Point3D - pozice afektoru
	 */
	public Point3D affectorPosition() {
		Stack<Joint> joints = new Stack<>();
		Joint it = this;
		while(it != null) {
			joints.push(it);
			it = it.nextJoint;
		}
		
		double[][] pointArr = new double[][] {
			{0.0, 0.0, 0.0, 1.0}
		};
		
		Matrix point = new Matrix(pointArr);
		point = point.transpose();
		Matrix transformationMatrix = Matrix.identity(4, 4);
		while(!joints.empty()) {
			Joint current = joints.pop();
			transformationMatrix =  current.getMoveMatrix().times(transformationMatrix.times(current.getRotationMatrix()));
		}
		point = transformationMatrix.times(point);
		return new Point3D(point.get(0, 0), point.get(0, 1), point.get(0, 2));
	}
}

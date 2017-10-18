package util;

/**
 * Tøída reprezentující bod v 3D prostoru
 */
public class Point3D {
	private double x;
	private double y;
	private double z;
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o.getClass() == getClass()) {
			Point3D other = (Point3D)o;
			return other.x == this.x && other.y == this.y && other.z == this.z;
		}
		return false;
	}
}

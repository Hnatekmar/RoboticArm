package util;

/**
 * Tøída reprezentující bod v 3D prostoru
 */
public class Point3D {
	private float x;
	private float y;
	private float z;
	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
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

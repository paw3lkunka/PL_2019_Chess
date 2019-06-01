
public class Vector3 {
	float x;
	float y;
	float z;
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	public Vector3(Vector3 v) {
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}

	public float[] getVector3() {
		float[] a = {x,y,z};
		return a;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public String toString() {
		return "X= "+x+"  Y= "+y+"  Z= "+z;
	}
	
	public void mult(int a) {
		this.x = x * a;
		this.y = y * a;
		this.z = z * a;
	}
}


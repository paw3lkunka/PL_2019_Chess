package Processing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import XML.XMLSerializable;

public class Vector3 implements XMLSerializable {
	private float x;
	private float y;
	private float z;
	
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
	
	public Vector3 (Element element) {
		
		this.x = Float.parseFloat( element.getElementsByTagName("X").item(0).getTextContent() );
		this.y = Float.parseFloat( element.getElementsByTagName("Y").item(0).getTextContent() );
		this.z = Float.parseFloat( element.getElementsByTagName("Z").item(0).getTextContent() );

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

	public String toChessString() {
		String str = new String();
		
		switch((int)this.x)
		{
		case 0: str="A"; break;
		case 1: str="B"; break;
		case 2: str="C"; break;
		case 3: str="D"; break;
		case 4: str="E"; break;
		case 5: str="F"; break;
		case 6: str="G"; break;
		case 7: str="H"; break;
		default: str+=(((int)this.x)+1)+" | "; break;
		}
		
		return str+(int)this.y;
	}
	
	public void mult(int a) {
		this.x = x * a;
		this.y = y * a;
		this.z = z * a;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Vector3 other = (Vector3) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x)) {
			return false;
		}
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y)) {
			return false;
		}
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z)) {
			return false;
		}
		return true;
	}
	
	public Element saveXML(Document document) {

            // root element
            Element root = document.createElement("Vector3");
            
            Element ex = document.createElement("X");
            ex.appendChild(document.createTextNode(x+""));
            root.appendChild(ex);
            
            Element ey = document.createElement("Y");
            ey.appendChild(document.createTextNode(y+""));
            root.appendChild(ey);
            
            Element ez = document.createElement("Z");
            ez.appendChild(document.createTextNode(z+""));
            root.appendChild(ez);

		
		return root;
	}
}


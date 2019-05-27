import processing.core.PApplet;

public class PositionedObject {
	
	protected final PApplet parent;
	private Vector3 pos;
	
	public PositionedObject(PApplet parent, Vector3 pos) {
		super();
		this.parent = parent;
		this.pos = pos;
	}
	
	public void place()
	{
		parent.translate(pos.getX(), pos.getY(), pos.getZ());
	}
}

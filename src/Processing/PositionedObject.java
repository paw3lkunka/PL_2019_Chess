package Processing;
import processing.core.PApplet;

/**
 * The Class PositionedObject.
 * @author Piotr Ruciñski
 */
public class PositionedObject {
	
	/** The parent. */
	protected final PApplet parent;
	
	/** The pos. */
	private Vector3 pos;
	
	/**
	 * Instantiates a new positioned object.
	 *
	 * @param parent the instance of PApplet containing object
	 * @param pos the position of object in PApplet
	 */
	public PositionedObject(PApplet parent, Vector3 pos) {
		super();
		this.parent = parent;
		this.pos = pos;
	}
	
	/**
	 * Places object in processing.
	 */
	public void place()
	{
		parent.translate(pos.getX(), pos.getY(), pos.getZ());
	}
}

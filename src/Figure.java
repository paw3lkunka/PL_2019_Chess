import java.util.List;
import processing.core.PApplet;
import processing.core.PShape;

public abstract class Figure {

	protected final PApplet parent;
	Color color;
	Vector3 position;
	PShape shape;
	
	public Figure(PApplet parent, Vector3 position, Color color) {
		this.parent = parent;
		this.color = color;
		this.position = position;
	}
	
	public abstract List<Vector3> getPossibleMoves();
	
	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public PShape getShape() {
		return shape;
	}

	public void setShape(String path) {
		this.shape = parent.loadShape(path);
	}
	
	public List<Vector3> removeAnomalies(List<Vector3> list)
	{
		for(int i=0; i<list.size(); ++i)
			if(list.get(i).getX() > 7 || list.get(i).getX() < 0 ||
			   list.get(i).getY() > 7 || list.get(i).getY() < 0)
				{
				list.remove(i);
				i--;
				}
		
		return list;
	}

}

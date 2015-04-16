
public class pathNode {
	public int id, x, y;
	private int h_value, g_value, f_value;
	private pathNode parent = null;
	public boolean walkable, closed;
	
	public pathNode(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setH(int h){
		this.h_value = h;
	}
	public void setG(int g){
		this.g_value = g;
	}
	public void setParent(pathNode parent){
		this.parent = parent;
	}
	
	public int getH(){
		return h_value;
	}
	public int getG(){
		return g_value;
	}
	public int getF(){
		return h_value+g_value;
	}
	public pathNode getParent(){
		return parent;
	}
}

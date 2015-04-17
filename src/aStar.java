import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class aStar{
	
	pathNode[] p = new pathNode[100];
	
	int[][] map = {	{1,1,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,0,0,0,0,1},
					{1,0,1,0,1,1,0,1,9,1},
					{1,0,1,0,0,1,0,1,1,1},
					{1,0,1,1,0,0,0,0,1,1},
					{1,0,0,0,0,1,1,0,1,1},
					{1,0,1,1,0,0,1,0,0,1},
					{1,0,1,1,1,0,1,1,0,1},
					{1,9,0,0,0,0,0,0,0,1},
					{1,1,1,1,1,1,1,1,1,1} };
	
	pathNode target = new pathNode(9,9);
	
	List<pathNode> pathList 	= new ArrayList<pathNode>();
	PriorityQueue<pathNode> openList = new PriorityQueue<pathNode>(1000, new Comparator<pathNode>(){
		public int compare(pathNode a, pathNode b) {
			return a.getF()-b.getF();
		} 
	});
	List<pathNode> closedList 	= new ArrayList<pathNode>();
	
	public aStar(){

		for(int i=0; i<100; i++){
			pathList.add(p[i] = new pathNode((i%10)+1,(i/10)+1));
			p[i].setH(distance(p[i],target));
			p[i].walkable = true;
			p[i].id = i;
		}
		
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				if(map[i][j] == 1){
					p[i+j].walkable = false;
				}
			}
		}
		
		nextNode(12,this.target);
		
		for(pathNode k: closedList){
			System.out.println("P"+k.id+" "+"Vorgänger:"+k.getParent().id);
		}
	}
	
	public int nextNode(int n, pathNode target){
		
		if((p[n].x == target.x) && (p[n].y == target.y))
		{
			return -1;
		}
		
		if((n-1)%10 < n%10){
			if(p[n-1].walkable && !p[n-1].closed){
				if((p[n-1].getParent() == null) || (p[n-1].getParent().getG() > p[n].getG())){
					p[n-1].setParent(p[n]);
					p[n-1].setG(p[n-1].getParent().getG()+10);
					openList.add(p[n-1]);
				}
			}
		}
		if((n+1)%10 > n%10){
			if(p[n+1].walkable && !p[n-1].closed){
				if((p[n+1].getParent() == null) || (p[n-1].getParent().getG() > p[n].getG())){
					p[n+1].setParent(p[n]);
					p[n+1].setG(p[n+1].getParent().getG()+10);
					openList.add(p[n+1]);
				}
			}
		}
		if((n-10) > 0){
			if(p[n-10].walkable && !p[n-1].closed){
				if((p[n-10].getParent() == null) || (p[n-10].getParent().getG() > p[n].getG())){
					p[n-10].setParent(p[n]);
					p[n-10].setG(p[n-10].getParent().getG()+10);
					openList.add(p[n-10]);
				}
			}
		}
		if((n+10) < 100 ){
			if(p[n+10].walkable && !p[n-1].closed){
				if((p[n+10].getParent() == null) || (p[n+10].getParent().getG() > p[n].getG())){
					p[n+10].setParent(p[n]);
					p[n+10].setG(p[n+10].getParent().getG()+10);
					openList.add(p[n+10]);
				}
			}
		}
		

		//System.out.println("Knoten: "+openList.peek().id+" "+openList.peek().getF()+" "+openList.peek().getParent().id);
		closedList.add(openList.peek());
		
		if(openList.isEmpty()){
			return -1;
		}
		
		return nextNode(openList.remove().id, target);
	}
	
	
	
	public int distance(pathNode a, pathNode b){
		return (int) (10*(Math.sqrt(Math.pow((a.x - b.x), 2)+Math.pow((a.y - b.y), 2))));
	}
}

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class aStar{
	
	pathNode[] p = new pathNode[25];
	
	pathNode target = new pathNode(5,5);
	
	List<pathNode> pathList 	= new ArrayList<pathNode>();
	PriorityQueue<pathNode> openList = new PriorityQueue<pathNode>(1000, new Comparator<pathNode>(){
		public int compare(pathNode a, pathNode b) {
			return a.getF()-b.getF();
		} 
	});
	List<pathNode> closedList 	= new ArrayList<pathNode>();
	
	public aStar(){

		for(int i=0; i<25; i++){
			pathList.add(p[i] = new pathNode((i%5)+1,(i/5)+1));
			p[i].setH(distance(p[i],target));
			p[i].walkable = true;
			p[i].id = i;
		}
		
		System.out.println(nextNode(1,this.target));
	}
	
	public int nextNode(int n, pathNode target){
		
		if((p[n].x == target.x) && (p[n].y == target.y))
		{
			return 99;
		}
		
		if((n-1)%5 < n%5){
			if(p[n-1].walkable){
				if((p[n-1].getParent() == null) || (p[n-1].getParent().getG() > p[n].getG())){
					p[n-1].setParent(p[n]);
					p[n-1].setG(p[n-1].getParent().getG()+10);
					openList.add(p[n-1]);
				}
			}
		}
		if((n+1)%5 > n%5){
			if(p[n+1].walkable){
				if((p[n+1].getParent() == null) || (p[n-1].getParent().getG() > p[n].getG())){
					p[n+1].setParent(p[n]);
					p[n+1].setG(p[n+1].getParent().getG()+10);
					openList.add(p[n+1]);
				}
			}
		}
		if((n-5) > 0){
			if(p[n-5].walkable){
				if((p[n-5].getParent() == null) || (p[n-5].getParent().getG() > p[n].getG())){
					p[n-5].setParent(p[n]);
					p[n-5].setG(p[n-5].getParent().getG()+10);
					openList.add(p[n-5]);
				}
			}
		}
		if((n+5) < 25 ){
			if(p[n+5].walkable){
				if((p[n+5].getParent() == null) || (p[n+5].getParent().getG() > p[n].getG())){
					p[n+5].setParent(p[n]);
					p[n+5].setG(p[n+5].getParent().getG()+10);
					openList.add(p[n+5]);
				}
			}
		}
		
		return openList.remove().id;
		
//		System.out.println("P3: "+p[1].getF());
//		System.out.println("P6: "+p[12].getF());
//		System.out.println("P6: "+p[8].getF());
//		System.out.println("P6: "+p[23].getF());
//		
//		openList.add(p[1]);
//		openList.add(p[12]);
//		openList.add(p[8]);
//		openList.add(p[23]);
				
//		while(!openList.isEmpty()){
//			System.out.println(openList.remove().getF());
//		}
	}
	
	
	
	public int distance(pathNode a, pathNode b){
		return (int) (10*(Math.sqrt(Math.pow((a.x - b.x), 2)+Math.pow((a.y - b.y), 2))));
	}
}

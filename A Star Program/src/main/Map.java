package main;

import java.awt.Color;
import java.util.ArrayList;

import window.WindowManager;

public class Map {
	
	public Node startingNode;
	public Node endingNode;
	
	public Node[][] node;
	
	public boolean hasBeenGenerated = false;
	
	public void generateMap() {
		node = new Node[WindowManager.width/WindowManager.blockSize][WindowManager.height/WindowManager.blockSize];
		for (int x=0; x<WindowManager.width/WindowManager.blockSize; x++) {
			for (int y=0; y<WindowManager.height/WindowManager.blockSize; y++) {
				node[x][y] = new Node(x, y, this);
			}
		}
		startingNode = node[0][0];
		endingNode = node[WindowManager.width/WindowManager.blockSize-1][WindowManager.height/WindowManager.blockSize-1];
		for (int x=0; x<WindowManager.width/WindowManager.blockSize; x++) {
			for (int y=0; y<WindowManager.height/WindowManager.blockSize; y++) {
				node[x][y].generateV();
			}
		}
		hasBeenGenerated = true;
	}
	
	public class Node implements Comparable<Node>{
		
		public int x = 0;
		public int y = 0;
		
		public double v = 0;
		
		private Map map;
		
		private ArrayList<Node> myNodes;
		
		public Node(int x, int y, Map map) {
			this.x = x;
			this.y = y;
			this.map = map;
		}
		
		public void generateV() {
			v = (this.distance(startingNode)+this.distance(endingNode));
		}
		
		public double distance(Node that) {
			return Math.sqrt(Math.pow(that.x-this.x, 2) + Math.pow(that.y-this.y, 2));
		}
		
		public ArrayList<Node> getSurroundingNodes() {
			ArrayList<Node> r = new ArrayList<Node>();
			for (int x=-1; x<2;x++) {
				if (x != 0) {
					if (this.x+x > -1 && this.x+x < WindowManager.width/WindowManager.blockSize) {
						if (!Grid.grid[this.x+x][this.y].equals(Color.BLACK) && !Simulation.surroundingNodes.contains(node[this.x+x][this.y])) {
							r.add(map.node[this.x+x][this.y]);
						}
					}
				}
			}
			for (int y=-1; y<2; y++) {
				if (y != 0) {
					if (this.y+y > -1 && this.y+y < WindowManager.height/WindowManager.blockSize) {
						if (!Grid.grid[this.x][this.y+y].equals(Color.BLACK) && !Simulation.surroundingNodes.contains(node[this.x][this.y+y])) {
							r.add(map.node[this.x][this.y+y]);
						}
					}
				}
			}
			myNodes = r;
			return r;
		}
		
		public int compareTo(Node that) {
			if (this.v > that.v) {
				return 1;
			}else if (this.v < that.v) {
				return -1;
			}else {
				return 0;
			}
		}
		
	}
	
}

package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import main.Map.Node;
import window.MainPanel;
import window.WindowManager;

public class Simulation {
	
	public static boolean running = false;
	
	public static ArrayList<Node> activeNodes;
	public static ArrayList<Node> surroundingNodes;
	public static Stack<Node> path;
	
	public static Map map;
	
	private static Thread thread;
	
	public static void init() {
		map = new Map();
		activeNodes = new ArrayList<Node>();
		surroundingNodes = new ArrayList<Node>();
		path = new Stack<Node>();
		map.generateMap();
		declareThread();
	}
	
	private static void declareThread() {
		thread = new Thread() {
			public void run() {
				while (running) {
					if (path.size() > 0) {
						Node node = path.peek();
						ArrayList<Node> ns = node.getSurroundingNodes();
						if (ns.size() == 0) {
							path.pop();
						}else {
							
							surroundingNodes.addAll(ns);
						}
						
					}
					
					WindowManager.mainPanel.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
				}
			}
		};
	}
	
	public static void onClickSimulation() {
		
		running = !running;
		if (running) {
			activeNodes.add(map.startingNode);
			path.add(map.startingNode);
			thread.start();
			
			WindowManager.mainPanel.repaint();
		}else {
			thread.interrupt();
			declareThread();
			activeNodes.clear();
			path.clear();
			
			WindowManager.mainPanel.repaint();
		}
		
	}
	
}

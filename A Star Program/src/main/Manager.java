package main;

import window.WindowManager;

public class Manager {
		
	public static void main(String[] args) {
		Grid.init();
		WindowManager.init();
		Editor.init();
		Simulation.init();
	}
	
}

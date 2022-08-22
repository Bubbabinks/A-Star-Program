package main;

import java.awt.Color;

import window.WindowManager;

public class Grid {
	
	public static Color[][] grid;
	
	public static void init() {
		grid = new Color[WindowManager.width/WindowManager.blockSize][WindowManager.height/WindowManager.blockSize];
		for (int x=0; x<WindowManager.width/WindowManager.blockSize; x++) {
			for (int y=0; y<WindowManager.height/WindowManager.blockSize; y++) {
				grid[x][y] = Color.WHITE;
			}
		}
		grid[0][0] = new Color(56, 161, 193);
		grid[WindowManager.width/WindowManager.blockSize-1][WindowManager.height/WindowManager.blockSize-1] = new Color(234, 82, 51);
	}
	
}

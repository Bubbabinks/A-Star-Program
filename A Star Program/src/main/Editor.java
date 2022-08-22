package main;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import window.MainPanel;
import window.WindowManager;

public class Editor {
	
	private static MainPanel mainPanel;
	
	public static void init() {
		mainPanel = WindowManager.mainPanel;
		mainPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				Editor.onClick(e);
			}
		});
	}
	
	public static void onClick(MouseEvent e) {
		if (!Simulation.running) {
			Point point = e.getPoint();
			int x = Math.floorDiv(point.x, WindowManager.blockSize);
			int y = Math.floorDiv(point.y, WindowManager.blockSize);
			if (Grid.grid[x][y].equals(Color.WHITE)) {
				Grid.grid[x][y] = Color.BLACK;
			}else if (Grid.grid[x][y].equals(Color.BLACK)) {
				Grid.grid[x][y] = Color.WHITE;
			}else {
				Simulation.onClickSimulation();
			}
			mainPanel.repaint();
		}else {
			Simulation.onClickSimulation();
		}
	}
	
}

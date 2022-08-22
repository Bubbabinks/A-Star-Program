package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Grid;
import main.Map.Node;
import main.Simulation;

public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public MainPanel() {
		setPreferredSize(new Dimension(WindowManager.width, WindowManager.height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Draw Blocks
		for (int x=0; x<WindowManager.width; x+=WindowManager.blockSize) {
			for (int y=0; y<WindowManager.height; y+=WindowManager.blockSize) {
				g.setColor(Grid.grid[x/WindowManager.blockSize][y/WindowManager.blockSize]);
				g.fillRect(x, y, WindowManager.blockSize, WindowManager.blockSize);
			}
		}
		
		//Drawing Active Blocks
		if (Simulation.running) {
			for (Node node: Simulation.activeNodes) {
				g.setColor(Color.GREEN);
				g.fillRect(node.x*WindowManager.blockSize, node.y*WindowManager.blockSize, WindowManager.blockSize, WindowManager.blockSize);
			}
			for (Node node: Simulation.surroundingNodes) {
				g.setColor(Color.YELLOW);
				g.fillRect(node.x*WindowManager.blockSize, node.y*WindowManager.blockSize, WindowManager.blockSize, WindowManager.blockSize);
			}
			for (Node node: Simulation.path) {
				g.setColor(Color.RED);
				g.fillRect(node.x*WindowManager.blockSize, node.y*WindowManager.blockSize, WindowManager.blockSize, WindowManager.blockSize);
			}
		}
		
		//Draw Grid
		for (int x=0; x<WindowManager.width; x+=WindowManager.blockSize) {
			for (int y=0; y<WindowManager.height; y+=WindowManager.blockSize) {
				g.setColor(Color.BLACK);
				g.drawRect(x, y, WindowManager.blockSize, WindowManager.blockSize);
			}
		}
		
		//Draw V numbers
		if (Simulation.running && Simulation.map.hasBeenGenerated) {
			Node[][] node = Simulation.map.node;
			FontMetrics f = g.getFontMetrics();
			for (int x=0; x<WindowManager.width/WindowManager.blockSize; x++) {
				for (int y=0; y<WindowManager.height/WindowManager.blockSize; y++) {
					int XO = WindowManager.blockSize/2-f.stringWidth((int)node[x][y].v+"")/2;
					int YO = WindowManager.blockSize/2+f.getAscent()/2;
					g.drawString((int)node[x][y].v+"", x*WindowManager.blockSize+XO, y*WindowManager.blockSize+YO);
				}
			}
		}
	}

}

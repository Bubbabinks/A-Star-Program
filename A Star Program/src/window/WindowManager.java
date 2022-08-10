package window;

import javax.swing.JFrame;

public class WindowManager {
	
	public static MainPanel mainPanel;
	
	public static void init() {
		JFrame frame = new JFrame("A*");
		mainPanel = new MainPanel();
		frame.setContentPane(mainPanel);
	}
	
}

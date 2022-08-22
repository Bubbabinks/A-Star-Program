package window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class WindowManager {
	
	public static final int width = 700, height = 700, blockSize = 50;
	public static MainPanel mainPanel;
	
	public static void init() {
		JFrame frame = new JFrame("A*");
		mainPanel = new MainPanel();
		frame.setContentPane(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
	
}

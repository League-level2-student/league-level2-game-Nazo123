package JungleExplorer;

import javax.swing.JFrame;

public class JungleExplorer {

	public final static int WIDTH = 1400;
	public final static int HEIGHT = 750;
	GamePanel gamePanel;
	JFrame frame;
	public static void main(String[] args) {

		JungleExplorer e = new JungleExplorer();
		
		e.setup();

	}

	 JungleExplorer() {
	frame = new JFrame();
gamePanel = new GamePanel();
	}

	void setup() {
		frame.add(gamePanel);
		frame.addKeyListener(gamePanel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
}
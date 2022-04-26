package bubble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame{
	
	private JLabel backgroundMap;
	
	private Player player;
	
	public boolean w, a, s, d, space;
	
	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}
	
	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		
		player = new Player();
		add(player);
		
		//backgroundMap.setSize(1000, 600);
		//add(backgroundMap);
	}
	
	private void initSetting() {
		w = false;
		a = false;
		s = false;
		d = false;
		space = false;
		
		setSize(1000, 640);
		getContentPane().setLayout(null);
		
		setLocationRelativeTo(null); // JFrame 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {				
				switch(e.getKeyCode()) {
					case KeyEvent.VK_W:
						if (!player.isUp() && !player.isDown())
						{
							player.up();
						}
						break;
					case KeyEvent.VK_A:
						if (!player.isLeft() && !player.isRight() && !player.isLeftWallCollide())
						{
							player.left();
						}
						break;
					//case KeyEvent.VK_S:
					//	player.down();
					//	break;
					case KeyEvent.VK_D:
						if (!player.isRight() && !player.isLeft() && !player.isRightWallCollide())
						{
							player.right();
						}
						break;
					case KeyEvent.VK_SPACE:
						if (!player.isUp() && !player.isDown()) {
							player.up();
						}
				}
			}
			
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_W:
						player.setUp(false);
						break;
					case KeyEvent.VK_A:
						player.setLeft(false);
						break;
					case KeyEvent.VK_D:
						player.setRight(false);
						break;
					case KeyEvent.VK_SPACE:
						break;
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}
}

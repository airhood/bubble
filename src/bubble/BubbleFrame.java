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
	
	public int num;
	
	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
		
		new Thread(() -> {
			while(true) {
				movementKeyInputProcess();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		while(true) {
			System.out.println("player x: " + player.getX() + " | player y: " + player.getY());
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
						movementKeyInput("w", true);
						break;
					case KeyEvent.VK_A:
						movementKeyInput("a", true);
						break;
					case KeyEvent.VK_D:
						movementKeyInput("d", true);
						break;
					case KeyEvent.VK_SPACE:
						movementKeyInput("space", true);
				}
			}
			
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_W:
						movementKeyInput("w", false);
						break;
					case KeyEvent.VK_A:
						movementKeyInput("a", false);
						break;
					case KeyEvent.VK_D:
						movementKeyInput("d", false);
						break;
					case KeyEvent.VK_SPACE:
						movementKeyInput("space", false);
						break;
				}
			}
		});
	}
	
	
	private void movementKeyInput(String key, boolean state) {
		//System.out.println("key : " + key + " | state : " + state);
		
		switch(key) {
			case "w":
				w = state;
				break;
			case "a":
				a = state;
				break;
			case "d":
				d = state;
				break;
			case "space":
				space = state;
				break;
		}
	}
	
	private void movementKeyInputProcess() {
		if (w || space) {
			if (!player.isUp() && !player.isDown() && !s) {
				player.up();
			}
		}
		
		if (a) {
			if (!player.isLeft() && !player.isRight() && !player.isLeftWallCollide() && !d)
			{
				player.left();
			}
		} else {
			player.setLeft(false);
		}
		
		if (d) {			
			if (!player.isRight() && !player.isLeft() && !player.isRightWallCollide() && !a)
			{
				player.right();
			}
		} else  {
			player.setRight(false);
		}
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}
}

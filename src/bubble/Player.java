package bubble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {

	private int x;
	private int y;
  
	private boolean left, right, up, down;
	
	private boolean leftWallCollide, rightWallCollide;

	private ImageIcon playerR, playerL;
	
	
	private final int moveSpeed = 3;
	private final int jumpSpeed = 4;
	
	private final int defaultX = 120;
	private final int defaultY = 531;
	
	private int currentFloor;
	
	private BackgroundPlayerService BPS;
	
	private boolean standing;
	

	public Player() {
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSetting() {
		x = defaultX;
		y = defaultY;

		left = false;
		right = false;
		up = false;
		down = false;
		
		currentFloor = 1;
		
		leftWallCollide = false;
		rightWallCollide = false;
		
		standing = true;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}

	@Override
	public void left() {
		left = true;
		new Thread(() -> {
			while (left) {
				setIcon(playerL);
				x = x - moveSpeed;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	@Override
	public void right() {
		right = true;
		new Thread(() -> {
			while (right) {
				setIcon(playerR);
				x = x + moveSpeed;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void up() {
		up = true;
		new Thread(() -> {
			for(int i = 0; i < 120 / jumpSpeed; i++) {
				y  = y - jumpSpeed;
				setLocation(x, y);
				try {
					Thread.sleep(9);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			currentFloor++;
			
			up = false;
			down();
			
		}).start();
	}

	@Override
	public void down() {
		down = true;
		new Thread(() -> {
			while(down) {
				y = y + jumpSpeed;
				setLocation(x, y);
				try {
					Thread.sleep(9);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if (currentFloor <= 4) {
//				switch ((int)Math.floor(defaultY - y / 120)) {
//				case 0:
//					currentFloor = 1;
//					break;
//				case 1:
//					currentFloor = 2;
//					break;
//				case 2:
//					currentFloor = 3;
//					break;
//				case 3:
//					currentFloor = 4;
//					break;
//				}
				
				if (y <= 536 && y > 420) {
					currentFloor = 1;
				} else if (y <= 420 && y > 330) {
					currentFloor = 2;
				} else if (y <= 330 && y > 280) {
					currentFloor = 3;
				} else if (y <= 280) {
					currentFloor = 4;
				}
				
				//y = defaultY - ((currentFloor - 1) * 120);
				
				switch(currentFloor) {
					case 1:
						y = 531;
						break;
					case 2:
						y = 415;
						break;
					case 3:
						y = 295;
						break;
					case 4:
						y = 175;
						break;
						
				}
			}
			
			setLocation(x, y);
			
			down = false;
		}).start();
	}
}

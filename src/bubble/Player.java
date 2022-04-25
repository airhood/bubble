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

	public boolean left, right, up, down;

	private ImageIcon playerR, playerL;
	
	
	private int speed = 3;
	

	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSetting() {
		x = 55;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		left = true;
		new Thread(() -> {
			while (left) {
				setIcon(playerL);
				x = x - speed;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
				x = x + speed;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void up() {
		up = true;
		new Thread(() -> {
			while (up) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void down() {

	}
}

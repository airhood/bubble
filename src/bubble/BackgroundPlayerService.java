package bubble;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable{

	private BufferedImage image;
	private Player player;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("src/image/backgroundMapService.png"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(true) {
			Color leftColor = new Color(image.getRGB(player.getX() + 1, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 10, player.getY() + 25));
			int bottomColorLeft = image.getRGB(player.getX() + 50 + 0, player.getY() + 50 + 5);
			int bottomColorRight = image.getRGB(player.getX(), player.getY() + 50 + 5);
			
			//System.out.println(bottomColor);
			
			//System.out.println("leftColor : " + leftColor + " | rightColor : " + rightColor);
			
			
			if (bottomColorLeft != -1 || bottomColorRight != -1) {
				player.setStanding(true);
				//System.out.println("bottom collision");
				player.setDown(false);
			} else {
				player.setStanding(false);
				
				if (!player.isUp() && !player.isDown()) {
					player.down();
				}
			}
			
			
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				player.setRightWallCollide(false);
				System.out.println("왼쪽 벽에 충돌함");
				player.setLeft(false);
				player.setLeftWallCollide(true);
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				player.setLeftWallCollide(false);
				System.out.println("오른쪽 벽에 충돌함");
				player.setRight(false);
				player.setRightWallCollide(true);
			} else {
				player.setLeftWallCollide(false);
				player.setRightWallCollide(false);
			}
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

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
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(true) {
			Color leftColor = new Color(image.getRGB(player.getX() - (-1), player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 10, player.getY() + 25));
			
			System.out.println("leftColor : " + leftColor + " | rightColor : " + rightColor);
			
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				System.out.println("왼쪽 벽에 충돌함");
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				System.out.println("오른쪽 벽에 충돌함");
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

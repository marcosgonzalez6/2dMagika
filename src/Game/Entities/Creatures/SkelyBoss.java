package Game.Entities.Creatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Game.Inventories.Inventory;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class SkelyBoss extends SkelyEnemy{
	public SkelyBoss(Handler handler, float x, float y) {
		super(handler, x, y);
		bounds.x=8*2;
		bounds.y=18*2;
		bounds.width=16*2;
		bounds.height=14*2;
		speed=2.5f;
		health=100;

		SkelyCam= new Rectangle();



		randint = new Random();
		direction = randint.nextInt(4) + 1;

		animDown = new Animation(animWalkingSpeed, Images.SkelyEnemy2_front);
		animLeft = new Animation(animWalkingSpeed,Images.SkelyEnemy2_left);
		animRight = new Animation(animWalkingSpeed,Images.SkelyEnemy2_right);
		animUp = new Animation(animWalkingSpeed,Images.SkelyEnemy2_back);

		Skelyinventory= new Inventory(handler);
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("Lucida Grande", 0, 11));
		g.drawImage(getCurrentAnimationFrame(animDown,animUp,animLeft,animRight,Images.SkelyEnemy2_front,Images.SkelyEnemy2_back,Images.SkelyEnemy2_left,Images.SkelyEnemy2_right), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		if(healthcounter<=120){
			if(this.getHealth() > 25){
				g.setColor(Color.GREEN);
				g.drawString("\u2665: " + getHealth(),(int) (x-handler.getGameCamera().getxOffset() + 11),(int) (y-handler.getGameCamera().getyOffset() - 2));
			}else if(this.getHealth()>= 10 && getHealth()<=25){
				g.setColor(Color.YELLOW);
				g.drawString("\u2665: " + getHealth(),(int) (x-handler.getGameCamera().getxOffset() + 11),(int) (y-handler.getGameCamera().getyOffset()-2));

			}else if(this.getHealth() < 10){
				g.setColor(Color.RED);
				g.drawString("\u2665: " + getHealth(),(int) (x-handler.getGameCamera().getxOffset() + 11),(int) (y-handler.getGameCamera().getyOffset()-2));
			}
		}
	}
	@Override
	public void die() {
	}
}

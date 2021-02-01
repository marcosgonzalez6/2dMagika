package Game.Entities.Statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Tiles.Tile;
import Main.Handler;
import Resources.Images;

/**
 * Created by Elemental on 1/2/2017.
 */
public class WoodenSign extends StaticEntity {
	
	private boolean read = false;
	public static final int INFO_SIGN = 0;
	public static final int ARROW_SIGN = 1;
	private BufferedImage sign;
	private String message;
	

    public WoodenSign(Handler handler, float x, float y, int signType){
        super(handler, x, y, Tile.TILEWIDTH * 4/5, Tile.TILEHEIGHT * 4/5);
        bounds.x=0;
        bounds.y=30;
        bounds.width = 64;
        bounds.height = 40;
        health=16;
        if(signType == 1) {
        	sign = Images.sign;
        	message = "Complete the maze to win the level!";
        } else {
        	sign = Images.infosign;
        	message = "Kill the boss to win the game!";
        }
    }

    @Override
    public void tick() {
       
    }
    
    @Override
    public void hurt(int amt) {
       read = !read;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sign ,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
        if (read) {
        	g.setColor(Color.BLACK);
        	g.fillRect((int)(x-handler.getGameCamera().getxOffset()-1), (int)(y-handler.getGameCamera().getyOffset()-21), 230, 17);
        	g.setColor(Color.white);
        	g.drawString(this.message,(int)(x-handler.getGameCamera().getxOffset()+2),(int)(y-handler.getGameCamera().getyOffset()-8));
        }
    }

    @Override
    public void die() {
       
    }
}
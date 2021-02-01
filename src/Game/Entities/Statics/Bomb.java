package Game.Entities.Statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Game.Items.Item;
import Game.Tiles.Tile;
import Main.Handler;
import Resources.Images;

public class Bomb {
	private final int ITEM_WIDTH = 28, ITEM_HEIGHT = 28;
	protected Handler handler;
	//        protected final BufferedImage texture = new BufferedImage(Images.bomb);
	protected int x, y;
	protected boolean pickedUp;

	protected Rectangle bounds;

	private File audioFile;
	private AudioInputStream audioStream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip audioClip;

	public Bomb(int xPos, int yPos) {
		pickedUp = false;
		bounds = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);

		try {
			audioFile = new File("res/music/Chopping.wav");
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			format = audioStream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			audioClip.setMicrosecondPosition(2000);

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void tick(){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds)){
			pickedUp = true;
			//	            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}
	public void render(Graphics g) {
		if(handler == null){
			System.out.println("Here");
			return;
		}
		System.out.println("Her11e");
		render(g,(int)(x-handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()));
	}
	public void render(Graphics g, int x, int y) {
		g.drawImage(Images.bomb,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),ITEM_WIDTH,ITEM_HEIGHT,null);
	}
	/*
	    public void renderExplosion(Graphics g) {
	    	for(int i = 0; i < Images.bombExplosion.length; i++) {
//		    	long startTime = System.currentTimeMillis();
//	    		if((System.currentTimeMillis()-startTime)<10000)
	    		g.drawImage(Images.bombExplosion[i],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
	    	}
	    }
	 */
}

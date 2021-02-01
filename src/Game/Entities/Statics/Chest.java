package Game.Entities.Statics;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

/**
 * Created by Elemental on 1/1/2017.
 */
public class Chest extends StaticEntity {
    private File audioFile1, audioFile2;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip1, audioClip2;
    private boolean open = false;
    private int stickAmount = 0;
    private int runeAmount = 0;
    private int rockAmount = 0;
    private int boneAmount = 0;
    private ArrayList<Item> inventoryItems;

    public Chest(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        bounds.x=0;
        bounds.y=0;
        bounds.width = 64;
        bounds.height = 64;

        try {
            audioFile1 = new File("res/music/chestopening.wav");
            audioStream = AudioSystem.getAudioInputStream(audioFile1);
            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            audioClip1 = (Clip) AudioSystem.getLine(info);
            audioClip1.open(audioStream);
            audioClip1.setMicrosecondPosition(2000);
            
            audioFile2 = new File("res/music/chestclosing.wav");
            audioStream = AudioSystem.getAudioInputStream(audioFile2);
            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            audioClip2 = (Clip) AudioSystem.getLine(info);
            audioClip2.open(audioStream);
            audioClip2.setMicrosecondPosition(2000);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
    	// makes level won if level has a chest
        if (stickAmount >= 3 && runeAmount >= 1 && rockAmount >= 1 && boneAmount >= 1) 
        	handler.getWorld().setChestFull(true);
    }   

    @Override
    public void render(Graphics g) {
    	if (!open) 
    		g.drawImage(Images.chest[0],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    	
    	else if (open) {
    		g.drawImage(Images.chest[1],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    		
    		g.setColor(Color.BLACK);
            g.fillRect((int)(x-handler.getGameCamera().getxOffset()-1), (int)(y-handler.getGameCamera().getyOffset()-66), 76, 60);
            g.setColor(Color.white);
            g.drawString(getStickAmount() + " / 3 Sticks",(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()-40));
            g.drawString(getRockAmount() + " / 1 Rock",(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()-25));
            g.drawString(getRuneAmount() + " / 1 Runes",(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()-10));
            g.drawString(getBoneAmount() + " / 1 Bones",(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()-55));
    	}
    }

    @Override
    public void die() {
       
    }
    
    @Override
    public void hurt(int amt){
    	if (!open) {
    		audioClip2.stop();
    		audioClip1.start();
    		deposit();
    	}
    	else {
    		audioClip1.stop();
    		audioClip2.start();
    	}
    	open = !open;	
    }
    
    public void deposit() {
    	inventoryItems = handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems();
    	
    	for (Item item : inventoryItems) {
    		if (item.getId() == 1 && rockAmount < 1) {
    			rockAmount += item.getCount();
    			item.setCount(0);
    		}
    		if (item.getId() == 2 && runeAmount < 1) {
    			runeAmount += item.getCount();
    			item.setCount(0);
    		}
    		if (item.getId() == 3 && stickAmount < 3) {
    			stickAmount += item.getCount();
    			item.setCount(0);
    		}
    		if (item.getId() == 4 && boneAmount < 1) {
    			boneAmount += item.getCount();
    			item.setCount(0);
    		}
    	}
    }

    public int getStickAmount() {
		return stickAmount;
	}
    
    public int getRockAmount() {
		return rockAmount;
	}
    
    public int getRuneAmount() {
		return runeAmount;
	}
    
    public int getBoneAmount() {
		return boneAmount;
	}

	public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
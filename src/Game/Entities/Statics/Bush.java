package Game.Entities.Statics;

import java.awt.Graphics;
import java.util.Random;

import Game.Items.Item;
import Game.Tiles.Tile;
import Main.Handler;
import Resources.Images;


public class Bush extends Tree {

    public Bush(Handler handler, float x, float y) {
        super(handler, x, y);
        this.height = Tile.TILEHEIGHT * 2/3;
        bounds.x=0;
        bounds.y=0;
        bounds.width = 64;
        bounds.height = 64;
        health=10;
    }

    @Override
    public void render(Graphics g) {
        renderLife(g);
        g.drawImage(Images.bush,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    }

    @Override
    public void die() {
    	Random rand = new Random();
    	for (int i = 0; i < rand.nextInt(5) + 1; i++)
    		handler.getWorld().getItemManager().addItem(Item.stickItem.createNew((int)x + bounds.x + i*5,(int)y + bounds.y - i*5,1));
    }

}


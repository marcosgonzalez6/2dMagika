package Game.Entities.Statics;

import java.awt.Graphics;

import Game.Items.Item;
import Main.Handler;
import Resources.Images;


public class Cactus extends Tree {

    public Cactus(Handler handler, float x, float y) {
        super(handler, x, y);
    }

    @Override
    public void render(Graphics g) {
        renderLife(g);
        g.drawImage(Images.cactus,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    }

    @Override
    public void die() { 	
    	handler.getWorld().getItemManager().addItem(Item.mannaItem.createNew((int)x + bounds.x,(int)y + bounds.y, 1));
    }

}


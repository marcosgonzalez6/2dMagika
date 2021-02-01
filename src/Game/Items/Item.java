package Game.Items;

import Resources.Images;
import Main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import Game.Entities.Statics.WoodenSign;

/**
 * Created by Elemental on 1/2/2017.
 */
public class Item {
	public static int ITEMWIDTH = 32, ITEMWEIGHT = 32,DEFAULTCOUNT=1;

	protected int width = 32, height = 32;
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;

	protected int x,y;
	protected int count;
	protected boolean pickedUp = false;

	protected Rectangle bounds;

	//handler

	public static Item[] items = new Item[256];

    public static Item woodItem = new Item(Images.items[0],"Wood",0);
    public static Item rockItem = new Item(Images.blocks[14],"Rock",1);
    public static Item fireRuneItem = new Item(Images.Runes[2],"Fire Rune",2);
    public static Item stickItem = new Item(Images.stick,"Stick",3);
    public static Item boneItem = new Item(Images.bone,"Bone", 4);
	public static Item bomb = new Item(Images.bomb,"bomb",3);
	public static Item mannaItem = new Item(Images.manna,"Manna", 5);
	public static Item heartItem = new Item(Images.heart,"Life", 6);
	public boolean explode = false;



	//class

	public Item(BufferedImage texture, String name, int id){
		this.texture=texture;
		this.id=id;
		this.name=name;
		count = DEFAULTCOUNT;

		bounds = new Rectangle(x,y,ITEMWIDTH,ITEMWEIGHT);

		items[id]=this;
	}
	public Item(BufferedImage texture, String name, int id,int height,int width){
		this.texture=texture;
		this.id=id;
		this.name=name;
		count = DEFAULTCOUNT;
		this.width=width;
		this.height=height;

		bounds = new Rectangle(x,y,ITEMWIDTH,ITEMWEIGHT);

		items[id]=this;
	}


	public void tick(){
//		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds) && this.name != "bomb"){
//			pickedUp=true;
//			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
//		} else if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds) && this.name == "bomb"){
//			pickedUp = true;
//			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
//		}
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds)) {
			pickedUp = true;
			if(this.getId() != 6 && this.getId() != 5) {
				handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
			} else if(this.getId() == 5){
				handler.getWorld().getEntityManager().getPlayer().setAttack(10);
			} else {
				handler.getWorld().getEntityManager().getPlayer().setHealth(75);
			}
		}
	}
	public void render(Graphics g){
		if(handler == null){
			return;
		}
		if(this.name == "bomb") {
			render(g,(int)(x-handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()));
			//System.out.println("1. " + handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems());
			if(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().contains(bomb)) {
				System.out.println(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().contains(bomb));
				final Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						x = (int)(x - handler.getGameCamera().getxOffset());
	  				    y = (int)(y - handler.getGameCamera().getyOffset());
						ITEMWIDTH = Images.bombExplosion.getWidth();
						ITEMWEIGHT = Images.bombExplosion.getHeight();
						texture = Images.bombExplosion;
					}
				},2000);
			}		
		} else {
			System.out.println(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems().isEmpty());
			ITEMWIDTH = 32;
			ITEMWEIGHT = 32;
			render(g,(int)(x-handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()));
		}
	}

	public void render(Graphics g ,int x, int y){
		g.drawImage(texture,x,y,ITEMWEIGHT,ITEMWEIGHT,null);
	}

	public Item createNew(int x, int y,int count){
		Item i = new Item(texture,name,id);
		i.setCount(count);
		i.setPosition(x,y);
		return i;
	}

	public void setPosition(int x,int y){
		this.x=x;
		this.y=y;
		bounds.x=x;
		bounds.y=y;
	}


	//get and set

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}

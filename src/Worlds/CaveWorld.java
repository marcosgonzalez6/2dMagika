package Worlds;
import java.awt.Rectangle;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Statics.Door;
import Game.Entities.Statics.WoodenSign;
import Main.Handler;
import Game.GameStates.State;

/**
 * Created by Elemental on 2/10/2017.
 */
public class CaveWorld extends BaseWorld{
    private Handler handler;
    private Player player;
    private BaseWorld world2;
    private Rectangle winPosition;

    public CaveWorld(Handler handler, String path, Player player, int worldId) {
        super(handler,path,player, worldId);
        this.handler = handler;
        this.player=player;
        winPosition = new Rectangle(1450, 1350, 64, 64);
        
        world2 = new World2(handler, "res/Maps/world2.map", player, 3);
        entityManager.addEntity(new Door(handler, 100, 0,world2));
        entityManager.addEntity(new SkelyEnemy(handler, 1250, 500));
        entityManager.addEntity(new WoodenSign(handler, 175, 85, WoodenSign.ARROW_SIGN));
    }
    @Override
    public void tick() {
        entityManager.tick();
        itemManager.tick();
        countP++;
        if(countP>=30){
            countP=30;
        }
       
        if(handler.getKeyManager().pbutt && countP>=30){
            handler.getMouseManager().setUimanager(null);
            countP=0;
            State.setState(handler.getGame().pauseState);
        }
        //Skip to the next world
        if(handler.getKeyManager().skipNextWorldBut  && countP>=30 || winPosition.contains((int) player.getX(), (int) player.getY())) { //TESTING
        	countP=0;
        	handler.setWorld(world2);
        	player.setX(spawnX);
            player.setY(spawnY);
        }
    }
}
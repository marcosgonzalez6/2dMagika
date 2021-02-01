package Worlds;

import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyBoss;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Statics.*;
import Game.GameStates.State;
import Main.Handler;

/**
 * Created by Elemental on 1/2/2017.
 */
public class World1 extends BaseWorld{

    private Handler handler;
    private BaseWorld caveWorld;

    public World1(Handler handler, String path, Player player, int worldId){
        super(handler,path,player, worldId);
        this.handler = handler;
        caveWorld = new CaveWorld(handler,"res/Maps/caveMap.map",player, 2);

        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Rock(handler, 100, 450));
        entityManager.addEntity(new Tree(handler, 533, 276));
        entityManager.addEntity(new Rock(handler, 684, 1370));
        entityManager.addEntity(new Tree(handler, 765, 888));
        entityManager.addEntity(new Rock(handler, 88, 1345));
        entityManager.addEntity(new Tree(handler, 77, 700));
        entityManager.addEntity(new Rock(handler, 700, 83));
        entityManager.addEntity(new Door(handler, 100, 0, caveWorld));
        entityManager.addEntity(new SkelyEnemy(handler, 1250, 500));
        entityManager.addEntity(new SkelyEnemy(handler, 600,600));
        entityManager.addEntity(new Chest(handler, 1450, 70));
        entityManager.addEntity(new Bush(handler, 300, 900));
        entityManager.addEntity(new Bush(handler, 300, 200));
        entityManager.addEntity(new Bush(handler, 1000, 1000));
        entityManager.addEntity(new Cactus(handler, 1000, 200));
        entityManager.addEntity(new Cactus(handler, 900, 900));
        entityManager.addEntity(new Cactus(handler, 500, 1100));

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
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
        if(handler.getKeyManager().skipNextWorldBut && countP>=30 || isChestFull()) { //TESTING
        	countP=0;
        	entityManager.getPlayer().setX(spawnX);
            entityManager.getPlayer().setY(spawnY);
        	handler.setWorld(caveWorld);
        }
    }
}
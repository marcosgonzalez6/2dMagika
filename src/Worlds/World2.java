package Worlds;

import javax.swing.JOptionPane;

import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyBoss;
import Game.Entities.Statics.Tree;
import Game.Entities.Statics.WoodenSign;
import Game.GameStates.State;
import Main.Handler;

public class World2 extends BaseWorld{
    private Handler handler;
    private Player player;
    private SkelyBoss boss;

    public World2(Handler handler, String path, Player player, int worldId) {
        super(handler,path,player, worldId);
        this.handler = handler;
        this.player=player;
        boss = new SkelyBoss(handler, 550, 450);
        entityManager.addEntity(boss);
        entityManager.addEntity(new WoodenSign(handler, 150, 100, WoodenSign.INFO_SIGN));
        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Tree(handler, 500, 650));
        entityManager.addEntity(new Tree(handler, 500, 350));
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
        System.out.println(entityManager.getEntities());
        if(!entityManager.getEntities().contains(boss)) {
        	JOptionPane.showMessageDialog(null, "YOU WIN!!", "Win", JOptionPane.INFORMATION_MESSAGE);
        	State.setState(handler.getGame().menuState);
        }
    }

}

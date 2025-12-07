package SelenaMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.TrueVictoryRoom;

public class HeroismAction extends AbstractGameAction {
    private boolean upgraded = false;

    public HeroismAction(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (!upgraded) {
            AbstractDungeon.getCurrRoom().isBattleOver = true;
        } else {
            goToTrueVictory();
        }
        this.isDone = true;
    }

    private void goToTrueVictory() {
        AbstractDungeon.overlayMenu.hideCombatPanels();
        CardCrawlGame.music.fadeOutBGM();
        CardCrawlGame.music.fadeOutTempBGM();
        MapRoomNode node = new MapRoomNode(3, 4);
        node.room = new TrueVictoryRoom();
        AbstractDungeon.nextRoom = node;
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.nextRoomTransitionStart();
    }

}

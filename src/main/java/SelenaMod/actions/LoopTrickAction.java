package SelenaMod.actions;

import SelenaMod.patches.ActionManagerPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class LoopTrickAction extends AbstractGameAction {
    AbstractCard card;
    public LoopTrickAction(int amount, AbstractCard card){
        this.amount=amount;
        this.card=card;
        this.duration= Settings.ACTION_DUR_FAST;
    }
    @Override
    public void update() {
        if(Settings.ACTION_DUR_FAST==this.duration){
            ArrayList<AbstractCard> lastTurnCards= ActionManagerPatch.LastTurnCards;
            for(AbstractCard c:lastTurnCards){

            }
        }
        tickDuration();
    }
}

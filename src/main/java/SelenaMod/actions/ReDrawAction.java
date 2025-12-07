package SelenaMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ReDrawAction extends AbstractGameAction {

    public ReDrawAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            addToTop(new ExpertiseAction(AbstractDungeon.player, 10));
            ArrayList<AbstractCard> cards = new ArrayList<>(AbstractDungeon.player.hand.group);
            for (AbstractCard c : cards) {
                AbstractDungeon.player.hand.moveToDeck(c, true);
            }
        }
        tickDuration();
    }
}

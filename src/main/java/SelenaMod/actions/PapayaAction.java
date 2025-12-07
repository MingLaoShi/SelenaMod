package SelenaMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PapayaAction extends AbstractGameAction {
    public PapayaAction(int amount) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int drawAmount = Math.min(this.amount, AbstractDungeon.player.drawPile.size());
            for (int i = 0; i < drawAmount; i++) {
                AbstractDungeon.player.drawPile.moveToDiscardPile(AbstractDungeon.player.drawPile.getTopCard());
            }

            for (int i = 0; i < drawAmount; i++) {
                AbstractDungeon.actionManager.addToTop(new SelectCardFromDiscardToDrawPileAction(1));
            }
        }
        this.tickDuration();
        this.isDone=true;
    }
}

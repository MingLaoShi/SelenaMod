package SelenaMod.actions;

import SelenaMod.cards.options.IdealismOptions;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

public class AskForRemoveIdealismAction extends AbstractGameAction {

    public AskForRemoveIdealismAction() {
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> cardList = new ArrayList<>();
            cardList.add(new IdealismOptions(0));
            cardList.add(new IdealismOptions(1));
            addToTop(new ChooseOneAction(cardList));
            tickDuration();
            return;
        }
        tickDuration();
    }
}

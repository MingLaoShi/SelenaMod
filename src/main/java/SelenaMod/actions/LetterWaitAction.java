package SelenaMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class LetterWaitAction extends AbstractGameAction {
    private final AbstractCard card;
    private boolean firstTick=true;
    public LetterWaitAction(int amount, AbstractCard card) {
        this.card=card;
        this.amount = amount;
        this.actionType = ActionType.WAIT;
        this.duration = 0.1f * amount;
    }
    @Override
    public void update() {
        if(firstTick){
            card.flash();
            firstTick=false;
        }
        this.tickDuration();
    }
}

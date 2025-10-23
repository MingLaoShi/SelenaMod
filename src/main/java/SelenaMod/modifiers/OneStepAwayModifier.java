package SelenaMod.modifiers;

import SelenaMod.utils.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class OneStepAwayModifier extends AbstractCardModifier {
    public static String ID = ModHelper.makeID("OneStepAwayModifier");

    @Override
    public AbstractCardModifier makeCopy() {
        return new OneStepAwayModifier();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return true;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.setCostForTurn(0);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new DrawCardAction(1));
    }
}

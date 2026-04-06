package SelenaMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;

public class ExhaustAllMiraclesAction extends AbstractGameAction {
    public ExhaustAllMiraclesAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        List<CardGroup> groups = new ArrayList<>();
        groups.add(AbstractDungeon.player.drawPile);
        groups.add(AbstractDungeon.player.discardPile);
        groups.add(AbstractDungeon.player.hand);

        for (CardGroup group : groups) {
            List<AbstractCard> miracles = new ArrayList<>();
            for (AbstractCard card : group.group) {
                if (card instanceof Miracle) {
                    miracles.add(card);
                }
            }
            for (AbstractCard miracle : miracles) {
                addToTop(new ExhaustSpecificCardAction(miracle, group));
            }
        }

        this.isDone = true;
    }
}

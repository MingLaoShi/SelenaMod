package SelenaMod.actions;

import SelenaMod.cardEffects.DrawEffect;
import SelenaMod.cards.Letter;
import SelenaMod.cards.SpringBreeze;
import SelenaMod.modifiers.ToneModifier;
import SelenaMod.modifiers.WhiteSpaceModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;

public class SpringBreezeAction extends AbstractGameAction {

    public SpringBreezeAction() {
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            List<AbstractCard> Letters = new ArrayList<>();
            List<CardGroup> groups = new ArrayList<>();
            groups.add(AbstractDungeon.player.drawPile);
            groups.add(AbstractDungeon.player.discardPile);
            groups.add(AbstractDungeon.player.hand);
            groups.add(AbstractDungeon.player.exhaustPile);
            for (CardGroup group : groups) {
                for (AbstractCard card : group.group) {
                    if (card instanceof Letter) {
                        Letters.add(card);
                    }
                }
            }
            for (AbstractCard card : Letters) {
                Letter letter = (Letter) card;
                letter.overrideEffect = new DrawEffect(SpringBreeze.ID, 2);
                CardModifierManager.removeModifiersById(letter, ToneModifier.ID, true);
                CardModifierManager.removeModifiersById(letter, WhiteSpaceModifier.ID, true);
                letter.initializeDescription();
            }
            this.isDone = true;
            this.tickDuration();
        }
    }
}

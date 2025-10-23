package SelenaMod.powers;

import SelenaMod.actions.PlayDiscardPailCardAction;
import SelenaMod.actions.PlayDrawPailCardAction;
import SelenaMod.actions.PlayHandCardAction;
import SelenaMod.modifiers.NotTriggerYourselfModifier;
import SelenaMod.utils.ModHelper;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class JoinTogetherPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(JoinTogetherPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static int COUNT = 0;
    private AbstractCard card, card2;

    public JoinTogetherPower(AbstractCreature owner, AbstractCard card, AbstractCard card2) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.card = card;
        this.card2 = card2;
        ModHelper.initPower(this);
        this.ID += COUNT;
        COUNT++;

    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[0], this.card.name, this.card2.name);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        AbstractCard cardToPlay = null;

        if (card == this.card) {
            cardToPlay = this.card2;
        } else if (card == this.card2) {
            cardToPlay = this.card;
        }
        if (cardToPlay != null) {
            if (CardModifierManager.hasModifier(card, NotTriggerYourselfModifier.ID)) {
                CardModifierManager.removeModifiersById(card, NotTriggerYourselfModifier.ID, true);
                return;
            }
            CardModifierManager.addModifier(cardToPlay, new NotTriggerYourselfModifier());
            if (AbstractDungeon.player.drawPile.contains(cardToPlay)) {
                addToBot(new PlayDrawPailCardAction(cardToPlay, null));
            }
            if (AbstractDungeon.player.hand.contains(cardToPlay)) {
                addToBot(new PlayHandCardAction(cardToPlay, null));
            }
            if (AbstractDungeon.player.discardPile.contains(cardToPlay)) {
                addToBot(new PlayDiscardPailCardAction(cardToPlay, null));
            }
        }
    }
}

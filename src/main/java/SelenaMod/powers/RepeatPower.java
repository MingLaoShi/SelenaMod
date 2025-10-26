package SelenaMod.powers;

import SelenaMod.modifiers.RepeatModifier;
import SelenaMod.utils.ModHelper;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RepeatPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(RepeatPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public RepeatPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[0]);
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        this.flash();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                CardModifierManager.addModifier(usedCard, new RepeatModifier());
                this.isDone = true;
            }
        });
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }
}

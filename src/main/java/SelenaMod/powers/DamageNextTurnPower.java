package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DamageNextTurnPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(DamageNextTurnPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public DamageNextTurnPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[0], this.amount);
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.flash();
        addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, this.amount)));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
}

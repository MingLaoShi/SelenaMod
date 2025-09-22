package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.InvinciblePower;

public class RemoveInvinciblePower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(RemoveInvinciblePower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private int powerType = 0;
    private boolean isNextTurn = false;

    public RemoveInvinciblePower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.DEBUFF;
        this.powerType = amount;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[this.powerType], this.amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        if (stackAmount == 1) {
            this.powerType = 1;
        }
    }

    @Override
    public void atStartOfTurn() {
        if (this.powerType == 0) {
            this.flash();
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (this.powerType == 1 && isPlayer) {
            if (isNextTurn) {
                this.flash();
                addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            } else {
                isNextTurn = true;
            }
        }
    }

    @Override
    public void onRemove() {
        AbstractPower power = this.owner.getPower(InvinciblePower.POWER_ID);
        if (power != null) {
            if (power.amount > 12) {
                addToBot(new ReducePowerAction(this.owner, this.owner, power, 12));
            } else {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, power.ID));
            }
        }
    }
}

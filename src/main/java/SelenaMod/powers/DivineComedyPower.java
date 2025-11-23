package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;

public class DivineComedyPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(DivineComedyPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public DivineComedyPower(AbstractCreature owner, int amount) {
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
//
//    @Override
//    public int onLoseHp(int damageAmount) {
//        if (damageAmount > 0) {
//            addToBot(new ApplyPowerAction(this.owner, this.owner, new RegenPower(this.owner, this.amount)));
//        }
//        return super.onLoseHp(damageAmount);
//    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new RegenPower(this.owner, this.amount)));
        }
        return super.onLoseHp(damageAmount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
}

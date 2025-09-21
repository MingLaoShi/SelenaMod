package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NextFormBondPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(NextFormBondPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private int powertype;
    public NextFormBondPower(AbstractCreature owner, int type) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.powertype=type;
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description= String.format(strings.DESCRIPTIONS[powertype], this.amount);
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        addToBot(new ApplyPowerAction(this.owner, this.owner, new FormBondPower(this.owner,this.powertype)));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }
}

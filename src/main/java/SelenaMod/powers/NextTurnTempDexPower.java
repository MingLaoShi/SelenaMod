package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

public class NextTurnTempDexPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(NextTurnTempDexPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public NextTurnTempDexPower(AbstractPlayer owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description=String.format(strings.DESCRIPTIONS[0],this.amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(this.owner,this.owner,new DexterityPower(this.owner,this.amount)));
        addToBot(new ApplyPowerAction(this.owner,this.owner,new LoseDexterityPower(this.owner,this.amount)));
        addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this.ID));
    }
}

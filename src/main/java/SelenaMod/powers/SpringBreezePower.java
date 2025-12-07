package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SpringBreezePower extends AbstractPower {
    public static String POWER_ID= ModHelper.makeID("SpringBreezePower");
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public SpringBreezePower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description=strings.DESCRIPTIONS[0];
    }
}

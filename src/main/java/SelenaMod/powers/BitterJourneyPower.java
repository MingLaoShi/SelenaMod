package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BitterJourneyPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(BitterJourneyPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public BitterJourneyPower(AbstractCreature owner, int amount) {
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
    public int onLoseHp(int damageAmount) {
        if (damageAmount > 0 && !AbstractDungeon.actionManager.turnHasEnded) {
            this.flash();
            AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            addToBot(new DamageAction(m, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.HP_LOSS)));
        }
        return damageAmount;
    }

}

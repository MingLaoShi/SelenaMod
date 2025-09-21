package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import org.apache.commons.lang3.StringUtils;

public class WanderPower extends TwoAmountPower {
    public static final String POWER_ID = ModHelper.makeID(WanderPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private int loseHp;
    private int triggerCount1 = 0;
    private int triggerCount2 = 0;
    private int stackAmount1 = 0;
    private int stackAmount2 = 0;

    public WanderPower(AbstractCreature owner, int amount, int type) {
        this.ID = POWER_ID;
        this.owner = owner;
        if (type == 0) {
            this.amount = amount;
            this.amount2 = 0;
            this.stackAmount1++;

        } else {
            this.amount = 0;
            this.amount2 = amount;
            this.stackAmount2++;
        }
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        String desc = "";
        if (this.amount > 0) {
            desc += (String.format(strings.DESCRIPTIONS[0] + strings.DESCRIPTIONS[2], this.stackAmount1 * 10, this.stackAmount1, this.amount));
        }
        if (StringUtils.isNotBlank(desc)) {
            desc += " NL ";
        }
        if (this.amount2 > 0) {
            desc += (String.format(strings.DESCRIPTIONS[1] + strings.DESCRIPTIONS[2], this.stackAmount2 * 10, this.stackAmount2, this.amount2));
        }
        this.description = desc;
    }

    public void stackPower(int amount1, int amount2) {
        if (amount1 > 0) {
            this.amount += amount1;
            this.stackAmount1++;
        }
        if (amount2 > 0) {
            this.amount2 += amount2;
            this.stackAmount2++;
        }
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if(this.stackAmount1>0){
            addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, this.stackAmount1 * 10, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

//        addToBot(new LoseHPAction(this.owner,this.owner,this.stackAmount1*10));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new IntangiblePlayerPower(this.owner, this.stackAmount1)));
            this.triggerCount1++;
            this.flash();
        }

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if(this.stackAmount2>0){
                addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, this.stackAmount2 * 10, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
//            addToBot(new LoseHPAction(this.owner,this.owner,this.stackAmount2*10));
                addToBot(new ApplyPowerAction(this.owner, this.owner, new IntangiblePlayerPower(this.owner, this.stackAmount2)));
                this.triggerCount2++;
                this.flash();
            }

        }
    }

    @Override
    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(this.triggerCount1 * this.amount + this.triggerCount2 * this.amount2);
        }
    }
}

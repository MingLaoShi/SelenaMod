package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Sonnet19Power extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(Sonnet19Power.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public Sonnet19Power(AbstractCreature owner,int amount){
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void onSpecificTrigger() {
        this.flash();
        addToBot(new ReducePowerAction(this.owner,this.owner,this,1));
    }

    @SpirePatch(clz = UseCardAction.class,method = SpirePatch.CONSTRUCTOR,paramtypez = {AbstractCard.class, AbstractCreature.class})
    public static class constructorPatch{
        @SpirePostfixPatch
        public static void postfix(UseCardAction _instance,AbstractCard card,AbstractCreature target){
            if(_instance.exhaustCard){
                AbstractPower power=AbstractDungeon.player.getPower(Sonnet19Power.POWER_ID);

                if(power!=null){
                    _instance.exhaustCard=false;
                    power.onSpecificTrigger();
                }
            }
        }
    }

}

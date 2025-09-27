package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SkipNextDrawPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(SkipNextDrawPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public SkipNextDrawPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description=String.format(strings.DESCRIPTIONS[0],amount);
    }

    @Override
    public void onSpecificTrigger() {
        this.flash();
        addToBot(new ReducePowerAction(this.owner,this.owner,this,1));
    }

    @SpirePatch(clz = DrawCardAction.class,method = "update")
    public static class DrawCardActionPatch{
        @SpirePrefixPatch
        public static void prefix(DrawCardAction __instance,AbstractGameAction ___followUpAction){
            AbstractPower power=AbstractDungeon.player.getPower(SkipNextDrawPower.POWER_ID);
            if(power!=null){
                power.onSpecificTrigger();
                __instance.amount=0;
                __instance.isDone=true;
                if (___followUpAction != null) {
                    AbstractDungeon.actionManager.addToTop(___followUpAction);
                }
            }
        }

    }

}

package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class BurningVowPower extends TwoAmountPower {
    public static final String POWER_ID = ModHelper.makeID(BurningVowPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public BurningVowPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.amount2 = 5;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[0], this.amount2, this.amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.amount2 += 5;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, this.amount2, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
            for (int i = 0; i < 5; i++) {
                addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.amount, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            }

        }
    }

    @SpirePatch(clz = GainBlockAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCreature.class, int.class})
    public static class GainBlockPatch {
        @SpirePostfixPatch
        public static void postfix(GainBlockAction __instance, AbstractCreature target, int amount) {
            if (target.hasPower(BurningVowPower.POWER_ID)) {
                __instance.amount = 0;
            }
        }
    }

    @SpirePatch(clz = GainBlockAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCreature.class, AbstractCreature.class, int.class})
    public static class GainBlockPatch2 {
        @SpirePostfixPatch
        public static void postfix(GainBlockAction __instance, AbstractCreature target, AbstractCreature source, int amount) {
            if (target.hasPower(BurningVowPower.POWER_ID)) {
                __instance.amount = 0;
            }
        }
    }
}

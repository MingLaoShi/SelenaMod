package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;

public class WhiteBird extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("WhiteBird");

    public WhiteBird() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.isInnate = true;
        this.isEthereal = true;
        this.exhaust = true;
        this.setDamage(6);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public void triggerWhenDrawn() {
        if (GameActionManager.turn > 1) {
            addPowerToSelf(new FlightPower(AbstractDungeon.player, 1));
        }
    }

    @SpirePatch(clz = FlightPower.class, method = "onRemove")
    public static class FlightPowerOnRemovePatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> prefix(FlightPower __instance) {
            if (__instance.owner == AbstractDungeon.player) {
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}

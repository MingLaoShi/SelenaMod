package SelenaMod.patches;

import SelenaMod.powers.SirenPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Optional;

public class GainBlockActionPatch {
    @SpirePatch(clz = GainBlockAction.class, method = "update")
    public static class updatePatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> prefix(GainBlockAction __instance) {
            if (__instance.target != AbstractDungeon.player) {
                return SpireReturn.Continue();
            }
            Optional<AbstractPower> siren = AbstractDungeon.player.powers.stream().filter(p -> p instanceof SirenPower).findFirst();
            if (siren.isPresent() && !__instance.isDone) {
                siren.get().flash();
                __instance.isDone = true;
                float damage = __instance.amount;
                AbstractDungeon.actionManager.addToTop(new DamageRandomEnemyAction(
                        new DamageInfo(AbstractDungeon.player, (int) damage, DamageInfo.DamageType.NORMAL),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}

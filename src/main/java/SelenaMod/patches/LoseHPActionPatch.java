package SelenaMod.patches;

import SelenaMod.core.SelenaMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;

public class LoseHPActionPatch {


    @SpirePatch(clz = DamageAction.class, method = "update")
    public static class updatePatch {
        private static int preDamageHp = 0;

        @SpirePostfixPatch
        public static void postfix(DamageAction __instance) {
            if (__instance.target != null && !__instance.target.isDeadOrEscaped()) {
                if (!__instance.isDone) {
                    preDamageHp = __instance.target.currentHealth;
                } else {
                    SelenaMod.LOSE_HP_THIS_TURN = __instance.target.currentHealth != preDamageHp;
                }
            }
        }
    }
}

package SelenaMod.patches;

import SelenaMod.core.SelenaMod;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;

public class LoseHPActionPatch {

    @SpirePatch(clz = LoseHPAction.class, method = "update")
    public static class Patch {
        private static int preDamageHp = 0;

        @SpireInsertPatch(loc = 35)
        public static void Postfix(LoseHPAction __instance) {
            if (__instance.target != null) {
                preDamageHp = __instance.target.currentHealth;
//                SelenaMod.LOSE_HP_THIS_TURN=true;
            }
        }

        @SpireInsertPatch(loc = 36)
        public static void Postfix2(LoseHPAction __instance) {
            if (__instance.target.currentHealth != preDamageHp) {
                SelenaMod.LOSE_HP_THIS_TURN = true;
            }
        }
    }
}

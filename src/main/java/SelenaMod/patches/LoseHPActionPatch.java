package SelenaMod.patches;

import SelenaMod.core.SelenaMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;

public class LoseHPActionPatch {

    @SpirePatch(clz = LoseHPAction.class,method = "update")
    public static class Patch{
        @SpirePrefixPatch
        public static void Postfix(LoseHPAction __instance){
            if (__instance.target != null &&__instance.isDone) {
                SelenaMod.LOSE_HP_THIS_TURN=true;
            }
        }
    }
}

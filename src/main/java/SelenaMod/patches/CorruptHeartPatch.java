package SelenaMod.patches;

import SelenaMod.powers.SecretCorruptHeartPower;
import SelenaMod.utils.SelenaEnums;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;

public class CorruptHeartPatch {
    @SpirePatch(clz = CorruptHeart.class, method = "usePreBattleAction")
    public static class usePreBattleActionPatch {
        @SpirePostfixPatch
        public static void Postfix(CorruptHeart __instance) {
            if (AbstractDungeon.player.chosenClass == SelenaEnums.SelenaColorEnum.Selena) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(__instance, __instance, new SecretCorruptHeartPower(__instance)));
            }
        }
    }
}

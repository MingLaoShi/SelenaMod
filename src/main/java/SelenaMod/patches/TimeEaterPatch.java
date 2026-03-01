package SelenaMod.patches;

import SelenaMod.utils.SelenaEnums;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.powers.CuriosityPower;

public class TimeEaterPatch {
    @SpirePatch(clz = TimeEater.class, method = "usePreBattleAction")
    public static class usePreBattleActionPatch {
        @SpirePostfixPatch
        public static void Postfix(TimeEater __instance) {
            int amount = 1;
//            if(AbstractDungeon.ascensionLevel >= 19){
//                amount=2;
//            }
            if (AbstractDungeon.player.chosenClass == SelenaEnums.SelenaColorEnum.Selena) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(__instance, __instance, new CuriosityPower(__instance, amount)));
            }
        }
    }
}

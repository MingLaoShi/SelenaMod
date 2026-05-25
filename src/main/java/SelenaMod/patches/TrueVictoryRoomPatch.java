package SelenaMod.patches;

import SelenaMod.cutscenes.SelenaEndCutscene;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.SelenaEnums;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.TrueVictoryRoom;

@SpirePatch(clz = TrueVictoryRoom.class, method = SpirePatch.CONSTRUCTOR)
public class TrueVictoryRoomPatch {

    @SpirePostfixPatch
    public static void Postfix(TrueVictoryRoom __instance) {
        if (AbstractDungeon.player != null &&
                AbstractDungeon.player.chosenClass == SelenaEnums.SelenaColorEnum.Selena) {
            ModHelper.logger.info("=== Replacing cutscene with SelenaEndCutscene ===");
            ReflectionHacks.setPrivate(__instance, TrueVictoryRoom.class, "cutscene", new SelenaEndCutscene());
        }
    }
}
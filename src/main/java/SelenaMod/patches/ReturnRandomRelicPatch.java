package SelenaMod.patches;

import SelenaMod.character.Selena;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.HashMap;
import java.util.Map;

public class ReturnRandomRelicPatch {
    public static final Map<String, String> replaceRelicMap = new HashMap<>();

    @SpirePatch(clz = AbstractDungeon.class, method = "returnRandomRelic")
    public static class ReturnRandomRelicPatchMethod {
        @SpirePostfixPatch
        public static AbstractRelic Postfix(AbstractRelic ___result, AbstractRelic.RelicTier tier) {
            if (AbstractDungeon.player instanceof Selena) {
                if (replaceRelicMap.containsKey(___result.relicId)) {
                    return RelicLibrary.getRelic(replaceRelicMap.get(___result.relicId)).makeCopy();
                }
            }
            return ___result;
        }
    }
}

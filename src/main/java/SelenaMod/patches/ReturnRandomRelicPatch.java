package SelenaMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashMap;
import java.util.Map;

public class ReturnRandomRelicPatch {
    public static final Map<String, String> replaceRelicMap = new HashMap<>();

    @SpirePatch(clz = AbstractDungeon.class, method = "initializeRelicList")
    public static class InitializeRelicListPatch {
        @SpirePostfixPatch
        public static void Postfix(AbstractDungeon __instance) {
            // 从所有遗物池中移除replaceRelicMap的key（原遗物ID）
            for (String originalRelicId : replaceRelicMap.keySet()) {
                AbstractDungeon.commonRelicPool.remove(originalRelicId);
                AbstractDungeon.uncommonRelicPool.remove(originalRelicId);
                AbstractDungeon.rareRelicPool.remove(originalRelicId);
                AbstractDungeon.bossRelicPool.remove(originalRelicId);
                AbstractDungeon.shopRelicPool.remove(originalRelicId);
            }
        }
    }
}

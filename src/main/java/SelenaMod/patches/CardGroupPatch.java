package SelenaMod.patches;

import SelenaMod.modifiers.RepeatModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CardGroupPatch {
    @SpirePatch(clz = CardGroup.class, method = "moveToDiscardPile")
    public static class moveToDiscardPilePatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> prefix(CardGroup __instance, AbstractCard c) {
            if (__instance == AbstractDungeon.player.hand && __instance.contains(c) && CardModifierManager.hasModifier(c, RepeatModifier.ID)) {
                __instance.moveToDeck(c, false);
                return SpireReturn.Return();
            } else {
                return SpireReturn.Continue();
            }
        }
    }
}

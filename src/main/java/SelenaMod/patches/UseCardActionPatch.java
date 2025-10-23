package SelenaMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UseCardActionPatch {
    @SpirePatch(clz = UseCardAction.class, method = SpirePatch.CLASS)
    public static class Field {
        public static SpireField<CardGroup> CARD_FROM = new SpireField<>(() -> null);
    }

    @SpirePatch(clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
    public static class constructorPatch {
        @SpirePostfixPatch
        public static void postfix(UseCardAction __instance, AbstractCard card, AbstractCreature target) {
            CardGroup[] groups = new CardGroup[]{AbstractDungeon.player.hand, AbstractDungeon.player.drawPile, AbstractDungeon.player.discardPile, AbstractDungeon.player.exhaustPile};
            for (CardGroup group : groups) {
                if (group.contains(card)) {
                    Field.CARD_FROM.set(__instance, group);
                    break;
                }
            }
        }
    }
}

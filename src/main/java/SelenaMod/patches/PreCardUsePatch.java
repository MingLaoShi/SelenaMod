package SelenaMod.patches;

import SelenaMod.interfaces.IPreUseCard;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PreCardUsePatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class useCardPatch {
        @SpirePrefixPatch
        public static void prefix(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            for (AbstractPower power : __instance.powers) {
                if (power instanceof IPreUseCard) {
                    ((IPreUseCard) power).onPreUseCard(c, monster);
                }
            }
        }
    }
}

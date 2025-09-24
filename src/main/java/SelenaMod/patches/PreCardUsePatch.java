package SelenaMod.patches;

import SelenaMod.actions.LetterWaitAction;
import SelenaMod.interfaces.IPreUseCard;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class PreCardUsePatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class useCardPatch {
        @SpirePrefixPatch
        public static void prefix(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            ArrayList<DescriptionLine> lines= ReflectionHacks.getPrivate(c,AbstractCard.class,"description");
            ArrayList<DescriptionLine> originLines=new ArrayList<>(lines);
            for (AbstractPower power : __instance.powers) {
                if (power instanceof IPreUseCard) {
                    ((IPreUseCard) power).onPreUseCard(c, monster);
                }
            }
            c.calculateCardDamage(monster);
            c.initializeDescription();
            ArrayList<DescriptionLine> newLines=new ArrayList<>(lines);
            newLines.removeIf(l->{
                for(DescriptionLine line:originLines){
                    if(line.getText().equals(l.getText())){
                        return true;
                    }
                }
                return false;
            });
            if(!newLines.isEmpty()){
                AbstractDungeon.actionManager.addToTop(new LetterWaitAction(50000, c,newLines));
            }

        }
    }
}

package SelenaMod.patches;

import SelenaMod.core.SelenaMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Objects;

public class AbstractMonsterPatch {
    @SpirePatch(clz = AbstractMonster.class,method = "damage")
    public static class damagePatch{
        @SpirePostfixPatch
        public static void postfix(AbstractMonster __instance, DamageInfo info,int ___lastDamageTaken){
            if (Objects.nonNull(info) && Objects.nonNull(info.owner) && info.owner.isPlayer) {
                SelenaMod.DAMAGED_THIS_TURN+=___lastDamageTaken;
            }
        }

    }

    @SpirePatch(clz = AbstractPlayer.class,method = "damage")
    public static class playerDamagePatch{
        public static int counter=0;
        @SpirePrefixPatch
        public static void prefix(AbstractPlayer __instance, DamageInfo info){
            if (Objects.nonNull(info) && Objects.nonNull(info.owner) && info.owner.isPlayer) {
                counter=__instance.currentHealth;
            }
        }

        @SpirePostfixPatch
        public static void postfix(AbstractPlayer __instance,DamageInfo info){
            if (Objects.nonNull(info) && Objects.nonNull(info.owner) && info.owner.isPlayer) {
                SelenaMod.DAMAGED_THIS_TURN+=(counter-__instance.currentHealth);
                counter=__instance.currentHealth;
            }
        }

    }
}

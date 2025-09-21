package SelenaMod.patches;

import SelenaMod.powers.ShortLetterPower;
import SelenaMod.powers.WildGooseLetterPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DamageInfoPatch {
    @SpirePatch(clz = DamageInfo.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCreature.class, int.class, DamageInfo.DamageType.class})
    public static class ConstructorPatch {
        @SpirePostfixPatch
        public static void Postfix(DamageInfo __instance) {
            try{
                if (__instance.type == DamageInfo.DamageType.NORMAL && __instance.owner.hasPower(WildGooseLetterPower.POWER_ID)) {
                    __instance.type = DamageInfo.DamageType.HP_LOSS;
                } else if (__instance.type == DamageInfo.DamageType.HP_LOSS && __instance.owner.hasPower(ShortLetterPower.POWER_ID)) {
                    __instance.type = DamageInfo.DamageType.NORMAL;
                }
            }catch (NullPointerException e){
                //do nothing
            }

        }
    }
}

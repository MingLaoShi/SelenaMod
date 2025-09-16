package SelenaMod.patches;

import SelenaMod.powers.SirenPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Optional;

public class GainBlockActionPatch {
    @SpirePatch(clz = GainBlockAction.class,method = "update")
    public static class updatePatch{
        @SpirePrefixPatch
        public static SpireReturn<Void> prefix(GainBlockAction __instance){
            Optional<AbstractPower> siren= AbstractDungeon.player.powers.stream().filter(p->p instanceof SirenPower).findFirst();
            if(siren.isPresent()&&!__instance.isDone){
                siren.get().flash();
                __instance.isDone=true;
                AbstractMonster m= AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                float damage=__instance.amount;
                for(AbstractPower power:m.powers){
                    damage=power.atDamageReceive(damage, DamageInfo.DamageType.NORMAL);
                }
                for(AbstractPower power:m.powers){
                    damage=power.atDamageFinalReceive(damage, DamageInfo.DamageType.NORMAL);
                }
                AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true),
                        new DamageInfo(AbstractDungeon.player, (int)damage, DamageInfo.DamageType.NORMAL)));
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}

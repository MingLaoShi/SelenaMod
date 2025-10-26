package SelenaMod.cards;

import SelenaMod.powers.LoopTrick1;
import SelenaMod.powers.LoopTrick2;
import SelenaMod.powers.LoopTrick3;
import SelenaMod.powers.RepeatPower;
import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class LoopTrick extends CustomSelenaCard {
    public static String ID= ModHelper.makeID("LoopTrick");
    public LoopTrick() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
//        this.setMagic(10);
//        CardModifierManager.addModifier(this,new RepeatModifier());
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (!upgraded) {
            addPowerToSelf(new LoopTrick1(abstractPlayer, 1));
        } else {
            addPowerToSelf(new LoopTrick2(abstractPlayer));
        }
        addPowerToSelf(new RepeatPower(abstractPlayer));
        addPowerToSelf(new LoopTrick3(abstractPlayer));
    }


    @SpirePatch(clz = AbstractCard.class, method = "freeToPlay")
    public static class freeToPlayPatch {
        public static SpireReturn<Boolean> Prefix(AbstractCard card) {
            if (AbstractDungeon.player != null &&
                    AbstractDungeon.currMapNode != null &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                if (AbstractDungeon.player.hasPower(LoopTrick2.POWER_ID)) {
                    return SpireReturn.Return(true);
                }
            }
            return SpireReturn.Continue();
        }
    }
}

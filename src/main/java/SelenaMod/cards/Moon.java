package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

public class Moon extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("Moon");
    public Moon() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.setMagic(6);
        this.exhaust=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainGoldAction(this.magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        addPowerToSelf(new EnergizedBluePower(AbstractDungeon.player,1));
    }
}

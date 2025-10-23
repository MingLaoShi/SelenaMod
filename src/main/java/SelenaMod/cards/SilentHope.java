package SelenaMod.cards;

import SelenaMod.interfaces.IFirstSight;
import SelenaMod.powers.AsFirstSightPower;
import SelenaMod.powers.SilentHopePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class SilentHope extends CustomSelenaCard implements IFirstSight {
    public static String ID= ModHelper.makeID(SilentHope.class.getSimpleName());
    public SilentHope() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(1);
        this.setSecondMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
        this.secondMagicVar=2;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new SilentHopePower(abstractPlayer,this.magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        if (AsFirstSightPower.isFirstSight(this)) {
            this.onFirstSight();
            this.firstSight=true;
        }
    }

    @Override
    public void onFirstSight() {
        addPowerToSelf(new ArtifactPower(AbstractDungeon.player,this.secondMagicVar));
    }
}

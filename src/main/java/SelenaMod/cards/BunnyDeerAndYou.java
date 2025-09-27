package SelenaMod.cards;

import SelenaMod.powers.NextTurnReturnHandPower;
import SelenaMod.powers.NextTurnTempDexPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class BunnyDeerAndYou extends CustomSelenaCard{
    public static String ID= ModHelper.makeID(BunnyDeerAndYou.class.getSimpleName());
    public BunnyDeerAndYou() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.setBlock(1);
        this.setMagic(3);
        this.setSecondMagic(2);
        this.isInnate=true;
    }

    @Override
    protected void upgradeMethod() {
        this.setSecondMagic(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new VigorPower(abstractPlayer,this.magicNumber));
        addPowerToSelf(new NextTurnTempDexPower(abstractPlayer,this.block));
        addPowerToSelf(new NextTurnReturnHandPower(abstractPlayer,2,this));
        this.baseMagicNumber+=this.secondMagicVar;
        this.baseBlock+=this.secondMagicVar;
    }

    @Override
    public void applyPowers() {
        this.isBlockModified=this.block!=this.baseBlock;
    }
}

package SelenaMod.cards;

import SelenaMod.powers.Sonnet19Power;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sonnet19 extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("Sonnet19");
    public Sonnet19() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.isInnate=true;
        this.exhaust=true;
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new Sonnet19Power(abstractPlayer,this.magicNumber));
    }
}

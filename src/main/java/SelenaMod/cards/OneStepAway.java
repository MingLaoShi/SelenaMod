package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OneStepAway extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("OneStepAway");
    public OneStepAway() {
        super(ID, 4, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.setMagic(300);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(100);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}

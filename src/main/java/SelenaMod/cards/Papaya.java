package SelenaMod.cards;

import SelenaMod.actions.PapayaAction;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Papaya extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("Papaya");
    public Papaya() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.setMagic(3);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new PapayaAction(this.magicNumber));
    }
}

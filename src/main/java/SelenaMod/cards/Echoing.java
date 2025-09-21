package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Echoing extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Echoing.class.getSimpleName());

    public Echoing() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.isInnate = true;
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        a
    }
}

package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Dodge extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Dodge.class.getSimpleName());

    public Dodge() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.setBlock(5);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomBlockAction();
    }
}

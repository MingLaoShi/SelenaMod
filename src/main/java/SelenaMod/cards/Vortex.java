package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Vortex extends CustomSelenaCard{
    public static String ID= ModHelper.makeID("Vortex");
    public Vortex() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
//        MultiCardPreview.add(this,);
    }

    @Override
    protected void upgradeMethod() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}

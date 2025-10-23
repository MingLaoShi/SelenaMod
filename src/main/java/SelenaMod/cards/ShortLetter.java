package SelenaMod.cards;

import SelenaMod.powers.ShortLetterPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShortLetter extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(ShortLetter.class.getSimpleName());

    public ShortLetter() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void upgradeMethod() {
        this.isInnate = true;
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new ShortLetterPower(abstractPlayer, -1));
    }
}

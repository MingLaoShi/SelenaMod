package SelenaMod.cards;

import SelenaMod.powers.WildGooseLetterPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WildGooseLetter extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(WildGooseLetter.class.getSimpleName());

    public WildGooseLetter() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new WildGooseLetterPower(abstractPlayer, -1));
    }
}

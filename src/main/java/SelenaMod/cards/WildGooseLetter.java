package SelenaMod.cards;

import SelenaMod.powers.WildGooseLetterPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WildGooseLetter extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(WildGooseLetter.class.getSimpleName());

    public WildGooseLetter() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void upgradeMethod() {
        this.isInnate = true;
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new WildGooseLetterPower(abstractPlayer, -1));
    }
}

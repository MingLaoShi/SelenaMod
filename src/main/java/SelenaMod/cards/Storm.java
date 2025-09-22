package SelenaMod.cards;

import SelenaMod.cardEffects.PrayerEffect;
import SelenaMod.powers.OverridePower;
import SelenaMod.powers.RemoveInvinciblePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.InvinciblePower;

public class Storm extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Storm.class.getSimpleName());

    public Storm() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.setMagic(3);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new InvinciblePower(abstractPlayer, 12));
        addPowerToSelf(new RemoveInvinciblePower(abstractPlayer, this.upgraded ? 1 : 0));
        addPowerToSelf(new OverridePower(abstractPlayer, this.magicNumber, new PrayerEffect(this.cardID, this.magicNumber)));
    }
}

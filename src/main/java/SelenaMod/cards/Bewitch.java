package SelenaMod.cards;

import SelenaMod.powers.SirenPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Bewitch extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Bewitch.class.getSimpleName());

    public Bewitch() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(2);
        this.cardsToPreview = new Madness();
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
        this.cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new SirenPower(abstractPlayer));
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), this.magicNumber));
    }
}

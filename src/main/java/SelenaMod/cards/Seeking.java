package SelenaMod.cards;

import SelenaMod.cardEffects.DrawEffect;
import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Seeking extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Seeking.class.getSimpleName());

    public Seeking() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(9);
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(4);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addPowerToSelf(new WhiteSpacePower(abstractPlayer, this.magicNumber, new DrawEffect(Questing.QUESTING_SEEKING_ID, this.magicNumber)));
    }
}

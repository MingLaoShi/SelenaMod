package SelenaMod.cards;

import SelenaMod.cardEffects.VigorEffect;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Minuet extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Minuet.class.getSimpleName());

    public Minuet() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(6);
        this.setMagic(3);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.LIGHTNING);
        addPowerToSelf(new TonePower(abstractPlayer, this.magicNumber, new VigorEffect(this.magicNumber)));
    }
}

package SelenaMod.cards;

import SelenaMod.cardEffects.DamageEffect;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Aria extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Aria.class.getSimpleName());

    public Aria() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(6);
        this.setMagic(1);
        this.setSecondMagic(4);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addPowerToSelf(new TonePower(abstractPlayer, this.magicNumber, new DamageEffect(this.cardID, this.secondMagicVar, this.magicNumber)));
    }
}

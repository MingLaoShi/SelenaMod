package SelenaMod.cards;

import SelenaMod.cardEffects.DamageEffect;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Unfinished extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("Unfinished");

    public Unfinished() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setDamage(4);
        this.exhaust = true;
        this.setSecondMagic(6);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(2);
        this.setSecondMagic(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addPowerToSelf(new TonePower(abstractPlayer, this.secondMagicVar, new DamageEffect(this.cardID, this.secondMagicVar, 2)));
    }
}

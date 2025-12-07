package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Idealism extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("Idealism");

    public Idealism() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.setDamage(6);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.FIRE);
    }

    public void triggerOnExhaust() {
        addToBot(new MakeTempCardInHandAction(this.makeSameInstanceOf()));
    }
}

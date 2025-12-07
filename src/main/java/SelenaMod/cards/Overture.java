package SelenaMod.cards;

import SelenaMod.cardEffects.ScryEffect;
import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Overture extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Overture.class.getSimpleName());

    public Overture() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.setNums(6, -1, 3);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addTonePower(new WhiteSpacePower(abstractPlayer, this.magicNumber, new ScryEffect(this.cardID, this.magicNumber)),abstractMonster);
    }
}

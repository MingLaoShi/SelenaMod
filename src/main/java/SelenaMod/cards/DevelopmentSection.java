package SelenaMod.cards;

import SelenaMod.cardEffects.BlockEffect;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DevelopmentSection extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(DevelopmentSection.class.getSimpleName());

    public DevelopmentSection() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.setDamage(6);
        this.setMagic(2);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.FIRE);
        addPowerToSelf(new TonePower(abstractPlayer, this.magicNumber, new BlockEffect(this.magicNumber)));
    }
}

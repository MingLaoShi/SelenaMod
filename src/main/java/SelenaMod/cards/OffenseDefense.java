package SelenaMod.cards;

import SelenaMod.cardEffects.VulnerableEffect;
import SelenaMod.powers.WhiteSpacePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OffenseDefense extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(OffenseDefense.class.getSimpleName());

    public OffenseDefense() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.setNums(3, 3, 1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(2);
        this.upgradeBlock(2);
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addCustomBlockAction();
        addPowerToSelf(new WhiteSpacePower(abstractPlayer, this.magicNumber, new VulnerableEffect(this.cardID,this.magicNumber)));
    }
}

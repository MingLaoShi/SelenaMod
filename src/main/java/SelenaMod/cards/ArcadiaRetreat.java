package SelenaMod.cards;

import SelenaMod.cardEffects.RareCardEffect;
import SelenaMod.powers.OverridePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ArcadiaRetreat extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(ArcadiaRetreat.class.getSimpleName());

    public ArcadiaRetreat() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.setMagic(1);
        this.setSecondMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(2);
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractPlayer, new DamageInfo(abstractPlayer, this.secondMagicVar, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
        addPowerToSelf(new OverridePower(abstractPlayer, this.magicNumber, new RareCardEffect(this.cardID, this.magicNumber)));
    }
}

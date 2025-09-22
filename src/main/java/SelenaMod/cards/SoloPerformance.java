package SelenaMod.cards;

import SelenaMod.cardEffects.ExhaustCardEffect;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoloPerformance extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(SoloPerformance.class.getSimpleName());
    public boolean activeFlag = false;

    public SoloPerformance() {
        super(ID, -2, CardType.ATTACK, CardRarity.RARE, CardTarget.NONE);
        this.setMagic(50);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(10);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (activeFlag) {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.magicNumber, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            addPowerToSelf(new TonePower(abstractPlayer, 1, new ExhaustCardEffect(this.cardID, 1)));
            activeFlag = false;
        }

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return this.isInAutoplay;
    }
}

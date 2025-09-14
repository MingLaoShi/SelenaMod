package SelenaMod.cards;

import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.ToneAndSpaceData;
import SelenaMod.utils.ToneAndSpaceDataManager;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Letter extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Letter.class.getSimpleName());
    public AbstractCardEffect overrideEffect = null;

    public Letter() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.overrideEffect == null) {
            addDrawCardAction();
            addToBot(new PutOnDeckAction(abstractPlayer, abstractPlayer, this.magicNumber, false));
        } else {
            addToBot(this.overrideEffect.trigger(abstractMonster));
        }
    }

    public void setOverrideEffect(ToneAndSpaceData overrideEffect) {
        this.target = ModHelper.adjustTarget(this.target, overrideEffect.target);
        this.overrideEffect = ToneAndSpaceDataManager.getEffectInstance(overrideEffect);
        assert this.overrideEffect != null;
        this.rawDescription = this.overrideEffect.getDescription();
        this.initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (this.overrideEffect != null) {
            this.overrideEffect.applyPowers();
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (this.overrideEffect != null) {
            this.overrideEffect.calcCardDamage(mo);
        }
    }
}

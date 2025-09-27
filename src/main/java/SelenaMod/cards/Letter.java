package SelenaMod.cards;

import SelenaMod.actions.PutToDeckAction;
import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Letter extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Letter.class.getSimpleName());
    public AbstractCardEffect overrideEffect = null;

    public Letter() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        this.setMagic(1);
    }

    public Letter(String id, int i, CardType cardType, CardRarity cardRarity, CardTarget cardTarget) {
        super(id, i, cardType, cardRarity, cardTarget);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.overrideEffect == null) {
            addDrawCardAction();
            addToBot(new PutToDeckAction(abstractPlayer, abstractPlayer, this.magicNumber, false));
        } else {
            addToBot(this.overrideEffect.trigger(abstractMonster));
        }
    }

    public void setOverrideEffect(AbstractCardEffect overrideEffect) {
        this.target = ModHelper.adjustTarget(this.target, overrideEffect.data.getTarget());
        this.overrideEffect = overrideEffect;
        this.initializeDescription();
    }

    @Override
    public void initializeDescription() {
        if (this.overrideEffect != null) {
            this.rawDescription = this.overrideEffect.getDescription();
        }
        super.initializeDescription();
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


    public static boolean isLetterCard(AbstractCard card){
        return card instanceof Letter;
    }
}

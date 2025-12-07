package SelenaMod.cards;

import SelenaMod.effects.SpotEffect;
import SelenaMod.interfaces.IFirstSight;
import SelenaMod.modifiers.RepeatModifier;
import SelenaMod.powers.AsFirstSightPower;
import SelenaMod.utils.ModHelper;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoloPerformance extends CustomSelenaCard implements IFirstSight {
    public static String ID = ModHelper.makeID(SoloPerformance.class.getSimpleName());
    public boolean activeFlag = false;

    public SoloPerformance() {
        super(ID, -2, CardType.ATTACK, CardRarity.RARE, CardTarget.NONE);
        this.setMagic(200);
        CardModifierManager.addModifier(this,new RepeatModifier());
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (activeFlag) {
            addToBot(new VFXAction(new SpotEffect(abstractPlayer, abstractMonster), 1.0F));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.magicNumber, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
//            addTonePower(new TonePower(abstractPlayer, 1, new ExhaustCardEffect(this.cardID, 1)));
            activeFlag = false;
        }

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return this.isInAutoplay;
    }

    @Override
    public void triggerWhenDrawn() {
        if (AsFirstSightPower.isFirstSight(this)) {
            this.onFirstSight();
            this.firstSight = false;
        }
    }

    @Override
    public void onFirstSight() {
        if (this.upgraded) {
            addToBot(new DrawCardAction(1));
        }
        addToBot(new ExhaustAction(1, false, false));
    }
}

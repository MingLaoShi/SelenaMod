package SelenaMod.cardEffects;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class DamageEffect extends AbstractCardEffect {
    public DamageEffect(String id, int amount, int count) {
        super(id);
        this.data.setAmount(amount);
        this.data.setAmount2(count);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(DamageEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription(toneStrings.DESCRIPTIONS[0]);
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
        card.baseDamage = data.amount;
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature tar) {
        return new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0; i < DamageEffect.this.data.amount2; i++) {
                    addToTop(new DamageAction(tar, new DamageInfo(AbstractDungeon.player, card.damage, DamageInfo.DamageType.NORMAL)));
                }
                this.isDone = true;
            }
        };
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    @Override
    public String getDescription() {
        return String.format(this.data.getDescription(), String.valueOf(card.damage), String.valueOf(this.data.amount2));
    }

    @Override
    public AbstractCardEffect clone() {
        return new DamageEffect(this.data.id,this.data.amount,this.data.amount2);
    }


    @Override
    public int val(AbstractCard card) {
        return this.card.damage;
    }

    @Override
    public int baseVal(AbstractCard card) {
        return this.data.amount;
    }

    @Override
    public boolean modified(AbstractCard card) {
        return this.data.amount != this.card.damage;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}

package SelenaMod.cardEffects;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class DamageEffect extends AbstractCardEffect {
    public DamageEffect(int amount, int count) {
        super();
        this.data.setAmount(amount);
        this.data.setAmount2(count);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(DamageEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription(toneStrings.DESCRIPTIONS[0]);
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
        card.baseDamage = data.amount;
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new DamageAction(target, new DamageInfo(AbstractDungeon.player, card.damage, DamageInfo.DamageType.NORMAL));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    @Override
    public String getDescription() {
        return String.format(this.data.getDescription(), String.valueOf(card.damage), String.valueOf(this.data.amount2));
    }
}

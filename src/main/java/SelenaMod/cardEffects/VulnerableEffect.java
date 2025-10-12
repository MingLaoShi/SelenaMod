package SelenaMod.cardEffects;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class VulnerableEffect extends AbstractCardEffect {
    public VulnerableEffect(String id, int amount) {
        super(id);
        this.data.setAmount(amount);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(VulnerableEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription(toneStrings.DESCRIPTIONS[0]);
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new ApplyPowerAction(target, AbstractDungeon.player, new VulnerablePower(target, this.data.amount, false), this.data.amount);
    }

    @Override
    public String getDescription() {
        return String.format(this.data.getDescription(), String.valueOf(this.data.amount));
    }

    @Override
    public AbstractCardEffect clone() {
        return new VulnerableEffect(this.data.id,this.data.amount);
    }

    @Override
    public int val(AbstractCard card) {
        return this.data.amount;
    }

    @Override
    public int baseVal(AbstractCard card) {
        return this.data.amount;
    }

    @Override
    public boolean modified(AbstractCard card) {
        return false;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}

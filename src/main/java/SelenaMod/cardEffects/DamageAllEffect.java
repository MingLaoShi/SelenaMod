package SelenaMod.cardEffects;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class DamageAllEffect extends AbstractCardEffect {
    public DamageAllEffect(int amount) {
        super();
        this.data.setAmount(amount);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(DamageAllEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription(toneStrings.DESCRIPTIONS[0]);
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
        card.baseDamage = data.amount;
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new DamageAllEnemiesAction(AbstractDungeon.player, data.amount, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public String getDescription() {
        return String.format(this.data.getDescription(), String.valueOf(this.data.amount));
    }
}

package SelenaMod.cardEffects;

import SelenaMod.utils.ToneAndSpaceData;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DamageEffect extends AbstractCardEffect{
    public DamageEffect(ToneAndSpaceData data) {
        super(data);
        card.baseDamage=data.amount;
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new DamageAction(target,new DamageInfo(AbstractDungeon.player,card.damage, DamageInfo.DamageType.NORMAL));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    @Override
    public String getDescription() {
        return String.format(this.data.getDescription(),String.valueOf(card.damage));
    }
}

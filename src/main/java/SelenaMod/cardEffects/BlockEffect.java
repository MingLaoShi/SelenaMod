package SelenaMod.cardEffects;

import SelenaMod.utils.ToneAndSpaceData;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlockEffect extends AbstractCardEffect{
    public BlockEffect(ToneAndSpaceData data) {
        super(data);
        card.baseBlock=data.amount;
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new GainBlockAction(AbstractDungeon.player,card.block);
    }


    @Override
    public String getDescription() {
        return String.format(this.data.getDescription(),String.valueOf(card.block));
    }
}

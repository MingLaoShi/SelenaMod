package SelenaMod.cardEffects;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class BlockEffect extends AbstractCardEffect {
    public BlockEffect(String id,int amount) {
        super(id);
        this.data.setAmount(amount);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(BlockEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription(toneStrings.DESCRIPTIONS[0]);
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
        card.baseBlock = data.amount;
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new GainBlockAction(AbstractDungeon.player, card.block);
    }


    @Override
    public String getDescription() {
        return String.format(this.data.getDescription(), String.valueOf(card.block));
    }
}

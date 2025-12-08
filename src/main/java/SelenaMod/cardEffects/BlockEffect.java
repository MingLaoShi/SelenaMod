package SelenaMod.cardEffects;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class BlockEffect extends AbstractCardEffect {
    public BlockEffect(String id, int amount) {
        super(id);
        this.data.setAmount(amount);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(BlockEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription(toneStrings.DESCRIPTIONS[0]);
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
        card.baseBlock = data.amount;
        card.block = card.baseBlock;
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        return new GainBlockAction(AbstractDungeon.player, card.block);
    }

    @Override
    public AbstractCardEffect clone() {
        return new BlockEffect(this.data.id,this.data.amount);
    }


//    @Override
//    public String getDescription() {
//        return String.format(this.data.getDescription(), this.numberColoring(this.data.amount, card.block));
//    }

    @Override
    public int val(AbstractCard card) {
        return this.card.block;
    }

    @Override
    public int baseVal(AbstractCard card) {
        return this.data.amount;
    }

    @Override
    public boolean modified(AbstractCard card) {
        return this.data.amount != this.card.block;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}

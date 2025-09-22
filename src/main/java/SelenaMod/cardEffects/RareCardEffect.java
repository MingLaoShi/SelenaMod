package SelenaMod.cardEffects;

import SelenaMod.actions.SelectRareCardAction;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class RareCardEffect extends AbstractCardEffect {

    public RareCardEffect(String id, int amount) {
        super(id);
        this.data.setAmount(amount);
        PowerStrings toneStrings = CardCrawlGame.languagePack.getPowerStrings(ModHelper.makeID(RareCardEffect.class.getSimpleName()));
        this.data.setName(toneStrings.NAME);
        this.data.setDescription(toneStrings.DESCRIPTIONS[0]);
        this.data.setTarget(toneStrings.DESCRIPTIONS.length > 1 ? toneStrings.DESCRIPTIONS[1] : "");
    }

    @Override
    public AbstractGameAction trigger(AbstractCreature target) {
        if (this.data.amount == 1) {
            AbstractCard card1 = AbstractDungeon.getCard(AbstractCard.CardRarity.RARE, AbstractDungeon.cardRandomRng).makeCopy();
            card1.setCostForTurn(0);
            return new MakeTempCardInHandAction(card1);
        } else {
            return new SelectRareCardAction(this.data.amount);
        }
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

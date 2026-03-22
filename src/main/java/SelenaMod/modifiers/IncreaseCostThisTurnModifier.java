package SelenaMod.modifiers;

import SelenaMod.utils.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class IncreaseCostThisTurnModifier extends AbstractCardModifier {
    public static String ID = ModHelper.makeID(IncreaseCostThisTurnModifier.class.getSimpleName());
    public int amount;
    public int count;
    private int active = 0;
    private boolean removed = false;

    public IncreaseCostThisTurnModifier() {
        this(1, 1);
    }

    public IncreaseCostThisTurnModifier(int amount, int count) {
        this.amount = amount;
        this.count = count;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        IncreaseCostThisTurnModifier copy = new IncreaseCostThisTurnModifier();
        copy.count = this.count;
        copy.amount = this.amount;
        return copy;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        this.active = this.amount;
        card.cost = card.cost + this.amount;
        card.costForTurn = card.cost;
        card.isCostModified = this.active > 0;
    }

    @Override
    public void onRemove(AbstractCard card) {
        if (!this.removed) {
            super.onRemove(card);
            card.cost = Math.max(0, card.cost - this.active);
            ModHelper.logger.info("remove IncreaseCostThisTurnModifier{}:{},current cost is {}", card.name, card.uuid, card.cost);
            card.costForTurn = card.cost;
            card.isCostModified = false;
        }
        this.removed = true;
    }

//    @Override
//    public boolean removeAtEndOfTurn(AbstractCard card) {
//        return true;
//    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return false;
    }
}
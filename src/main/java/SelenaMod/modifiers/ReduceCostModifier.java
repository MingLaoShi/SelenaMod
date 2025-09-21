package SelenaMod.modifiers;

import SelenaMod.utils.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ReduceCostModifier extends AbstractCardModifier {
    public static String ID = ModHelper.makeID(ReduceCostModifier.class.getSimpleName());
    public int amount;
    public int count;
    private int active = 0;

    public ReduceCostModifier() {
        this(1, 1);
    }

    public ReduceCostModifier(int amount, int count) {
        this.amount = amount;
        this.count = count;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        ReduceCostModifier copy = new ReduceCostModifier();
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
        this.active = Math.min(card.cost, this.amount);
        card.cost = Math.max(0, card.cost - this.amount);
        card.costForTurn = card.cost;
    }

//    @Override
//    public boolean onBattleStart(AbstractCard card) {
//        addToBot(new AbstractGameAction() {
//            @Override
//            public void update() {
//                for(AbstractCard card1: AbstractDungeon.player.masterDeck.group){
//                    if(card1.uuid.equals(card.uuid)){
//                        ArrayList<AbstractCardModifier> modifierList= CardModifierManager.getModifiers(card1,ReduceCostModifier.ID);
//                        for(AbstractCardModifier modifier:modifierList){
//                            if(modifier instanceof ReduceCostModifier){
//                                ((ReduceCostModifier) modifier).amount--;
//                                if(((ReduceCostModifier) modifier).amount==0){
//                                    CardModifierManager.removeSpecificModifier(card1,modifier,true);
//                                }
//                            }
//                        }
//                        break;
//                    }
//                }
//                this.isDone=true;
//            }
//        });
//        return true;
//    }

    @Override
    public void onRemove(AbstractCard card) {
        super.onRemove(card);
        card.cost += this.active;
        card.costForTurn = card.cost;
    }
}

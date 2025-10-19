//package SelenaMod.powers;
//
//import SelenaMod.utils.ModHelper;
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
//import com.megacrit.cardcrawl.cards.tempCards.Miracle;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.PowerStrings;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//public class AdditionCollectPower extends AbstractPower {
//    public static final String POWER_ID = ModHelper.makeID(AdditionCollectPower.class.getSimpleName());
//    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
//
//    public AdditionCollectPower(AbstractCreature owner, int amount) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = amount;
//        this.type = PowerType.BUFF;
//        ModHelper.initPower(this);
//        if(this.amount>999){
//            this.amount=999;
//        }
//        updateDescription();
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = String.format(strings.DESCRIPTIONS[0], this.amount);
//    }
//
//    @Override
//    public void stackPower(int stackAmount) {
//        super.stackPower(stackAmount);
//        if(this.amount>999){
//            this.amount=999;
//        }
//    }
//
//    @Override
//    public void onEnergyRecharge() {
//        flash();
//        Miracle miracle=new Miracle();
//        addToBot(new MakeTempCardInDrawPileAction());
//    }
//}

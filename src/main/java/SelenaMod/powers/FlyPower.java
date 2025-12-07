//package SelenaMod.powers;
//
//import SelenaMod.utils.ModHelper;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.PowerStrings;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//public class FlyPower extends AbstractPower {
//    public static String POWER_ID= ModHelper.makeID("FlyPower");
//    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
//
//    public FlyPower(AbstractCreature owner, int amount) {
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = amount;
//        this.type = PowerType.BUFF;
//        ModHelper.initPower(this);
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = String.format(strings.DESCRIPTIONS[0], this.amount);
//    }
//
//}

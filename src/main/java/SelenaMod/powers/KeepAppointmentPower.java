package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class KeepAppointmentPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(KeepAppointmentPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private AbstractCreature target;
    private int damage;
    private int count;
    private static int COUNT=0;
    public KeepAppointmentPower(AbstractCreature owner,AbstractCreature target,int amount,int damage,int count){
        this.ID=POWER_ID;
        this.owner=owner;
        this.amount=amount;
        this.damage=damage;
        this.count=count;
        this.target=target;
        this.type=PowerType.BUFF;
        ModHelper.initPower(this);
        this.ID=POWER_ID+COUNT;
        COUNT++;
    }

    @Override
    public void updateDescription() {
        this.description=String.format(strings.DESCRIPTIONS[0],this.target.name,this.damage,this.count);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(this.owner,this.owner,POWER_ID,1));
    }

    @Override
    public void onInitialApplication() {
        if(this.amount<=0){
            addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
        }
    }

    @Override
    public void onRemove() {
        for(int i=0;i<this.count;i++){
            addToBot(new LoseHPAction(this.target,this.owner,this.damage, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }
}

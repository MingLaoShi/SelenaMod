package SelenaMod.cards;

import SelenaMod.actions.EasyXCostAction;
import SelenaMod.powers.KeepAppointmentPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KeepAppointment extends CustomSelenaCard{
    public static String ID= ModHelper.makeID(KeepAppointment.class.getSimpleName());
    public KeepAppointment() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.setMagic(20);
        this.exhaust=true;
        this.isEthereal=true;
        this.cardsToPreview=new Miracle();
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(5);
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new EasyXCostAction(this,(i, params) -> {
            addToTop(new ApplyPowerAction(abstractPlayer,abstractPlayer,new KeepAppointmentPower(abstractPlayer,abstractMonster,5-i,this.magicNumber,i)));
            return true;
        },this.magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        if(this.firstSight){
            Miracle miracle=new Miracle();
            if(this.upgraded){
                miracle.upgrade();
            }
            addToBot(new MakeTempCardInHandAction(miracle));
        }
    }
}

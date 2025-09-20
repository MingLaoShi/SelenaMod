package SelenaMod.cards;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.BufferPower;

public class PhantomMelody extends CustomSelenaCard{
    public static String ID = ModHelper.makeID(PhantomMelody.class.getSimpleName());
    public PhantomMelody() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(3);
        this.exhaust=true;
        this.selfRetain=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addPowerToSelf(new BufferPower(abstractPlayer,1));

    }

    @Override
    public void triggerWhenDrawn() {
        if(this.firstSight){
            this.firstSight=false;
            addPowerToSelf(new BlurPower(AbstractDungeon.player,this.magicNumber));
        }
    }
}

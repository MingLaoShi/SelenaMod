package SelenaMod.cards;

import SelenaMod.cardEffects.PrayerEffect;
import SelenaMod.powers.OverridePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.InvinciblePower;

public class Storm extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Storm.class.getSimpleName());

    public Storm() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.setMagic(4);
        this.setSecondMagic(12);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(AbstractDungeon.player.hasPower(InvinciblePower.POWER_ID)){
                    AbstractPower p=AbstractDungeon.player.getPower(InvinciblePower.POWER_ID);
                    p.amount=Storm.this.secondMagicVar;
                    p.updateDescription();
                }else{
                    addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new InvinciblePower(abstractPlayer, Storm.this.secondMagicVar)));
                }
                this.isDone=true;
            }
        });
//        addPowerToSelf(new RemoveInvinciblePower(abstractPlayer, this.upgraded ? 1 : 0));
        addTonePower(new OverridePower(abstractPlayer, this.magicNumber, new PrayerEffect(this.cardID, this.magicNumber)),abstractMonster);
    }
}

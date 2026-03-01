package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.CuriosityPower;
import com.megacrit.cardcrawl.powers.TimeWarpPower;

public class SecretCorruptHeartPower extends AbstractPower implements InvisiblePower {
    public static final String POWER_ID = ModHelper.makeID(SecretCorruptHeartPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private int currentHp = 0;
    private static int STATE1 = 600;
    private static int STATE2 = 200;

    public SecretCorruptHeartPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        this.currentHp = this.owner.currentHealth;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int hp = owner.currentHealth;
                if (currentHp > STATE1 && hp <= STATE1) {
                    addToTop(new ApplyPowerAction(owner, owner, new CuriosityPower(owner, 1)));
                }
                if (currentHp > STATE2 && hp <= STATE2) {
                    addToTop(new ApplyPowerAction(owner, owner, new TimeWarpPower(owner)));
                }
                currentHp = hp;
                isDone = true;
            }
        });
        return super.onAttacked(info, damageAmount);
    }
}

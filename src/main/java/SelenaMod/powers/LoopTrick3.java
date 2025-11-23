package SelenaMod.powers;

import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.HashMap;
import java.util.Map;

public class LoopTrick3 extends AbstractPower implements InvisiblePower {
    public static final String POWER_ID = ModHelper.makeID(LoopTrick3.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private Map<AbstractCard, Integer> cardPlayCount = new HashMap<>();

    public LoopTrick3(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        ModHelper.initPower(this);
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (this.cardPlayCount.containsKey(card)) {
            this.cardPlayCount.put(card, this.cardPlayCount.get(card) + 1);
        } else {
            this.cardPlayCount.put(card, 1);
        }
        if (this.cardPlayCount.get(card) >= 4) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (AbstractDungeon.player.currentHealth > 0) {
                        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, AbstractDungeon.player.currentHealth / 2, DamageInfo.DamageType.HP_LOSS));
                    }
                    this.isDone = true;
                }
            });
        }
    }
}

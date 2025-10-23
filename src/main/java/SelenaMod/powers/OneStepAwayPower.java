package SelenaMod.powers;

import SelenaMod.modifiers.OneStepAwayModifier;
import SelenaMod.utils.ModHelper;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class OneStepAwayPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(OneStepAwayPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public OneStepAwayPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[0], this.amount);
    }


    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = AbstractDungeon.player.drawPile.size() - 3; i >= 0; i--) {
                    for (int j = 0; j < OneStepAwayPower.this.amount; j++) {
                        CardModifierManager.addModifier(AbstractDungeon.player.drawPile.group.get(i), new OneStepAwayModifier());
                    }
                }
                this.isDone = true;
            }
        });

    }
}

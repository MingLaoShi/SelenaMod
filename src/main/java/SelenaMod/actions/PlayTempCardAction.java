package SelenaMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Objects;

public class PlayTempCardAction extends AbstractGameAction {
    private AbstractCard card;
    private boolean exhaust;

    public PlayTempCardAction(AbstractCard card, AbstractCreature target, boolean exhaust) {
        this.card = card;
        this.exhaust = exhaust;
        this.target = target;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.getCurrRoom().souls.remove(this.card);
            card.exhaust = this.exhaust;
            card.unfadeOut();
//            AbstractDungeon.player.limbo.group.add(this.card);
            card.current_y = -200.0F * Settings.scale;
            card.current_x = 0.0F;
            card.target_x = Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;

            card.applyPowers();
            if (Objects.isNull(this.target)) {
                this.target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            }
            card.calculateCardDamage((AbstractMonster) this.target);
            card.isInAutoplay = true;
            addToTop(new NewQueueCardAction(card, this.target, false, true));
            addToTop(new UnlimboAction(card));


        }
        this.isDone = true;
    }
}

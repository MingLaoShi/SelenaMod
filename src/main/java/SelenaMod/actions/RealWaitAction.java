package SelenaMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class RealWaitAction extends AbstractGameAction {

    public RealWaitAction(float duration) {
        this.actionType = ActionType.WAIT;
        this.duration = duration;
    }
    @Override
    public void update() {
        tickDuration();
    }
}

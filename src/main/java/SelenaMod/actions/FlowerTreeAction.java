package SelenaMod.actions;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;

public class FlowerTreeAction extends AbstractGameAction {
    private int blockAmount = 0;

    public FlowerTreeAction(int blockAmount) {
        this.blockAmount = blockAmount;
    }

    @Override
    public void update() {
        List<AbstractCreature> creatureList = ModHelper.GetAllCreatures(true);

        List<AbstractCreature> blockedCreatures = new ArrayList<>();
        for (AbstractCreature creature : creatureList) {
            if (creature.currentBlock > 0) {
                blockedCreatures.add(creature);
                creature.loseBlock();
            }
        }
        if (blockedCreatures.isEmpty()) {
            addToTop(new GainBlockAction(AbstractDungeon.player, this.blockAmount));
        }
        this.isDone = true;
    }
}

package SelenaMod.powers;

import SelenaMod.cards.CustomSelenaCard;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.HashMap;
import java.util.Map;

public class AsFirstSightPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(AsFirstSightPower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private static Map<AbstractCard, Integer> drawnCards = new HashMap<>();

    public AsFirstSightPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
    }

    public static boolean isFirstSight(AbstractCard card) {
        if (!(card instanceof CustomSelenaCard)) {
            return false;
        }
        CustomSelenaCard customSelenaCard = (CustomSelenaCard) card;
        if (customSelenaCard.firstSight) {
            return true;
        }
        if (!AbstractDungeon.player.hasPower(POWER_ID)) {
            return false;
        }
        AsFirstSightPower power = (AsFirstSightPower) AbstractDungeon.player.getPower(POWER_ID);
        if (drawnCards.containsKey(card)) {
            int count = drawnCards.get(card);
            if (count < power.amount) {
                drawnCards.put(card, count + 1);
                return true;
            } else {
                return false;
            }
        } else {
            drawnCards.put(card, 1);
            return true;
        }
    }

    @Override
    public void updateDescription() {
        this.description = String.format(strings.DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void atStartOfTurn() {
        drawnCards.clear();
    }

    @Override
    public void onInitialApplication() {
        drawnCards.clear();
    }
}

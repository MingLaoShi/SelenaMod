package SelenaMod.utils;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ToneAndSpaceDataManager {

    private static Map<ToneAndSpaceType, Function<ToneAndSpaceData, AbstractGameAction>> dataMap = new HashMap<>();

    public enum ToneAndSpaceType {
        SCRY
    }

    public static void registerData(ToneAndSpaceType id, Function<ToneAndSpaceData, AbstractGameAction> dataObjectFunction) {
        dataMap.put(id, dataObjectFunction);
    }

    public static void registerAll() {
        registerData(ToneAndSpaceType.SCRY, ToneAndSpaceDataManager::scry);
    }

    public static AbstractGameAction getAction(ToneAndSpaceData data) {
        if (dataMap.containsKey(data.ID)) {
            return new AbstractGameAction() {
                @Override
                public void update() {
                    addToTop(dataMap.get(data.ID).apply(data));
                    this.isDone = true;
                }
            };
        } else {
            return new AbstractGameAction() {
                @Override
                public void update() {
                    this.isDone = true;
                }
            };
        }

    }


    public static AbstractGameAction scry(ToneAndSpaceData data) {
        return new ScryAction(data.amount);
    }

    private static void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    private static void addToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }
}

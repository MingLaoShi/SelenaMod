package SelenaMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CtBehavior;

import java.util.ArrayList;

public class ActionManagerPatch {
    public static ArrayList<AbstractCard> LastTurnCards=new ArrayList<>();
    @SpirePatch(clz = GameActionManager.class,method = "getNextAction")
    public static class getNextActionPatch{
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(GameActionManager __instance){
            LastTurnCards=new ArrayList<>(__instance.cardsPlayedThisTurn);
        }

        public static class Locator extends SpireInsertLocator{

            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher=new Matcher.MethodCallMatcher(ArrayList.class,"clear");
                return LineFinder.findAllInOrder(ctBehavior,matcher);
            }
        }
    }

    @SpirePatch(clz = GameActionManager.class,method = "clear")
    public static class clearPatch{
        @SpirePostfixPatch
        public static void Postfix(GameActionManager __instance){
            LastTurnCards.clear();
        }
    }
}

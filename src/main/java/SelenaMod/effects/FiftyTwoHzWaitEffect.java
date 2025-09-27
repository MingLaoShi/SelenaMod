package SelenaMod.effects;

import SelenaMod.cardEffects.DamageEffect;
import SelenaMod.cards.FiftyTwoHz;
import SelenaMod.modifiers.NotTriggerYourselfModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class FiftyTwoHzWaitEffect extends AbstractGameEffect {
    private FiftyTwoHz card;
    private String methodName;
    private CardGroup group=new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private float target_x;
    public FiftyTwoHzWaitEffect(FiftyTwoHz card){
        this.card=card;
        this.duration=1.0F;
        this.target_x=this.card.current_x- AbstractCard.IMG_WIDTH*this.card.drawScale;

    }

    public FiftyTwoHzWaitEffect(AbstractCard card, String methodName) {
        this((FiftyTwoHz) card);
        this.methodName=methodName;
        group.addToBottom(card);
    }

    @Override
    public void update() {
        this.card.current_x = MathHelper.cardLerpSnap(this.card.current_x, this.target_x);

        if(this.duration==0.0F){
            this.duration=1.0F;
            this.card.target_x=this.card.current_x;
            AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    FiftyTwoHzWaitEffect.this.isDone=true;
                    switch (methodName) {
                        case "moveToDiscardPile":
                            group.moveToDiscardPile(card);
                            break;
                        case "moveToDeck":
                            group.moveToDeck(card, true);
                            break;
                        case "moveToHand":
                            group.moveToHand(card);
                            AbstractDungeon.player.onCardDrawOrDiscard();
                            break;
                        case "moveToExhaustPile":
                            group.moveToExhaustPile(card);
                            CardCrawlGame.dungeon.checkForPactAchievement();
                            break;
                    }
                    this.isDone=true;
                }
            });

        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        this.card.render(spriteBatch);
    }

    @Override
    public void dispose() {

    }


    @SpirePatch(clz = UseCardAction.class,method = "update")
    public static class updatePatch{
        @SpireInstrumentPatch
        public static ExprEditor instrument(){
            return new ExprEditor(){
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if(m.getClassName().equals(CardGroup.class.getName())&&m.getMethodName().contains("moveTo")
                    ){
                        m.replace(String.format("{if(%s.replaceCondition($1)){" +
                                "%s.replace($1,\"%s\");" +
                                "}else{" +
                                "$_ = $proceed($$);" +
                                "}}", FiftyTwoHzWaitEffect.class.getName(), FiftyTwoHzWaitEffect.class.getName(),m.getMethodName()));
                    }
                }
            };
        }
    }

    public static boolean replaceCondition(AbstractCard card){
        if(card instanceof FiftyTwoHz&& !CardModifierManager.hasModifier(card, NotTriggerYourselfModifier.ID)&&FiftyTwoHz.findOtherFiftyTowHz(card)){
            return true;
        }else{
            CardModifierManager.removeModifiersById(card,NotTriggerYourselfModifier.ID,true);
            for(AbstractGameEffect effect:AbstractDungeon.effectList){
                if(effect instanceof FiftyTwoHzWaitEffect){
                    effect.duration=0.0F;
                }
            }
            return false;
        }
    }

    public static void replace(AbstractCard card,String methodName){
        AbstractDungeon.player.hand.removeCard(card);
        AbstractDungeon.effectList.add(new FiftyTwoHzWaitEffect(card,methodName));
    }
}

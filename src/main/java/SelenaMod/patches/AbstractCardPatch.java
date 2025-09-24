package SelenaMod.patches;

import SelenaMod.actions.LetterWaitAction;
import SelenaMod.cards.Letter;
import SelenaMod.utils.ModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.apache.commons.lang3.StringUtils;

public class AbstractCardPatch {
    @SpirePatch(clz = AbstractCard.class, method = "renderDescriptionCN")
    public static class renderDescriptionCNPatch {
        @SpireInstrumentPatch
        public static ExprEditor instrumentPatch() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(FontHelper.class.getName()) && m.getMethodName().equals("renderRotatedText")&&m.getLineNumber()>2310) {
                        m.replace(String.format("{if(%s.replaceCondition(this,this.description.get(i))){" +
                                "%s.renderDescription($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,this.description.get(i));" +
                                "}else{" +
                                "$_ = $proceed($$);" +
                                "}}", AbstractCardPatch.class.getName(), AbstractCardPatch.class.getName()));
                    }
                }
            };
        }
    }

    public static boolean replaceCondition(AbstractCard card, Object line) {
        if(!(line instanceof DescriptionLine)){
            return false;
        }
        if(AbstractDungeon.actionManager==null){
            return false;
        }
        AbstractGameAction action=getFirstWaitAction();
        if(action!=null){
            return ((LetterWaitAction) action).isCurrentLine(card, (DescriptionLine) line);
        }
        return false;
        //        return false;
//        return AbstractDungeon.actionManager != null
//                && AbstractDungeon.actionManager.currentAction instanceof LetterWaitAction
//                && ((LetterWaitAction) AbstractDungeon.actionManager.currentAction).isCurrentLine(card, (DescriptionLine) line);
    }
    public static GlyphLayout layout=new GlyphLayout();
    public static void renderDescription(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float offsetX, float offsetY, float angle, boolean roundY, Color c,Object descriptionLine) {
        if(StringUtils.isBlank(msg)){
            return;
        }
        DescriptionLine line= (DescriptionLine) descriptionLine;
//            FontHelper.renderRotatedText(sb,text,x,y,width,lineSpacing,angle,shadow,alpha,scale,singleLine,startIndex);
        ModHelper.logger.info("replace render description");
        msg=ModHelper.removeBracketsContent(msg);
        int index = getFirstWaitAction().getGradientIndex(msg,line);
//        index=index-4;
        if (index-LetterWaitAction.COLOR_STEP >= msg.length()) {
            FontHelper.renderRotatedText(sb, font, msg, x, y, offsetX, offsetY, angle, roundY, c);
        }else if(index>=0){
            float offset=0.0F;
            String solidText=msg.substring(0,Math.max(0,index-LetterWaitAction.COLOR_STEP));
            String lastText=msg.substring(Math.max(0,index-LetterWaitAction.COLOR_STEP));
            layout.setText(font,lastText);
            offset=layout.width;
            if(StringUtils.isBlank(lastText)){
                offset=0.0F;
            }
            FontHelper.renderRotatedText(sb,font,solidText,x-offset/2,y,offsetX,offsetY,angle,roundY,c);
            Color copy=c.cpy();
            int gradientIndex=index-LetterWaitAction.COLOR_STEP;
            while(gradientIndex<index&&gradientIndex<msg.length()){
                copy.a=(1.0F/(LetterWaitAction.COLOR_STEP+1))*(index-gradientIndex);
                if(gradientIndex<0){
                    gradientIndex++;
                    continue;
                }
                String gradientText=msg.substring(gradientIndex,gradientIndex+1);
                lastText=msg.substring(gradientIndex+1);
                layout.setText(font,lastText);
                offset=layout.width;
                if(StringUtils.isBlank(lastText)){
                    offset=0.0F;
                }
                layout.setText(font,msg);
                float total=layout.width;
                layout.setText(font,gradientText);
                float textWidth=layout.width;
                FontHelper.renderRotatedText(sb,font,gradientText,x+total/2-offset-textWidth/2.0F,y,offsetX,offsetY,angle,roundY,copy);
                gradientIndex++;
            }
        }
    }

    public static LetterWaitAction getFirstWaitAction(){
        AbstractGameAction action=AbstractDungeon.actionManager.currentAction;
        if(action instanceof LetterWaitAction){
            return (LetterWaitAction) action;
        }
        for(AbstractGameAction action1:AbstractDungeon.actionManager.actions){
            if(action1 instanceof LetterWaitAction){
                return (LetterWaitAction) action1;
            }
        }
        return null;
    }

}

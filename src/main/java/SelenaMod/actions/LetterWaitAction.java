package SelenaMod.actions;

import SelenaMod.utils.ModHelper;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class LetterWaitAction extends AbstractGameAction {
    private final AbstractCard card;
    private boolean firstTick = true;
    public ArrayList<DescriptionLine> lines;
    private int index=0;
    private static final float TEXT_SPEED=0.05F;
    private float indexDuration=TEXT_SPEED;
    private ArrayList<String> renderTexts=new ArrayList<>();

    private int totalTextLen=0;

    public static int COLOR_STEP=9;

    public LetterWaitAction(int amount, AbstractCard card, ArrayList<DescriptionLine> lines) {
        this.card = card;
        this.amount = amount;
        this.actionType = ActionType.WAIT;
        this.duration = 0.1f * amount;
        this.lines=lines;
    }

    @Override
    public void update() {
        if (firstTick) {
            card.flash();
            firstTick = false;
        }
        this.tickDuration();
        indexDuration-= Gdx.graphics.getDeltaTime();
        if(indexDuration<=0.0F){
            this.indexDuration=TEXT_SPEED;
            this.index++;
        }
    }

    public boolean isCurrentLine(AbstractCard card,DescriptionLine line){
        return this.card==card&&this.lines.stream().anyMatch(l-> l.getText().equals(line.getText()));
    }

    public int getGradientIndex(String msg,DescriptionLine line){
        msg=ModHelper.removeBracketsContent(msg);
            if(!renderTexts.contains(msg)){
                renderTexts.add(msg);
                totalTextLen+=msg.length();
            }else if(index>totalTextLen){
                this.isDone=true;
                card.flash();
                if(AbstractDungeon.actionManager.actions.stream().noneMatch(action -> action instanceof LetterWaitAction)){
                    addToTop(new RealWaitAction(0.1F));
                }
            }
            int msgIndex=renderTexts.indexOf(msg);
            int resIndex=index;
            for(int i=0;i<msgIndex;i++){
                resIndex-=this.renderTexts.get(i).length();
            }
            return resIndex;


    }

}

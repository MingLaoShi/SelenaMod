package SelenaMod.actions;

import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class SelectCardFromDiscardToDrawPileAction extends AbstractGameAction {
    public UIStrings GridSelectTitle = CardCrawlGame.languagePack.getUIString(ModHelper.makeID("GridSelectTitle"));

    public SelectCardFromDiscardToDrawPileAction(int amount){
        this.amount=amount;
        this.duration= Settings.ACTION_DUR_FAST;

    }
    @Override
    public void update() {
        if(this.duration==Settings.ACTION_DUR_FAST){
            if(AbstractDungeon.player.discardPile.isEmpty()){
                this.isDone=true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.discardPile,this.amount,false,GridSelectTitle.TEXT[1]);
        }else{
            if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()){
                for(AbstractCard c:AbstractDungeon.gridSelectScreen.selectedCards){
                    AbstractDungeon.player.discardPile.moveToDeck(c,false);
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.isDone=true;
            }
        }
        this.tickDuration();
    }
}

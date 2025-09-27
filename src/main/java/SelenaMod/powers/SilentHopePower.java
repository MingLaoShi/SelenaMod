package SelenaMod.powers;

import SelenaMod.actions.DrawOrDisCardToHandAction;
import SelenaMod.cards.Letter;
import SelenaMod.utils.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class SilentHopePower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makeID(SilentHopePower.class.getSimpleName());
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public SilentHopePower(AbstractCreature owner,int amount){
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        ModHelper.initPower(this);
    }

    @Override
    public void updateDescription() {
        this.description=String.format(strings.DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> cards=new ArrayList<>();
                cards.addAll(AbstractDungeon.player.drawPile.group);
                cards.addAll(AbstractDungeon.player.discardPile.group);
                addToTop(new SelectCardsAction(cards,amount, CardCrawlGame.languagePack.getUIString(ModHelper.makeID("GridSelectTitle")).TEXT[0],
                        true,c->c instanceof Letter, selectedCards->{
                    for(int i=selectedCards.size()-1;i>=0;i--){
                        AbstractCard card=selectedCards.get(i);
                        addToTop(new DrawOrDisCardToHandAction(card));
                    }
                }));
                this.isDone=true;
            }
        });
    }
}

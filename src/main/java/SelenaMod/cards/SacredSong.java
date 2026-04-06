package SelenaMod.cards;

import SelenaMod.actions.PlayTempCardAction;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SacredSong extends CustomSelenaCard {


    public static String ID = ModHelper.makeID("SacredSong");

    public SacredSong() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        // 随机选择一张留白效果的牌
        List<String> whiteSpaceCards = new ArrayList<>();
        whiteSpaceCards.add("SelenaMod:Overture");
        whiteSpaceCards.add("SelenaMod:AsFirstSight");
        Collections.shuffle(whiteSpaceCards, AbstractDungeon.cardRandomRng.random);
        AbstractCard whiteSpaceCard = CardLibrary.getCard(whiteSpaceCards.get(0)).makeCopy();
        addToBot(new PlayTempCardAction(whiteSpaceCard, abstractMonster, true));

        // 随机选择一张转调效果的牌
        List<String> toneCards = new ArrayList<>();
        toneCards.add("SelenaMod:DevelopmentSection");
        toneCards.add("SelenaMod:Minuet");
        toneCards.add("SelenaMod:Aria");
        toneCards.add("SelenaMod:Seeking");
        toneCards.add("SelenaMod:Lament");
        toneCards.add("SelenaMod:Questing");
        toneCards.add("SelenaMod:FaustHoliday");
        toneCards.add("SelenaMod:Fantasia");
        toneCards.add("SelenaMod:Unfinished");
        toneCards.add("SelenaMod:Dirge");
        Collections.shuffle(toneCards, AbstractDungeon.cardRandomRng.random);
        AbstractCard toneCard = CardLibrary.getCard(toneCards.get(0)).makeCopy();
        addToBot(new PlayTempCardAction(toneCard, abstractMonster, true));

        // 随机选择一张覆写效果的牌
        List<String> overrideCards = new ArrayList<>();
        overrideCards.add("SelenaMod:Finale");
        overrideCards.add("SelenaMod:ArcadiaRetreat");
        overrideCards.add("SelenaMod:DivineComedy");
        overrideCards.add("SelenaMod:Storm");
        overrideCards.add("SelenaMod:Rebirth");
        overrideCards.add("SelenaMod:PeonyPavilion");
        Collections.shuffle(overrideCards, AbstractDungeon.cardRandomRng.random);
        AbstractCard overrideCard = CardLibrary.getCard(overrideCards.get(0)).makeCopy();
        addToBot(new PlayTempCardAction(overrideCard, abstractMonster, true));
    }

}

package SelenaMod.interfaces;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface IPreUseCard {
    void onPreUseCard(AbstractCard card, AbstractMonster target);
}

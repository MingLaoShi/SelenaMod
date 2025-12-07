package SelenaMod.cards;

import SelenaMod.actions.HeroismAction;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Heroism extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("Heroism");

    public Heroism() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.NONE);
        this.isInnate=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription();

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new HeroismAction(this.upgraded));
    }
}

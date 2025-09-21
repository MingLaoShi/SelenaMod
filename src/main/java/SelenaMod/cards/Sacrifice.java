package SelenaMod.cards;

import SelenaMod.powers.SirenPower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sacrifice extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Sacrifice.class.getSimpleName());

    public Sacrifice() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.setBlock(4);
        this.setMagic(7);
        this.cardsToPreview = new OutOfControl();
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.hasPower(SirenPower.POWER_ID)) {
            int multi = 2;
            if (this.upgraded) {
                multi = 3;
            }
            this.block *= multi;
        }
        for (int i = 0; i < this.magicNumber; i++) {
            addCustomBlockAction();
        }
        addToBot(new MakeTempCardInDrawPileAction(new OutOfControl(), 1, true, true));

    }
}

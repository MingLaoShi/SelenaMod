package SelenaMod.cards;

import SelenaMod.cardEffects.DexterityEffect;
import SelenaMod.powers.TonePower;
import SelenaMod.utils.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Lament extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Lament.class.getSimpleName());

    public Lament() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.setDamage(2);
        this.setMagic(1);
        this.cardsToPreview = new OutOfControl();
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(1);
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i < 5; i++) {
            addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        }
        if (this.upgraded) {
            addToBot(new MakeTempCardInDiscardAction(new OutOfControl(), 1));
        } else {
            addToBot(new MakeTempCardInDrawPileAction(new OutOfControl(), 1, true, true));
        }
        addPowerToSelf(new TonePower(abstractPlayer, this.magicNumber, new DexterityEffect(this.cardID, this.magicNumber)));
    }
}

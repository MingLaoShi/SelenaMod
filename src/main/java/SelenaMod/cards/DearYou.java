package SelenaMod.cards;

import SelenaMod.effects.PlayTempMusicEffect;
import SelenaMod.powers.DearYouPower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.PlayMusicHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DearYou extends CustomSelenaCard {
    public static String ID = ModHelper.makeID("DearYou");

    public DearYou() {
        super(ID, 0, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        this.setMagic(3);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (isFirstInThisBattle()) {
            AbstractDungeon.effectList.add(new PlayTempMusicEffect(PlayMusicHelper.Music.DEAR_YOU, null));
        }
        addPowerToSelf(new DearYouPower(abstractPlayer, this.magicNumber));
    }

    private boolean isFirstInThisBattle() {
        return AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().noneMatch(c -> c.cardID.equals(ID));
    }
}

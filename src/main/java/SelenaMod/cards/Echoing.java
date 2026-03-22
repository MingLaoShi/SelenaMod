package SelenaMod.cards;

import SelenaMod.effects.PlayTempMusicEffect;
import SelenaMod.powers.EchoingPower;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.PlayMusicHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Echoing extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Echoing.class.getSimpleName());

    public Echoing() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setMagic(1);
    }

    @Override
    protected void upgradeMethod() {
        this.isInnate = true;
        this.upgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (isFirstInThisBattle()) {
            AbstractDungeon.effectList.add(new PlayTempMusicEffect(PlayMusicHelper.Music.ECHOING, null));
        }
        addPowerToSelf(new EchoingPower(abstractPlayer, this.magicNumber));
    }

    private boolean isFirstInThisBattle() {
        return AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().noneMatch(c -> c.cardID.equals(ID) && c != this);
    }
}

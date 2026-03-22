package SelenaMod.cards;

import SelenaMod.modifiers.RepeatModifier;
import SelenaMod.utils.ModHelper;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Confrontation extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Confrontation.class.getSimpleName());
    public static UIStrings TALK_STRINGS = CardCrawlGame.languagePack.getUIString(ModHelper.makeID("Talk"));

    public Confrontation() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.setDamage(10);
        CardModifierManager.addModifier(this, new RepeatModifier());
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(4);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (isFirstInThisBattle()) {
            ModHelper.AddTalkAction(TALK_STRINGS.TEXT[2]);
        }
        addCustomDamageAction(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) && p.hand.group.stream().noneMatch(c -> c != this && c.type == CardType.ATTACK);
    }

    private boolean isFirstInThisBattle() {
        return AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().noneMatch(c -> c.cardID.equals(ID) && c != this);
    }
}

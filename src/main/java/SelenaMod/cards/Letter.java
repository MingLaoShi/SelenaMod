package SelenaMod.cards;

import SelenaMod.actions.PutToDeckAction;
import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.TextureLoader;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Letter extends CustomSelenaCard {
    public static String ID = ModHelper.makeID(Letter.class.getSimpleName());
    public AbstractCardEffect overrideEffect = null;
    private static Texture REPLACE_IMG = TextureLoader.getTexture(ModHelper.makeImgPath("cards", "Letter2"));
    public float textureReplaceDuration = 1.0F;

    public Letter() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        this.setMagic(1);
    }

    public Letter(String id, int i, CardType cardType, CardRarity cardRarity, CardTarget cardTarget) {
        super(id, i, cardType, cardRarity, cardTarget);
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.overrideEffect == null) {
            addDrawCardAction();
            addToBot(new PutToDeckAction(abstractPlayer, abstractPlayer, this.magicNumber, false));
        } else {
            addToBot(this.overrideEffect.trigger(abstractMonster));
        }
    }

    public void setOverrideEffect(AbstractCardEffect overrideEffect) {
        this.target = ModHelper.adjustTarget(this.target, overrideEffect.data.getTarget());
        this.overrideEffect = overrideEffect;
        this.initializeDescription();
    }

    @Override
    public void initializeDescription() {
        if (this.overrideEffect != null) {
            this.rawDescription = this.overrideEffect.getDescription();
        }
        super.initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (this.overrideEffect != null) {
            this.overrideEffect.applyPowers();
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (this.overrideEffect != null) {
            this.overrideEffect.calcCardDamage(mo);
        }
    }

    @SpireOverride
    public void renderPortrait(SpriteBatch sb) {
        if (this.textureReplaceDuration >= 1.0F)
            SpireSuper.call(sb);
        else {
            Color color1 = ReflectionHacks.getPrivate(this, AbstractCard.class, "renderColor");
            float originA = color1.a;
            color1.a = this.textureReplaceDuration;
            SpireSuper.call(sb);
            Color color2 = Color.WHITE.cpy();
            color2.a = 1.0F - this.textureReplaceDuration;
            sb.setColor(color2);
            float drawX = this.current_x - 125.0F;
            float drawY = this.current_y - 95.0F;
            sb.draw(REPLACE_IMG, drawX, drawY + 72.0F,
                    125.0F, 23.0F,
                    250.0F, 190.0F,
                    this.drawScale * Settings.scale, this.drawScale * Settings.scale,
                    this.angle, 0, 0, 250, 190,
                    false, false);
            color1.a = originA;
        }

    }

    public static boolean isLetterCard(AbstractCard card){
        return card instanceof Letter;
    }
}

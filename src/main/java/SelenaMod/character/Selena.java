package SelenaMod.character;


import SelenaMod.cards.*;
import SelenaMod.core.SelenaMod;
import SelenaMod.relics.PaperAndPen;
import SelenaMod.utils.ModHelper;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

public class Selena extends CustomPlayer {
    private static final String SELENA_SHOULDER_1 = ModHelper.makeImgPath("char", "shoulder1");
    private static final String SELENA_SHOULDER_2 = ModHelper.makeImgPath("char", "shoulder2");
    private static final String SELENA_CORPSE = ModHelper.makeImgPath("char", "corpse");
    private static final String[] ORB_TEXTURES = new String[]{
            ModHelper.makeImgPath("ui/orb", "layer1"),
            ModHelper.makeImgPath("ui/orb", "layer2"),
            ModHelper.makeImgPath("ui/orb", "layer3"),
            ModHelper.makeImgPath("ui/orb", "layer4"),
            ModHelper.makeImgPath("ui/orb", "layer5"),
            ModHelper.makeImgPath("ui/orb", "layer6"),
            ModHelper.makeImgPath("ui/orb", "layer1d"),
            ModHelper.makeImgPath("ui/orb", "layer2d"),
            ModHelper.makeImgPath("ui/orb", "layer3d"),
            ModHelper.makeImgPath("ui/orb", "layer4d"),
            ModHelper.makeImgPath("ui/orb", "layer5d")
    };
    private static final String ORB_VFX = ModHelper.makeImgPath("ui/orb", "vfx");
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F,
            -5.0F, 0.0F};
    private static final String CHAR_IMG = ModHelper.makeImgPath("char", "Selena");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack
            .getCharacterString(ModHelper.makeID("Selena"));


    public Selena(String name) {
        super(name, ModHelper.getSelenaClass(), ORB_TEXTURES, ORB_VFX, LAYER_SPEED, null, null);
        this.initializeClass(CHAR_IMG, SELENA_SHOULDER_1, SELENA_SHOULDER_2, SELENA_CORPSE, getLoadout(),
                0.0F, 0.0F, 200.0F, 250.0F, new EnergyManager(3));

    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> deck = new ArrayList<>();
        //todo
        deck.add(Strike.ID);
        deck.add(Strike.ID);
        deck.add(Dodge.ID);
        deck.add(Dodge.ID);
        deck.add(Dodge.ID);
        deck.add(Dodge.ID);
        deck.add(Dodge.ID);
        deck.add(Overture.ID);
        deck.add(Letter.ID);
        deck.add(Letter.ID);
        deck.add(Letter.ID);
        deck.add(DevelopmentSection.ID);
        deck.add(Finale.ID);

        return deck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> relics = new ArrayList<>();
        //todo
        relics.add(PaperAndPen.ID);
        return relics;

    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(characterStrings.NAMES[0], characterStrings.TEXT[0], 75, 75,
                0, 99, 5, this,
                this.getStartingRelics(), this.getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return ModHelper.getSelenaColor();
    }

    @Override
    public Color getCardRenderColor() {
        return SelenaMod.SELENA_COLOR.cpy();
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        //todo
        return new Strike_Red();
    }

    @Override
    public Color getCardTrailColor() {
        return SelenaMod.SELENA_COLOR.cpy();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontPurple;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Selena(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return SelenaMod.SELENA_COLOR.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

}

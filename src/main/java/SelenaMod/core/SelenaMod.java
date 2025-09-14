package SelenaMod.core;

import SelenaMod.cards.CustomSelenaCard;
import SelenaMod.character.Selena;
import SelenaMod.relics.PaperAndPen;
import SelenaMod.utils.ModHelper;
import SelenaMod.utils.SaveHelper;
import SelenaMod.utils.ToneAndSpaceDataManager;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

import static com.megacrit.cardcrawl.core.Settings.language;

@SpireInitializer
public class SelenaMod implements ISubscriber, EditStringsSubscriber, EditKeywordsSubscriber, EditCharactersSubscriber,
        EditCardsSubscriber, EditRelicsSubscriber, PostInitializeSubscriber {

    public static final Color SELENA_COLOR = new Color(0.8f, 0.8f, 1.0f, 1.0f);
    public static final String SELENA_ATTACK_512 = ModHelper.makeImgPath("512", "bg_attack_512");
    public static final String SELENA_SKILL_512 = ModHelper.makeImgPath("512", "bg_skill_512");
    public static final String SELENA_POWER_512 = ModHelper.makeImgPath("512", "bg_power_512");
    public static final String SELENA_ATTACK_1024 = ModHelper.makeImgPath("1024", "bg_attack_1024");
    public static final String SELENA_SKILL_1024 = ModHelper.makeImgPath("1024", "bg_skill_1024");
    public static final String SELENA_POWER_1024 = ModHelper.makeImgPath("1024", "bg_power_1024");

    public static final String SELENA_SMALL_ORB = ModHelper.makeImgPath("512", "small_orb");
    public static final String SELENA_BIG_ORB = ModHelper.makeImgPath("1024", "card_orb");
    public static final String SELENA_ENERGY_ORB = ModHelper.makeImgPath("1024", "cost_orb");

    public static final String SELENA_CHARACTER_BUTTON = ModHelper.makeImgPath("char", "Character_Button");
    public static final String SELENA_CHARACTER_PORTRAIT = ModHelper.makeImgPath("char", "Character_Portrait");

    public static SaveHelper saveHelper;

    public SelenaMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(ModHelper.getSelenaColor(), SELENA_COLOR, SELENA_COLOR, SELENA_COLOR, SELENA_COLOR, SELENA_COLOR, SELENA_COLOR, SELENA_COLOR, SELENA_ATTACK_512, SELENA_SKILL_512, SELENA_POWER_512, SELENA_BIG_ORB, SELENA_ATTACK_1024, SELENA_SKILL_1024, SELENA_POWER_1024, SELENA_ENERGY_ORB, SELENA_SMALL_ORB);

        saveHelper = new SaveHelper();

    }


    public static void initialize() {
        new SelenaMod();
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(ModHelper.MOD_ID).packageFilter(CustomSelenaCard.class).setDefaultSeen(true).cards();

    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Selena(CardCrawlGame.playerName), SELENA_CHARACTER_BUTTON, SELENA_CHARACTER_PORTRAIT);
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ENG";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else if (language == Settings.GameLanguage.ENG) {
            //todo: make English translate
            lang = "ZHS";
        } else if (language == Settings.GameLanguage.ZHT) {
            lang = "ZHS";
        }
        String json = Gdx.files.internal(ModHelper.makeLocalizationPath(lang, "keyword")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(StringUtils.lowerCase(ModHelper.MOD_PRE), keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new PaperAndPen(), ModHelper.getSelenaColor());
    }

    @Override
    public void receiveEditStrings() {
        String lang = "ENG";
        if (com.megacrit.cardcrawl.core.Settings.language == com.megacrit.cardcrawl.core.Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else if (com.megacrit.cardcrawl.core.Settings.language == com.megacrit.cardcrawl.core.Settings.GameLanguage.ENG) {
            // todo：make English translate
            lang = "ZHS";
        } else if (com.megacrit.cardcrawl.core.Settings.language == com.megacrit.cardcrawl.core.Settings.GameLanguage.ZHT) {
            lang = "ZHS";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, ModHelper.makeLocalizationPath(lang, "card"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, ModHelper.makeLocalizationPath(lang, "character"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, ModHelper.makeLocalizationPath(lang, "power"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, ModHelper.makeLocalizationPath(lang, "relic"));
        BaseMod.loadCustomStringsFile(UIStrings.class, ModHelper.makeLocalizationPath(lang, "uistring"));
        BaseMod.loadCustomStringsFile(EventStrings.class, ModHelper.makeLocalizationPath(lang, "event"));
        BaseMod.loadCustomStringsFile(PotionStrings.class, ModHelper.makeLocalizationPath(lang, "potion"));

    }

    @Override
    public void receivePostInitialize() {
        saveHelper.values = new SaveHelper.SaveValue();
        ToneAndSpaceDataManager.registerAll();
    }
}

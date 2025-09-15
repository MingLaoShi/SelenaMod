package SelenaMod.utils;

import SelenaMod.cardEffects.AbstractCardEffect;
import SelenaMod.modifiers.ToneModifier;
import SelenaMod.modifiers.WhiteSpaceModifier;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class ModHelper {
    public static String MOD_ID = "SelenaMod";
    public static String MOD_PRE = "SelenaMod";
    public static String RESOURCES_FOLDER_PATH = "SelenaResources";
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String makeImgPath(String folder, String imgName) {
        return RESOURCES_FOLDER_PATH + "/img/" + folder + "/" + imgName + ".png";
    }

    public static String makeID(String id) {
        return MOD_PRE + ":" + id;
    }

    public static String makeCardImagePath(String ID) {
        String filename = ID.replace(MOD_PRE + ":", "");
        String filePath = ModHelper.makeImgPath("cards", filename);
        if (Gdx.files.internal(filePath).exists()) {
            return filePath;
        } else {
            return ModHelper.makeImgPath("cards", "default");
        }
    }

    public static String makePowerIconPath(String ID, boolean bigIcon) {
        String filename = ID.replace(MOD_PRE + ":", "");
        if (bigIcon) {
            filename += "84";
        } else {
            filename += "32";
        }
        String filePath = ModHelper.makeImgPath("power", filename);
        if (Gdx.files.internal(filePath).exists()) {
            return filePath;
        } else {
            if (bigIcon) {
                return ModHelper.makeImgPath("power", "default84");
            } else {
                return ModHelper.makeImgPath("power", "default32");
            }
        }
    }

    public static String makeRelicImagePath(String id) {
        String filename = id.replace(MOD_PRE + ":", "");
        String filePath = ModHelper.makeImgPath("relic", filename);
        if (Gdx.files.internal(filePath).exists()) {
            return filePath;
        } else {
            return ModHelper.makeImgPath("relic", "default");
        }
    }

    public static String makeRelicOutlinePath(String id) {
        String filename = id.replace(MOD_PRE + ":", "");
        String filePath = ModHelper.makeImgPath("relic", filename + "_p");
        if (Gdx.files.internal(filePath).exists()) {
            return filePath;
        } else {
            return ModHelper.makeImgPath("relic", "default");
        }
    }

    public static String makeLocalizationPath(String lang, String type) {
        return RESOURCES_FOLDER_PATH + "/localization/" + lang + "/" + type + ".json";
    }

    public static AbstractCard.CardColor getSelenaColor() {
        return SelenaEnums.SelenaColorEnum.Selena_CARD;
    }

    public static String makeFilePath(String folder, String fileName, String fileType) {
        return String.format("%s/%s/%s.%s", RESOURCES_FOLDER_PATH, folder, fileName, fileType);
    }

    public static void initPower(AbstractPower power) {
        power.name = CardCrawlGame.languagePack.getPowerStrings(power.ID).NAME;
        String IMG_84 = ModHelper.makePowerIconPath(power.ID, true);
        String IMG_32 = ModHelper.makePowerIconPath(power.ID, false);
        power.region128 = TextureLoader.getTextureRegion(IMG_84);
        power.region48 = TextureLoader.getTextureRegion(IMG_32);
        power.updateDescription();
    }

    public static AbstractPlayer.PlayerClass getSelenaClass() {
        return SelenaEnums.SelenaColorEnum.Selena;
    }


    public static void addToneModifier(AbstractCard card, AbstractCardEffect effect) {
        ArrayList<AbstractCardModifier> modifier = CardModifierManager.getModifiers(card, ToneModifier.ID);
        if (modifier.isEmpty()) {
            ToneModifier toneModifier = new ToneModifier();
            toneModifier.addTone(effect);
            CardModifierManager.addModifier(card, toneModifier);
            card.initializeDescription();
        } else {
            ToneModifier toneModifier = (ToneModifier) modifier.get(0);
            toneModifier.addTone(effect);
            card.initializeDescription();
        }
    }

    public static void addWhiteSpaceModifier(AbstractCard card, AbstractCardEffect effect) {
        ArrayList<AbstractCardModifier> modifier = CardModifierManager.getModifiers(card, WhiteSpaceModifier.ID);
        if (modifier.isEmpty()) {
            WhiteSpaceModifier whiteSpaceModifier = new WhiteSpaceModifier();
            whiteSpaceModifier.addWhiteSpace(effect);
            CardModifierManager.addModifier(card, whiteSpaceModifier);
            card.initializeDescription();
        } else {
            WhiteSpaceModifier whiteSpacePower = (WhiteSpaceModifier) modifier.get(0);
            whiteSpacePower.addWhiteSpace(effect);
            card.initializeDescription();
        }
    }

    public static void addOverrideModifier(AbstractCard card, ToneAndSpaceData data) {
//        ArrayList<AbstractCardModifier> modifier= CardModifierManager.getModifiers(card, WhiteSpaceModifier.ID);
//        if(modifier.isEmpty()){
//            WhiteSpaceModifier whiteSpaceModifier=new WhiteSpaceModifier();
//            whiteSpaceModifier.addWhiteSpace(data);
//            CardModifierManager.addModifier(card,whiteSpaceModifier);
//            card.initializeDescription();
//        }else{
//            WhiteSpaceModifier whiteSpacePower= (WhiteSpaceModifier) modifier.get(0);
//            whiteSpacePower.addWhiteSpace(data);
//            card.initializeDescription();
//        }
    }

    public static AbstractCard.CardTarget adjustTarget(AbstractCard.CardTarget originTarget, AbstractCard.CardTarget newTarget) {
        if (originTarget == AbstractCard.CardTarget.NONE) {
            return newTarget;
        }
        if (originTarget == AbstractCard.CardTarget.ALL) {
            if (newTarget == AbstractCard.CardTarget.ENEMY) {
                return newTarget;
            }
        }
        if (originTarget == AbstractCard.CardTarget.ENEMY) {
            if (newTarget == AbstractCard.CardTarget.SELF) {
                return AbstractCard.CardTarget.SELF_AND_ENEMY;
            }
        }
        if (originTarget == AbstractCard.CardTarget.SELF) {
            if (newTarget == AbstractCard.CardTarget.ENEMY) {
                return AbstractCard.CardTarget.SELF_AND_ENEMY;
            } else if (newTarget == AbstractCard.CardTarget.ALL_ENEMY) {
                return AbstractCard.CardTarget.ALL;
            }
        }
        if (originTarget == AbstractCard.CardTarget.ALL_ENEMY) {
            if (newTarget == AbstractCard.CardTarget.SELF) {
                return AbstractCard.CardTarget.ALL;
            } else if (newTarget == AbstractCard.CardTarget.ENEMY) {
                return AbstractCard.CardTarget.ENEMY;
            }
        }
        return originTarget;
    }
}

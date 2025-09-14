package SelenaMod.utils;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class SelenaEnums {
    public static class SelenaColorEnum {
        @SpireEnum
        public static AbstractPlayer.PlayerClass Selena;
        @SpireEnum(name = "Selena_CARD")
        public static AbstractCard.CardColor Selena_CARD;
        @SpireEnum(name = "Selena_CARD")
        public static CardLibrary.LibraryType Selena_LIBRARY;
    }
}

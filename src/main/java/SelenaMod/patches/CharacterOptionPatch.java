package SelenaMod.patches;

import SelenaMod.character.Selena;
import SelenaMod.utils.PlayMusicHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;

import java.util.Random;

public class CharacterOptionPatch {
    @SpirePatch(clz = CharacterOption.class, method = "updateHitbox")
    public static class updateHitboxPatch {
        public static int PressCount = 0;
        public static boolean FindEasterEgg = false;
        public static Random random = new Random();

        @SpireInsertPatch(rloc = 35)
        public static void updateHitboxPatch(CharacterOption _instance) {
            if (_instance.c instanceof Selena) {
                CardCrawlGame.music.fadeOutBGM();
                CardCrawlGame.music.changeBGM(PlayMusicHelper.Music.DREAMING_TO_THE_GLOWING_PLACE.getFilename());

            } else {
                CardCrawlGame.music.fadeOutBGM();
                CardCrawlGame.music.changeBGM("MENU");
            }
//            System.out.println("pressCount:"+PressCount);
        }
    }
}

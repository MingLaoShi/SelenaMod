package SelenaMod.patches;

import SelenaMod.utils.PlayMusicHelper;
import SelenaMod.utils.SelenaEnums;
import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.RestRoom;

public class MainMusicPatch {
    @SpirePatch(clz = MainMusic.class, method = "getSong")
    public static class getSongPatch {
        @SpirePrefixPatch
        public static SpireReturn<Music> getSongPatch(MainMusic _instance, String key) {
            return getMusicSpireReturn(key);
        }
    }

    @SpirePatch(clz = TempMusic.class, method = "getSong")
    public static class getSongTempPatch {
        @SpirePrefixPatch
        public static SpireReturn<Music> prefixPatch(TempMusic _instance, String key) {
            return getMusicSpireReturn(key);
        }


    }

    private static SpireReturn<Music> getMusicSpireReturn(String key) {
        if (PlayMusicHelper.contains(key)) {
            return SpireReturn.Return(MainMusic.newMusic(key));
        }
        return SpireReturn.Continue();
    }

    @SpirePatch(clz = RestRoom.class, method = "onPlayerEntry")
    public static class RestRoomPatch {
        @SpirePostfixPatch
        public static void prefix(RestRoom __instance) {
            if (AbstractDungeon.player.chosenClass == SelenaEnums.SelenaColorEnum.Selena) {
                __instance.fireSoundId = CardCrawlGame.sound.playAndLoop(PlayMusicHelper.Music.CHROMATIC_AMNESIA.name());
            }
        }
    }

//    @SpirePatch(clz = MonsterRoomBoss.class,method = "onPlayerEntry")
//    public static class MonsterRoomBossPatch{
//        @SpirePostfixPatch
//        public static void prefix(MonsterRoomBoss __instance){
//            if(AbstractDungeon.player.chosenClass== SelenaEnums.SelenaColorEnum.Selena){
//                CardCrawlGame.sound.playAndLoop(PlayMusicHelper.Music.CHIVAL.name());
//            }
//        }
//    }
}

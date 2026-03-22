package SelenaMod.utils;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.MusicMaster;
import com.megacrit.cardcrawl.audio.TempMusic;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayMusicHelper {
    private static String MP3 = "mp3";

    public static boolean contains(String key) {
        if (ALL_MUSIC_PATHS.isEmpty()) {
            for (Music music : Music.values()) {
                ALL_MUSIC_PATHS.add(music.getFilename());
            }
        }
        return ALL_MUSIC_PATHS.contains(key);
    }

    public enum Music {
        MUSIC_52HZ(ModHelper.makeMusicFilePatch("52Hz", MP3)),
        BURNING_VOW(ModHelper.makeMusicFilePatch("BurningVow", MP3)),
        CHIVAL(ModHelper.makeMusicFilePatch("Chival", MP3)),
        CHROMATIC_AMNESIA(ModHelper.makeMusicFilePatch("ChromaticAmnesia", MP3)),
        DEAR_YOU(ModHelper.makeMusicFilePatch("DearYou", MP3)),
        DREAMING_TO_THE_GLOWING_PLACE(ModHelper.makeMusicFilePatch("DreamingtotheGlowingPlace", MP3)),
        ECHOING(ModHelper.makeMusicFilePatch("ECHOING", MP3)),
        OVER_THE_GLEN(ModHelper.makeMusicFilePatch("OvertheGlen", MP3)),
        SIE_LIEBTEN_SICH_BEIDE(ModHelper.makeMusicFilePatch("Sieliebtensichbeide", MP3)),
        TEARLESS_NIGHTS(ModHelper.makeMusicFilePatch("TEARLESSNIGHTS", MP3)),
        CONFESSION(ModHelper.makeMusicFilePatch("Confession", MP3)),
        CIRCULATION(ModHelper.makeMusicFilePatch("Circulation", MP3)),
        HEROISM(ModHelper.makeMusicFilePatch("Heroism", MP3));

        private final String filename;

        private Music(String filename) {
            this.filename = filename;
        }

        public String getFilename() {
            return filename;
        }
    }

    public static final List<String> ALL_MUSIC_PATHS = new ArrayList<>();


    private static String Last_Music = "";
    private static String LAST_TEMP_MUSIC = "";
    public static void PlayMusic(Music music) {
        ArrayList<MainMusic> mainTrack = ReflectionHacks.getPrivate(CardCrawlGame.music, MusicMaster.class, "mainTrack");
        if (!mainTrack.isEmpty()) {
            Last_Music = mainTrack.get(0).key;
        }
        CardCrawlGame.music.silenceTempBgmInstantly();
        AbstractDungeon.getCurrRoom().playBgmInstantly(music.filename);
    }

    public static void PlayLastMusic() {
        if (StringUtils.isBlank(Last_Music)) {
            return;
        }
        AbstractDungeon.getCurrRoom().playBgmInstantly(Last_Music);
        Last_Music = "";
    }


    public static void PlayTempMusic(Music music, boolean loop) {
        ArrayList<TempMusic> tempTrack = ReflectionHacks.getPrivate(CardCrawlGame.music, MusicMaster.class, "tempTrack");
        if (!tempTrack.isEmpty()) {
            LAST_TEMP_MUSIC = tempTrack.get(0).key;
        }
        CardCrawlGame.music.playTempBgmInstantly(music.filename, loop);
    }

    public static void PlayLastTempMusic() {
        if (StringUtils.isBlank(LAST_TEMP_MUSIC)) {
            CardCrawlGame.music.fadeOutTempBGM();
            return;
        }
        CardCrawlGame.music.playTempBgmInstantly(LAST_TEMP_MUSIC, true);
        LAST_TEMP_MUSIC = "";
    }

}

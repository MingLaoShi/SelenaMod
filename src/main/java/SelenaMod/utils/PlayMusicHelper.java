package SelenaMod.utils;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayMusicHelper {
    private static String MP3 = "mp3";

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
        CONFESSION(ModHelper.makeMusicFilePatch("告白", MP3)),
        CIRCULATION(ModHelper.makeMusicFilePatch("环流.", MP3)),
        HEROISM(ModHelper.makeMusicFilePatch("英雄主义", MP3));

        private final String filename;

        private Music(String filename) {
            this.filename = filename;
        }

        public String getFilename() {
            return filename;
        }
    }

    public static final List<String> ALL_MUSIC_PATHS;

    static {
        List<String> paths = new ArrayList<>();
        for (Music music : Music.values()) {
            paths.add(music.getFilename());
        }
        ALL_MUSIC_PATHS = Collections.unmodifiableList(paths);
    }

    public static void PlayMusic(Music music) {
        AbstractDungeon.getCurrRoom().playBgmInstantly(music.filename);
    }


    public static void PlayTempMusic(Music music, boolean loop) {
        CardCrawlGame.music.playTempBgmInstantly(music.filename, loop);
    }

}

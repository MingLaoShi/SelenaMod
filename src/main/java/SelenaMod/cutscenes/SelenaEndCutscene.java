package SelenaMod.cutscenes;

import SelenaMod.utils.ModHelper;
import SelenaMod.utils.SelenaEnums;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.Cutscene;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.VictoryScreen;

public class SelenaEndCutscene extends Cutscene {
    private VideoPlayer videoPlayer;
    private boolean videoStarted = false;
    private boolean videoEnded = false;
    private float playTime = 0f;
    private float lastRenderTime = 0f;
    private int renderFrameCount = 0;

    private static final String VIDEO_PATH = ModHelper.RESOURCES_FOLDER_PATH + "/music/end.webm";
    private static final float MIN_PLAY_TIME = 1.0f;

    public SelenaEndCutscene() {
        super(SelenaEnums.SelenaColorEnum.Selena);

        ModHelper.logger.info("=== SelenaEndCutscene constructor ===");
        ModHelper.logger.info("Video path: {}", VIDEO_PATH);

        if (Gdx.files.internal(VIDEO_PATH).exists()) {
            ModHelper.logger.info("Video file exists in jar");
        } else {
            ModHelper.logger.error("Video file NOT found: {}", VIDEO_PATH);
            videoEnded = true;
        }
    }

    @Override
    public void update() {
        // 如果视频已结束，不再处理任何逻辑
        if (videoEnded) {
            endCutscene();
            return;
        }

        // 更新播放时间
        if (videoStarted && videoPlayer != null) {
            playTime += Gdx.graphics.getDeltaTime();
        }

        // 开始播放视频

        if (!videoStarted) {
            videoStarted = true;
            GameCursor.hidden = true;

            ModHelper.logger.info("Starting video playback");
            try {
                videoPlayer = VideoPlayerCreator.createVideoPlayer();
                videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
                    @Override
                    public void onCompletionListener(com.badlogic.gdx.files.FileHandle file) {
                        ModHelper.logger.info("Video playback ended naturally (completion listener)");
                        if (!videoEnded) {
                            videoEnded = true;
                            // 清理视频播放器
                            if (videoPlayer != null) {
                                videoPlayer.stop();
                                videoPlayer.dispose();
                                videoPlayer = null;
                            }
                            endCutscene();
                        }
                    }
                });
                videoPlayer.play(Gdx.files.internal(VIDEO_PATH));
            } catch (Exception e) {
                ModHelper.logger.error("Failed to start video playback", e);
                videoEnded = true;
                endCutscene();
            }
            return;
        }

        // 更新视频帧
        if (videoPlayer != null && !videoEnded) {
            // 更新视频
            if (videoPlayer.isBuffered()) {
                videoPlayer.update();
            }

            // 检测视频是否播放结束
            if (!videoPlayer.isPlaying()) {
                ModHelper.logger.info("Video playback ended (isPlaying = false)");
                videoEnded = true;
                videoPlayer.stop();
                videoPlayer.dispose();
                videoPlayer = null;
                endCutscene();
                return;
            }

            // 检测点击跳过（只有播放超过1秒才允许）
            if (playTime >= MIN_PLAY_TIME) {
                boolean skipPressed = InputHelper.justClickedLeft;
                if (Settings.isControllerMode && CInputActionSet.select != null) {
                    skipPressed = skipPressed || CInputActionSet.select.isJustPressed();
                }
                if (skipPressed) {
                    ModHelper.logger.info("=== Video skipped by user after {}s ===", playTime);
                    skipVideo();
                    return;
                }
            }
        }
    }

    private void renderVideoFrame(SpriteBatch sb) {
        renderFrameCount++;

        // 检查状态，使用局部变量避免多线程竞争
        if (videoEnded || !videoStarted) {
            return;
        }

        VideoPlayer vp = videoPlayer;
        if (vp == null) {
            return;
        }

        // 每帧都调用 update 确保视频解码
        vp.update();

        Texture frame = vp.getTexture();
        if (frame != null) {
            sb.setColor(Color.WHITE);
            sb.draw(frame, 0, 0, Settings.WIDTH, Settings.HEIGHT);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        // 先绘制黑色背景覆盖整个屏幕（覆盖父类的内容）
        sb.setColor(Color.BLACK);
        sb.draw(com.megacrit.cardcrawl.helpers.ImageMaster.WHITE_SQUARE_IMG, 0, 0, Settings.WIDTH, Settings.HEIGHT);

        // 绘制视频帧
        renderVideoFrame(sb);
    }

    @Override
    public void renderAbove(SpriteBatch sb) {
        // 再次绘制视频帧，覆盖父类 renderAbove 中渲染的 bgImg 和 panels
        renderVideoFrame(sb);
    }

    private void skipVideo() {
        videoEnded = true;
        CardCrawlGame.sound.play("SELECT");

        if (videoPlayer != null) {
            videoPlayer.stop();
            videoPlayer.dispose();
            videoPlayer = null;
        }

        endCutscene();
    }

    private void endCutscene() {
        ModHelper.logger.info("=== endCutscene called ===");

        GameCursor.hidden = false;
        AbstractDungeon.victoryScreen = new VictoryScreen(null);
    }

    @Override
    public void dispose() {
        ModHelper.logger.info("=== dispose called ===");

        if (videoPlayer != null) {
            videoPlayer.stop();
            videoPlayer.dispose();
            videoPlayer = null;
        }

        super.dispose();
    }
}

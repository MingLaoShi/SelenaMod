package SelenaMod.effects;

import SelenaMod.utils.ModHelper;
import SelenaMod.utils.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SpotEffect extends AbstractGameEffect {
    private static ShapeRenderer shapeRenderer = new ShapeRenderer();
    private static Texture Mask = TextureLoader.getTexture(ModHelper.makeImgPath("utils", "mask"));
    private AbstractPlayer p;
    private AbstractMonster m;
    private FrameBuffer buffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
    private TextureRegion spotImage = null;
    private Color color;

    public SpotEffect(AbstractPlayer p, AbstractMonster m) {
        this.p = p;
        this.m = m;
        this.duration = 2.0F;
        this.color = Color.WHITE.cpy();
        this.color.a = 0.0F;
    }

    private void makeSpotImage(SpriteBatch spriteBatch) {
        spriteBatch.end();
        buffer.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1.0F);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shapeRenderer.end();
        spriteBatch.begin();
        int src = spriteBatch.getBlendSrcFunc();
        int dst = spriteBatch.getBlendDstFunc();
        spriteBatch.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_COLOR);
        spriteBatch.setColor(1, 1, 1, 1);
        drawMask(spriteBatch, this.p);
        drawMask(spriteBatch, this.m);

        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDisable(GL20.GL_BLEND);
        spriteBatch.setBlendFunction(src, dst);
        buffer.end();
        spriteBatch.end();

        this.spotImage = new TextureRegion(buffer.getColorBufferTexture());
        this.spotImage.flip(false, true);
        spriteBatch.begin();
    }

    private void drawMask(SpriteBatch spriteBatch, AbstractCreature target) {
        spriteBatch.draw(
                Mask,
                target.hb.cX - (target.hb.width),      // x 起点 = 锚点左移一半宽度
                target.hb.y - 50.0F,                           // y 起点 = 正好脚下
                target.hb.width,                       // 旋转中心 X（相对贴图左下角）
                0f,                                    // 旋转中心 Y（脚底）
                target.hb.width * 2f,                  // 绘制宽度
                Gdx.graphics.getHeight() + 50.0F,              // 绘制高度（你原逻辑保留）
                1.0f,                                  // scaleX
                1.0f,                                  // scaleY
                0.0f,                                  // rotation
                0, 0,                                  // srcX, srcY
                Mask.getWidth(),                       // srcWidth
                Mask.getHeight(),                      // srcHeight
                false, false                           // flipX, flipY
        );
    }

    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        // 总时长
        float total = 2.0f;
        float t = total - this.duration; // 已经过的时间

        if (t < 0.8f) {
            // 0 ~ 0.8s : 线性上升
            this.color.a = t / 0.8f;
        } else if (t < 1.3f) {
            // 0.8s ~ 1.3s : 保持1
            this.color.a = 1.0f;
        } else if (t < 2.0f) {
            // 1.3s ~ 2.0s : 线性下降
            this.color.a = 1.0f - (t - 1.3f) / (2.0f - 1.3f);
        } else {
            this.color.a = 0.0f;
        }

//        this.color.a=1.0F;

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
//        if(Objects.isNull(this.spotImage))
        this.makeSpotImage(spriteBatch);

        spriteBatch.setColor(this.color);
        spriteBatch.draw(this.spotImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {

    }
}

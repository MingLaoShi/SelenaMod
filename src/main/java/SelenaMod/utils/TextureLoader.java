package SelenaMod.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.GdxRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class TextureLoader {
    public static final Logger logger = LogManager.getLogger(TextureLoader.class.getName());
    private static HashMap<String, Texture> textrues = new HashMap<>();
    private static HashMap<String, TextureAtlas.AtlasRegion> textureRegions = new HashMap<>();

    public static Texture getTexture(String id) {
        if (textrues.get(id) == null) {
            try {
                loadTexture(id);
            } catch (GdxRuntimeException e) {
                logger.error("Could not find Texture " + id);
                return getTexture(ModHelper.makeImgPath("utils", "default"));
            }
        }
        return textrues.get(id);
    }

    private static void loadTexture(String id) throws GdxRuntimeException {
        logger.info(ModHelper.MOD_ID + ":LoadTexture" + id);
        Texture texture = new Texture(id);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textrues.put(id, texture);
    }

    public static TextureAtlas.AtlasRegion getTextureRegion(String id) {
        if (textureRegions.get(id) == null) {
            Texture texture = getTexture(id);
            textureRegions.put(id, new TextureAtlas.AtlasRegion(getTexture(id), 0, 0, texture.getWidth(), texture.getHeight()));
        }
        return textureRegions.get(id);
    }
}

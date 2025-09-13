//import EnderLiliesMod.utils.ModHelper;
//import com.google.gson.*;
//import com.megacrit.cardcrawl.localization.PowerStrings;
//import org.junit.jupiter.api.Test;
//import org.junit.platform.commons.util.StringUtils;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class MakePowerJsonTest {
//    Gson gson=new GsonBuilder().setPrettyPrinting().create();
//    @Test
//    public void MakePowersJsonTest(){
//        MakePowersJson();
//    }
//
//    public void MakePowersJson(){
//        String cardsJsonPath="src/main/resources/EnderLiliesResources/localization/ZHS/power.json";
//        String keywordId="Lily:";
//        PowerStrings strings=new PowerStrings();
//        //修改这部分来增加或修改cardjson的内容
//        String ID= ModHelper.makeID("BeResponsiblePower");
//        strings.NAME="肩负的责任";
//        strings.DESCRIPTIONS= new String[]{"守护者西丽德在场时，回合开始获得%d [E] 抽%d张牌。"};
//
//        if(strings.DESCRIPTIONS!=null){
//            for(int i=0;i<strings.DESCRIPTIONS.length;i++){
//                strings.DESCRIPTIONS[i]=replaceKeywordId(strings.DESCRIPTIONS[i],keywordId);
//            }
//        }
//
//        if(StringUtils.isNotBlank(ID)){
//            JsonObject powerInfoObject=null;
//            Path path = Paths.get(cardsJsonPath);
//            try(InputStreamReader reader=new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8)){
//                JsonElement cardsInfoElement= JsonParser.parseReader(reader);
//                powerInfoObject=cardsInfoElement.getAsJsonObject();
//            }catch (IOException e){
//                System.out.println("读取Json失败！");
//            }
//
//            JsonElement rewriteElement= JsonParser.parseString(gson.toJson(strings));
//            assert powerInfoObject != null;
//            powerInfoObject.add(ID,rewriteElement);
//            System.out.println(rewriteElement);
//
//            try(OutputStreamWriter writer=new OutputStreamWriter(Files.newOutputStream(path), StandardCharsets.UTF_8)){
//                writer.write(gson.toJson(powerInfoObject));
//            }catch (IOException e){
//                System.out.println("写入Json失败！");
//            }
//        }
//
//    }
//
//    private String replaceKeywordId(String s,String keywordId){
//        if(StringUtils.isNotBlank(s)){
//            return s.replace("#",keywordId);
//        }
//        return s;
//    }
//}

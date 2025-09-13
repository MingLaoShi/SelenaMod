//import EnderLiliesMod.utils.ModHelper;
//import com.google.gson.*;
//import com.megacrit.cardcrawl.localization.CardStrings;
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
//class MakeCardsJsonTest {
//    Gson gson=new GsonBuilder().setPrettyPrinting().create();
//    @Test
//    public void MakeCardsJsonTest(){
//        MakeCardsJson();
//    }
//
//    public void MakeCardsJson(){
//        String cardsJsonPath="src/main/resources/EnderLiliesResources/localization/ZHS/card.json";
//        String keywordId="Lily:";
//        CardStrings strings=new CardStrings();
//        //修改这部分来增加或修改cardjson的内容
//        String ID= ModHelper.makeID("Dirty");
//        strings.NAME="污浊";
//        strings.DESCRIPTION=" 保留 。 NL 回合结束时受到 !M! 点伤害。被弃置时 消耗 。 NL 消耗 。";
//        strings.UPGRADE_DESCRIPTION=null;
//        strings.EXTENDED_DESCRIPTION=null;
//
//        strings.DESCRIPTION=replaceKeywordId(strings.DESCRIPTION,keywordId);
//        strings.UPGRADE_DESCRIPTION=replaceKeywordId(strings.UPGRADE_DESCRIPTION,keywordId);
//        if(strings.EXTENDED_DESCRIPTION!=null){
//            for(int i=0;i<strings.EXTENDED_DESCRIPTION.length;i++){
//                strings.EXTENDED_DESCRIPTION[i]=replaceKeywordId(strings.EXTENDED_DESCRIPTION[i],keywordId);
//            }
//        }
//
//        if(StringUtils.isNotBlank(ID)){
//            JsonObject cardsInfoObject=null;
//            Path path = Paths.get(cardsJsonPath);
//            try(InputStreamReader reader=new InputStreamReader(Files.newInputStream(path),StandardCharsets.UTF_8)){
//                JsonElement cardsInfoElement= JsonParser.parseReader(reader);
//                cardsInfoObject=cardsInfoElement.getAsJsonObject();
//            }catch (IOException e){
//                System.out.println("读取Json失败！");
//            }
//
//            JsonElement rewriteElement= JsonParser.parseString(gson.toJson(strings));
//            assert cardsInfoObject != null;
//            cardsInfoObject.add(ID,rewriteElement);
//            System.out.println(rewriteElement);
//
//            try(OutputStreamWriter writer=new OutputStreamWriter(Files.newOutputStream(path), StandardCharsets.UTF_8)){
//                writer.write(gson.toJson(cardsInfoObject));
//            }catch (IOException e){
//                System.out.println("写入Json失败！");
//            }
//        }
//
//    }
//
//    private String replaceKeywordId(String s,String keywordId){
//        if(StringUtils.isNotBlank(s)){
//            s= s.replace("#",keywordId);
//            s=s.replace("!m2!","!MichuruMod:NinjaToolCountVar!");
//        }
//        return s;
//    }
//}
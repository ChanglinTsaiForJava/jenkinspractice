package tw.com.ispan.service;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PokeApiServiceTests {
    @Autowired
    private PokeApiService pokeApiService;

    @Test
    // 测试Pokemon方法
    public void testPokemon() {
        // 调用pokeApiService的pokemon方法，传入参数1，获取返回的json字符串
        String json = pokeApiService.pokemon(1);
        // 将json字符串转换为JSONObject对象
        JSONObject obj = new JSONObject(json);
        // 从JSONObject对象中获取forms数组中的第一个JSONObject对象，并获取name属性的值
        String name = obj.getJSONArray("forms").getJSONObject(0).getString("name");
        // 打印name属性的值
        System.out.println("name="+name);
    }
}

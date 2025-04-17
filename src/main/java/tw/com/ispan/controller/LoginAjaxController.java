package tw.com.ispan.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.com.ispan.domain.CustomerBean;
import tw.com.ispan.service.CustomerService;

@RestController
public class LoginAjaxController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/ajax/secure/login")
    public String login(@RequestBody String body, HttpSession session) {
        System.out.println("SessionId="+session.getId());

        JSONObject responseBody = new JSONObject();

//接收資料
        JSONObject obj = new JSONObject(body);
        String username = obj.isNull("username") ? null : obj.getString("username");
        String password = obj.isNull("password") ? null : obj.getString("password");

//驗證資料
        if(username == null || username.length()==0 || password == null || password.length()==0) {
            responseBody.put("success", false);
            responseBody.put("message", "請輸入帳號或密碼");
            return responseBody.toString();
        }

//呼叫企業邏輯
        CustomerBean bean = customerService.login(username, password);

//回傳執行結果
        if(bean == null) {
            responseBody.put("success", false);
            responseBody.put("message", "登入失敗");

        } else {
            responseBody.put("success", true);
            responseBody.put("message", "登入成功");
            session.setAttribute("user", bean);

        }
        return responseBody.toString();
    }
}

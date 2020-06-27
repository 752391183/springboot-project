package com.example.springbootproject.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class EncryptComponent {
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${my.secretkey}")
    private String secretkey;
    @Value("${my.salt}")
    private String salt;
    @Autowired
    private TextEncryptor textEncryptor;

    /**
     * 通过单例模式来创建一个文本加密器，当成容器直接注入,避免反复创建
     * @return
     */
    @Bean
    public TextEncryptor getTextEncryptor() {
        return Encryptors.text(secretkey,salt);
    }

    /**
     * 对Token进行序列化和加密，无法进行加密时抛出客户端错误异常处理
     * @param myToken
     * @return
     */
    public String encryptToken(MyToken myToken) {
        try {
            String json = objectMapper.writeValueAsString(myToken);
            return textEncryptor.encrypt(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"服务器端错误");
        }
    }

    /**
     * 无法验证/解密/反序列化，说明数据被篡改，判断为无权限
     * @param json
     * @return
     */
    public MyToken decryptToken(String json) {
        try {
            String rejson = textEncryptor.decrypt(json);
            return objectMapper.readValue(rejson,MyToken.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"无权限");
        }
    }
}

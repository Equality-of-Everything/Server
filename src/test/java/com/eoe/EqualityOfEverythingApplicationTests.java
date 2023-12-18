package com.eoe;

import com.eoe.mapper.UserLoginMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;

@SpringBootTest
class EqualityOfEverythingApplicationTests {

    @Autowired
    private UserLoginMapper userLoginMapper;


    @Test
    void contextLoads() {
        System.out.println(userLoginMapper.selectList(null));
    }

    @Test
    void byteArrayTest() {
        String obj  = "abc";
        byte b[] = obj.getBytes();
        ByteArrayInputStream obj1 = new ByteArrayInputStream(b);
        for (int i = 0; i < 2; ++ i) {
            int c;
            while ((c = obj1.read()) != -1) {
                if (i == 0) {
                    System.out.print(Character.toUpperCase((char)c));
                }
            }
        }
    }

}

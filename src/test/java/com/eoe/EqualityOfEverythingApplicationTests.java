package com.eoe;

import com.eoe.mapper.UserLoginMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EqualityOfEverythingApplicationTests {

    @Autowired
    private UserLoginMapper userLoginMapper;


    @Test
    void contextLoads() {
        System.out.println(userLoginMapper.selectList(null));
    }

}

package com.nnc.myblog;

import com.nnc.myblog.entity.User;
import com.nnc.myblog.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //通过条件构造器查询一个list集合
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
        System.out.println(1);

    }


}

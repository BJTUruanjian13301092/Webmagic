package com.example.webmagic;

import com.example.webmagic.data.mapper.InformationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebmagicApplication.class)
public class MapperTest {

    @Autowired
    InformationMapper informationMapper;

//    @Test
//    public void informationMapperTest(){
//        informationMapper.getAllInfo();
//    }
}

package com.example.webmagic.data.mapper;

import com.example.webmagic.data.entity.InformationEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InformationMapper {

    @Select("select * from info_test")
    List<InformationEntity> getAllInfo();

    @Insert("insert into info_test(title, info) values(#{title}, #{info})")
    void insertInfo(@Param("title") String title,
                    @Param("info") String info);
}

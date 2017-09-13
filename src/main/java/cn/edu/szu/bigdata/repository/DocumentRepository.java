package cn.edu.szu.bigdata.repository;

import cn.edu.szu.bigdata.entity.SegmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by longhao on 2017/8/30.
 * Email: longhao1@email.szu.edu.cn
 */
@Repository
@Mapper
public interface DocumentRepository {

    @Select("select * from huanping_segment_info order by id")
    List<SegmentEntity> getSegmentEntitys();

    @Select({
            "select * from huanping_segment_info where segment_name=#{name}"
    })
    SegmentEntity getSegmentEntityByName(@Param(value = "name") String name);
}

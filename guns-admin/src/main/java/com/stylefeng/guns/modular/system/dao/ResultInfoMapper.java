package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.ResultInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 槽控结果展示表 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
public interface ResultInfoMapper extends BaseMapper<ResultInfo> {

    List<Map<String,Object>> selectCategoryCount(@Param("startTime") String startTime,@Param("endTime")  String endTime,@Param("listPotno") List<String> listPotno);
    //    按车间分布 槽状态查询
    List<Map<String,Object>> selectWorkshopCount(@Param("startTime") String startTime,@Param("endTime")  String endTime);
}

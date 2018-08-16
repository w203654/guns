package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Warehouse;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产铝量盘存台帐 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-15
 */
public interface WarehouseMapper extends BaseMapper<Warehouse> {
    //查询 单槽管理复盘控制面版
    List<Map<String,Object>> selectSingleTable(@Param("newDate") String newDate,@Param("potNo") String potNo);
    //查询 单槽管理复盘控制面版 雷达图
    List<Map<String,Object>> selectSingleRadar(@Param("newDate") String newDate,@Param("potNo") String potNo);
    //查询 效率参数分析
    List<Map<String,Object>> parameterUnalysisPotnoList(@Param("newDate") String newDate, @Param("workCode")String workCode, @Param("startInt")int startInt, @Param("endInt")int endInt);
    //查询 效率参数分析 分项
    List<Map<String,Object>> parameterUnalysisPotnoAvage(@Param("newDate") String newDate, @Param("workCode")String workCode, @Param("startInt")int startInt, @Param("endInt")int endInt);
    //查询 单槽指示出铝量
    List<Map<String,Object>> singlePotnoData(@Param("newDate") String newDate,@Param("potNo") String potNo);
    //查询 单槽指示高效率7台
    List<Map<String,Object>> singlePotnoHeight(@Param("newDate") String newDate);
    //查询 单槽指示低效率7台
    List<Map<String,Object>> singlePotnoLow(@Param("newDate") String newDate);
    //查询 电解温度 散点图
    List<String> scatterDJWD(@Param("newDate") String newDate, @Param("workCode")String workCode);
    //查询 电解分子比 散点图
    List<String> scatterFZB(@Param("newDate") String newDate, @Param("workCode")String workCode);
    //查询 电解设定电压 散点图
    List<String> scatterSETV(@Param("newDate") String newDate, @Param("workCode")String workCode);



}

package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.BaseInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 槽控基础信息表 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
public interface BaseInfoMapper extends BaseMapper<BaseInfo> {
    //    读取槽控机信息
    List<Map<String,Object>> selectEccData(@Param("dateTime") String dateTime);
    //    读取MES信息
    List<Map<String,Object>> selectMesData(@Param("dateTime") String dateTime);
    //    读取雷达图右边信息信息
    List<Map<String,Object>> selectSingleTextData(@Param("dateTime") String dateTime,@Param("potNo") String potNo);
    //    查询区间内停槽的天数
    String selectPotnoDays(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("potNo") String potNo);
    //    预测产铝量
    String selectPotnoForecast(@Param("dateTime") String dateTime,@Param("potNo") String potNo);
    //    存储过程产生新状态
    String procPotnoStatus(@Param("dateTime") String dateTime,@Param("potNo") String potNo);
    //    单槽氟化铝料量添加设置
    List<Map<String,Object>>  selectSingleAIF3TextData(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("potNo") String potNo);
    //    单槽氟化铝料量前3条
    List<Map<String,Object>>  selectAIF3top3(@Param("potNo") String potNo);


}

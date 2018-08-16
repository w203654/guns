package com.stylefeng.guns.modular.ecc.service;

import com.stylefeng.guns.modular.system.model.BaseInfo;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 槽控基础信息表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
public interface IBaseInfoService extends IService<BaseInfo> {
    //    读取槽控机信息
    List<Map<String,Object>> selectEccData(@Param("dateTime") String dateTime);
    //    读取雷达图右边信息信息
    List<Map<String,Object>>  selectSingleTextData(String dateTime, String potNo);
//    查询区间内停槽的天数
    String selectPotnoDays(String twodate, String newDate, String potno);
    //    存储过程产生新状态
    void procPotnoStatus(String ddate, String potNo);
//    单槽氟化铝料量添加设置
    List<Map<String,Object>> selectSingleAIF3TextData(String startTime,String endTime, String potNo);
    //    单槽氟化铝料量前3条
    List<Map<String,Object>> selectAIF3top3( String potNo);

}

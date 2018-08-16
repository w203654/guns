package com.stylefeng.guns.modular.ecc.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.model.BaseInfo;
import com.stylefeng.guns.modular.system.model.ResultInfo;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 槽控结果展示表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
public interface IResultInfoService extends IService<ResultInfo> {
    //查询不同类型槽控机状态
    List<Map<String,Object>> selectCategoryCount(String startTime, String endTime, List<String> listPotno);
//    按车间分布 槽状态查询
    List<Map<String,Object>> selectWorkshopCount(String startTime, String endTime);
}

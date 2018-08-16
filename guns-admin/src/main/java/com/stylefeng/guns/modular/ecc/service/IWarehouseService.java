package com.stylefeng.guns.modular.ecc.service;

import com.stylefeng.guns.modular.system.model.Warehouse;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产铝量盘存台帐 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-15
 */
public interface IWarehouseService extends IService<Warehouse> {
//查询 单槽管理复盘控制面版
    List<Map<String,Object>> selectSingleTable(String newDate, String potNo);
    //查询 单槽管理复盘控制面版 雷达图
    List<Map<String, Object>> selectSingleRadar(String newDate, String potNo);
    //查询 效率参数分析
    List<Map<String,Object>> parameterUnalysisPotnoList(String newDate, String workCode, int startInt, int endInt);
    //查询 效率参数分析 分项
    List<Map<String, Object>> parameterUnalysisPotnoAvage(String newDate, String workCode, int startInt, int endInt);
    //查询 单槽指示出铝量
    List<Map<String,Object>> singlePotnoData(String newDate, String potNo);
    //查询 单槽指示高效率7台
    List<Map<String, Object>> singlePotnoHeight(String newDate);
    //查询 单槽指示低效率7台
    List<Map<String, Object>> singlePotnoLow(String newDate);
    //查询 电解温度 散点图
    List<String> scatterDJWD(String newDate,String workCode);
    //查询 电解分子比 散点图
    List<String> scatterFZB(String newDate,String workCode);
    //查询 电解设定电压 散点图
    public List<String> scatterSETV(String newDate,String workCode);
}

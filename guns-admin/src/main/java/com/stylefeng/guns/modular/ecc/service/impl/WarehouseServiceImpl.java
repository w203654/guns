package com.stylefeng.guns.modular.ecc.service.impl;

import com.stylefeng.guns.modular.system.model.Warehouse;
import com.stylefeng.guns.modular.system.dao.WarehouseMapper;
import com.stylefeng.guns.modular.ecc.service.IWarehouseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产铝量盘存台帐 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-15
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {

    @Autowired
    WarehouseMapper warehouseMapper;
    //查询 单槽管理复盘控制面版
    public List<Map<String, Object>> selectSingleTable(String newDate, String potNo) {
        return warehouseMapper.selectSingleTable(newDate, potNo);
    }

    @Override
    public List<Map<String, Object>> selectSingleRadar(String newDate, String potNo) {
        return warehouseMapper.selectSingleRadar(newDate, potNo);
    }
    //查询 效率参数分析
    @Override
    public List<Map<String, Object>> parameterUnalysisPotnoList(String newDate, String workCode, int startInt, int endInt) {
        return warehouseMapper.parameterUnalysisPotnoList( newDate,  workCode,  startInt,  endInt);
    }

    //查询 效率参数分析
    public List<Map<String, Object>> parameterUnalysisPotnoAvage(String newDate, String workCode, int startInt, int endInt) {
        return warehouseMapper.parameterUnalysisPotnoAvage(newDate,  workCode,  startInt,  endInt);
    }
    //查询 单槽指示出铝量
    @Override
    public List<Map<String, Object>> singlePotnoData(String newDate, String potNo) {
        return warehouseMapper.singlePotnoData(newDate, potNo);
    }

    //查询 单槽指示高效率7台
    @Override
    public List<Map<String, Object>> singlePotnoHeight(String newDate) {
        return warehouseMapper.singlePotnoHeight(newDate);
    }
    //查询 单槽指示低效率7台
    @Override
    public List<Map<String, Object>> singlePotnoLow(String newDate) {
        return warehouseMapper.singlePotnoLow(newDate);
    }
    //查询 电解温度 散点图
    @Override
    public List<String> scatterDJWD(String newDate,String workCode) {
        return warehouseMapper.scatterDJWD(newDate,workCode);
    }
    //查询 电解分子比 散点图
    @Override
    public List<String> scatterFZB(String newDate,String workCode) {
        return warehouseMapper.scatterFZB(newDate,workCode);
    }
    //查询 电解设定电压 散点图
    @Override
    public List<String> scatterSETV(String newDate,String workCode) {
        return warehouseMapper.scatterSETV(newDate,workCode);
    }




}

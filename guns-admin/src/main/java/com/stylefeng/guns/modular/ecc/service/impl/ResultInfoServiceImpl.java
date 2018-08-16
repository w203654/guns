package com.stylefeng.guns.modular.ecc.service.impl;

import com.stylefeng.guns.modular.system.model.ResultInfo;
import com.stylefeng.guns.modular.system.dao.ResultInfoMapper;
import com.stylefeng.guns.modular.ecc.service.IResultInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 槽控结果展示表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
@Service
public class ResultInfoServiceImpl extends ServiceImpl<ResultInfoMapper, ResultInfo> implements IResultInfoService {
@Autowired
    ResultInfoMapper resultInfoMapper;
    @Override
    public List<Map<String, Object>> selectCategoryCount(String startTime, String endTime,List<String> listPotno) {
        return resultInfoMapper.selectCategoryCount(startTime,endTime,listPotno);
    }
    //    按车间分布 槽状态查询
    @Override
    public List<Map<String, Object>> selectWorkshopCount(String startTime, String endTime) {
        return resultInfoMapper.selectWorkshopCount(startTime, endTime);
    }
}

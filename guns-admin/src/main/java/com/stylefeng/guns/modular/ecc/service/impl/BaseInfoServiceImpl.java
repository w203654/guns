package com.stylefeng.guns.modular.ecc.service.impl;

import com.stylefeng.guns.modular.system.model.BaseInfo;
import com.stylefeng.guns.modular.system.dao.BaseInfoMapper;
import com.stylefeng.guns.modular.ecc.service.IBaseInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 槽控基础信息表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
@Service
public class BaseInfoServiceImpl extends ServiceImpl<BaseInfoMapper, BaseInfo> implements IBaseInfoService {
    @Autowired
    BaseInfoMapper baseInfoMapper;
    @Override
    public List<Map<String, Object>> selectEccData(String dateTime) {
        return baseInfoMapper.selectEccData(dateTime);
    }

    @Override
    public List<Map<String, Object>> selectSingleTextData(String dateTime, String potNo) {
        return baseInfoMapper.selectSingleTextData(dateTime,potNo);
    }
//    查询区间内停槽的天数
    @Override
    public String selectPotnoDays(String twodate, String newDate, String potno) {
        return baseInfoMapper.selectPotnoDays(twodate,newDate,potno);
    }
    //    存储过程产生新状态
    @Override
    public void procPotnoStatus(String dateTime, String potNo) {
        baseInfoMapper.procPotnoStatus(dateTime,potNo);
    }

//    单槽氟化铝料量添加设置

    public List<Map<String, Object>> selectSingleAIF3TextData(String startTime,String endTime ,String potNo) {
        return baseInfoMapper.selectSingleAIF3TextData(startTime,endTime,potNo);
    }
//    单槽氟化铝料量前3条
    @Override
    public List<Map<String, Object>> selectAIF3top3(String potNo) {
        return baseInfoMapper.selectAIF3top3(potNo);
    }
}

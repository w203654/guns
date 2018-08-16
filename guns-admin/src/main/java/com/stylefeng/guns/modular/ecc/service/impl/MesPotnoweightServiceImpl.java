package com.stylefeng.guns.modular.ecc.service.impl;

import com.stylefeng.guns.modular.system.dao.BaseInfoMapper;
import com.stylefeng.guns.modular.system.model.MesPotnoweight;
import com.stylefeng.guns.modular.system.dao.MesPotnoweightMapper;
import com.stylefeng.guns.modular.ecc.service.IMesPotnoweightService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * mes天车出铝量 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-30
 */
@Service
public class MesPotnoweightServiceImpl extends ServiceImpl<MesPotnoweightMapper, MesPotnoweight> implements IMesPotnoweightService {

    @Autowired
    BaseInfoMapper baseInfoMapper;
    public List<Map<String, Object>> selectMesData(String dateTime) {
        return baseInfoMapper.selectMesData(dateTime);
    }
}

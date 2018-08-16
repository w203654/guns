package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.dao.BaseInfoMapper;
import com.stylefeng.guns.modular.system.model.WorkArea;
import com.stylefeng.guns.modular.system.dao.WorkAreaMapper;
import com.stylefeng.guns.modular.system.service.IWorkAreaService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 车间对槽号 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-28
 */
@Service
public class WorkAreaServiceImpl extends ServiceImpl<WorkAreaMapper, WorkArea> implements IWorkAreaService {
    @Autowired
    WorkAreaMapper workAreaMapper;
    @Override
    public List<String> selectPontos(String workCode) {
        return workAreaMapper.selectPotnos(workCode);
    }
}

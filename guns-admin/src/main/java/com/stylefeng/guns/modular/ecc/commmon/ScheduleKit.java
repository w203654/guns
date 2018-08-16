package com.stylefeng.guns.modular.ecc.commmon;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.core.support.DateTimeKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.BaseInfoMapper;
import com.stylefeng.guns.modular.system.dao.MesPotnoweightMapper;
import com.stylefeng.guns.modular.system.dao.ResultInfoMapper;
import com.stylefeng.guns.modular.system.model.BaseInfo;
import com.stylefeng.guns.modular.system.model.MesPotnoweight;
import com.stylefeng.guns.modular.system.model.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScheduleKit {
    List<Map<String, Object>> list = new ArrayList<>();
    List<Map<String, Object>> listPotno = new ArrayList<>();
    @Autowired
    BaseInfoMapper baseInfoMapper;
    @Autowired
    ResultInfoMapper resultInfoMapper;
    @Autowired
    MesPotnoweightMapper mesPotnoweightMapper;


    @Scheduled(cron = "0 0 0/2 * * ?")
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    public void getEccData() {
        list.clear();
        System.out.println("testSchedule");
        String dateTime = DateTimeKit.formatDate(new Date());
        list = baseInfoMapper.selectEccData(dateTime);
        System.out.println(list);
    }


    @Scheduled(cron = "20 0 0/2 * * ?")
    public void insertEccDataForGuns() {
        BaseInfo baseInfo = null;
        BaseInfo baseInfoOne = null;
        System.out.println("list:" + list);
        for (Map<String, Object> map : list) {
            baseInfo = new BaseInfo();
            baseInfo.setRuntime(map.get("Ddate").toString());
            baseInfo.setPotno(map.get("PotNo").toString());

            //根据条件查询是否存在实体类 存在更新，不存在插入
            baseInfoOne = baseInfoMapper.selectOne(baseInfo);
            baseInfo.setFhlcnt(map.get("FhlCnt").toString());
            baseInfo.setYhlcnt(map.get("YhlCnt").toString());
            baseInfo.setSetv(map.get("SetV").toString());
            baseInfo.setWorkv(map.get("WorkV").toString());
            baseInfo.setAveragev(map.get("AverageV").toString());
            baseInfo.setZf(map.get("ZF").toString());
            baseInfo.setDjzsp(map.get("Djzsp").toString());
            baseInfo.setDjwd(map.get("Djwd").toString());
            baseInfo.setFzb(map.get("Fzb").toString());
            baseInfo.setLsp(map.get("Lsp").toString());
            baseInfo.setPotage(map.get("PotAge").toString());
            baseInfo.setStopage(map.get("StopAge").toString());
            baseInfo.setAge(map.get("Age").toString());
            baseInfo.setObj1(map.get("AIF3set").toString());

            baseInfo.setCreatetime(new Date());

            if (ToolUtil.isEmpty(baseInfoOne)) {
                System.out.println(JSON.toJSON(baseInfo).toString());
                baseInfoMapper.insert(baseInfo);
            } else {
                if(!(baseInfoOne.getFhlcnt().equals(map.get("FhlCnt").toString()) && baseInfoOne.getYhlcnt().equals(map.get("YhlCnt").toString()) && baseInfoOne.getFzb().equals(map.get("Fzb").toString()))){
                Wrapper<ResultInfo> warpResult = new EntityWrapper<>();
                warpResult.eq("Runtime", map.get("Ddate").toString());
                warpResult.eq("Potno", map.get("PotNo").toString());
                resultInfoMapper.delete(warpResult);//删除结果
                baseInfoMapper.deleteById(baseInfoOne.getId());//删除基础表信息
                baseInfoMapper.insert(baseInfo);//新增基础记录

            }
            }

        }

    }
//执行单槽的存储过程
    @Scheduled(cron = "20 10 0/2 * * ?")
    public void insertProcPotnoStatus() {
        BaseInfo baseInfo = new BaseInfo();
        Wrapper<BaseInfo> wrapper=new EntityWrapper<>();
        wrapper.eq("runtime",DateTimeKit.formatDate(new Date()));
        List<BaseInfo> list = baseInfoMapper.selectList(wrapper);
        for (BaseInfo info : list){
        baseInfoMapper.procPotnoStatus(DateTimeKit.formatDate(new Date()),info.getPotno());
        }
    }

    @Scheduled(cron = "0 0/25 * * * ?")
    @DataSource(name = DatasourceEnum.DATA_SOURCE_MES)
    public void getMesData() {
        listPotno.clear();
        String dateTime = DateTimeKit.formatDate(new Date());
        listPotno = baseInfoMapper.selectMesData(dateTime);
        System.out.println(listPotno);
    }

    @Scheduled(cron = "20 0/25 * * * ?")
    public void insertMesDataForGuns() {
        MesPotnoweight selectOne = new MesPotnoweight();
        MesPotnoweight iuMes = new MesPotnoweight();



        for (Map<String, Object> map : listPotno) {
            selectOne.setRuntime(map.get("RUNTIME").toString());
            selectOne.setPotno(map.get("POTNO").toString());
            iuMes = mesPotnoweightMapper.selectOne(selectOne);
            // 预测产铝量
//            String forecast=baseInfoMapper.selectPotnoForecast(map.get("RUNTIME").toString(),map.get("POTNO").toString());

            //不存在，则插入新数据 否则更新 计划和实际量
            if (ToolUtil.isEmpty(iuMes)) {
                iuMes = new MesPotnoweight();
                iuMes.setRuntime(map.get("RUNTIME").toString());
                iuMes.setPotno(map.get("POTNO").toString());
                iuMes.setPlanweight(map.get("PLANWEIGHT").toString());
                iuMes.setZweight(map.get("ZWEIGHT").toString());
                iuMes.setCreatedatetime(new Date());
                // 预测产铝量
                iuMes.setForecast(baseInfoMapper.selectPotnoForecast(map.get("RUNTIME").toString(),map.get("POTNO").toString()));
                mesPotnoweightMapper.insert(iuMes);
            } else {
//                if(!(iuMes.getZweight().equals(map.get("ZWEIGHT").toString()) && iuMes.getPlanweight().equals(map.get("PLANWEIGHT").toString()))){
                iuMes.setPlanweight(map.get("PLANWEIGHT").toString());
                iuMes.setZweight(map.get("ZWEIGHT").toString());
                // 预测产铝量
                iuMes.setForecast(baseInfoMapper.selectPotnoForecast(map.get("RUNTIME").toString(),map.get("POTNO").toString()));
                iuMes.setCreatedatetime(new Date());
                mesPotnoweightMapper.updateById(iuMes);
//                }
            }

        }
    }

}

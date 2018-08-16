package com.stylefeng.guns.modular.ecc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.support.DateTimeKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.service.IWorkAreaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.ResultInfo;
import com.stylefeng.guns.modular.ecc.service.IResultInfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 槽控结果展示控制器
 *
 * @author fengshuonan
 * @Date 2018-03-20 01:03:59
 */
@Controller
@RequestMapping("/resultInfo")
public class ResultInfoController extends BaseController {

    private String PREFIX = "/ecc/resultInfo/";

    @Autowired
    private IResultInfoService resultInfoService;
    @Autowired
    private IWorkAreaService workAreaService;



    /**
     * 跳转到槽控结果展示首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "resultInfo.html";
    }

    /**
     * 跳转到添加槽控结果展示
     */
    @RequestMapping("/resultInfo_add")
    public String resultInfoAdd() {
        return PREFIX + "resultInfo_add.html";
    }

    /**
     * 跳转到修改槽控结果展示
     */
    @RequestMapping("/resultInfo_update/{resultInfoId}")
    public String resultInfoUpdate(@PathVariable Integer resultInfoId, Model model) {
        ResultInfo resultInfo = resultInfoService.selectById(resultInfoId);
        model.addAttribute("item", resultInfo);
        LogObjectHolder.me().set(resultInfo);
        return PREFIX + "resultInfo_edit.html";
    }

    /**
     * 获取槽控结果展示列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String potNo,@RequestParam(required = false) String workCode, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String result ) {
        //查询区间槽号
        List<String> listPotno=workAreaService.selectPontos(workCode);

        //查询槽号根据条件
        Wrapper<ResultInfo> wrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(potNo))
            wrapper.like("potno", potNo);

        if (ToolUtil.isNotEmpty(workCode))
        wrapper.in("potno",listPotno);

        if (ToolUtil.isNotEmpty(beginTime) && ToolUtil.isNotEmpty(endTime))
            wrapper.between("runtime", beginTime, endTime  );

        if (ToolUtil.isNotEmpty(result))
            wrapper.eq("result", result);

        return resultInfoService.selectList(wrapper);
    }

    /**
     * 新增槽控结果展示
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ResultInfo resultInfo) {
        resultInfoService.insert(resultInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除槽控结果展示
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer resultInfoId) {
        resultInfoService.deleteById(resultInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改槽控结果展示
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ResultInfo resultInfo) {
        resultInfoService.updateById(resultInfo);
        return SUCCESS_TIP;
    }

    /**
     * 槽控结果展示详情
     */
    @RequestMapping(value = "/detail/{resultInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("resultInfoId") Integer resultInfoId) {
        return resultInfoService.selectById(resultInfoId);
    }

    /**
     * 首页面 分图
     */
    @RequestMapping(value = "/selectCategoryCount")
    @ResponseBody
    public Object selectCategoryCount(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime, @RequestParam("workCode") String workCode) {
        //查询区间槽号
        List<String> listPotno=null;
        if (ToolUtil.isNotEmpty(workCode)){
        listPotno=workAreaService.selectPontos(workCode);
        }

        Map<String, Object> mapSub = new HashMap<>();
        List<Map<String, Object>> list = resultInfoService.selectCategoryCount(startTime, endTime,listPotno);
        mapSub.put("data", list);
        return mapSub;
    }
    /**
     * 按车间分布 槽状态查询
     */
    @RequestMapping(value = "/selectWorkshopCount")
    @ResponseBody
    public Object selectWorkshopCount(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        List<Map<String, Object>>  list = resultInfoService.selectWorkshopCount(startTime, endTime);
        return list;
    }

}

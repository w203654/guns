package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.WorkArea;
import com.stylefeng.guns.modular.system.service.IWorkAreaService;

/**
 * 车间对槽号控制器
 *
 * @author fengshuonan
 * @Date 2018-05-28 17:39:31
 */
@Controller
@RequestMapping("/workArea")
public class WorkAreaController extends BaseController {

    private String PREFIX = "/system/workArea/";

    @Autowired
    private IWorkAreaService workAreaService;

    /**
     * 跳转到车间对槽号首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "workArea.html";
    }

    /**
     * 跳转到添加车间对槽号
     */
    @RequestMapping("/workArea_add")
    public String workAreaAdd() {
        return PREFIX + "workArea_add.html";
    }

    /**
     * 跳转到修改车间对槽号
     */
    @RequestMapping("/workArea_update/{workAreaId}")
    public String workAreaUpdate(@PathVariable Integer workAreaId, Model model) {
        WorkArea workArea = workAreaService.selectById(workAreaId);
        model.addAttribute("item", workArea);
        LogObjectHolder.me().set(workArea);
        return PREFIX + "workArea_edit.html";
    }

    /**
     * 获取车间对槽号列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return workAreaService.selectList(null);
    }

    /**
     * 新增车间对槽号
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WorkArea workArea) {
        workAreaService.insert(workArea);
        return SUCCESS_TIP;
    }

    /**
     * 删除车间对槽号
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer workAreaId) {
        workAreaService.deleteById(workAreaId);
        return SUCCESS_TIP;
    }

    /**
     * 修改车间对槽号
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WorkArea workArea) {
        workAreaService.updateById(workArea);
        return SUCCESS_TIP;
    }

    /**
     * 车间对槽号详情
     */
    @RequestMapping(value = "/detail/{workAreaId}")
    @ResponseBody
    public Object detail(@PathVariable("workAreaId") Integer workAreaId) {
        return workAreaService.selectById(workAreaId);
    }

    /**
     * 车间对槽号列表
     */
    @RequestMapping(value = "findPotNoByWorkCode")
    @ResponseBody
    public Object findPotNoByWorkCode(@RequestParam Integer workCode) {
        Wrapper<WorkArea> wrapper = new EntityWrapper<>();
        wrapper.eq("workcode", workCode);
        wrapper.orderBy("potno", true);
//        workAreaService.selectList(wrapper);
        return workAreaService.selectMaps(wrapper);
    }
}

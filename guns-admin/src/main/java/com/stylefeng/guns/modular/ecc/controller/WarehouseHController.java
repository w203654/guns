package com.stylefeng.guns.modular.ecc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Warehouse;
import com.stylefeng.guns.modular.system.service.IWorkAreaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.WarehouseH;
import com.stylefeng.guns.modular.ecc.service.IWarehouseHService;

import java.util.List;

/**
 * 产铝盘存台账历史控制器
 *
 * @author fengshuonan
 * @Date 2018-05-30 21:33:50
 */
@Controller
@RequestMapping("/warehouseH")
public class WarehouseHController extends BaseController {

    private String PREFIX = "/ecc/warehouseH/";

    @Autowired
    private IWarehouseHService warehouseHService;
    @Autowired
    private IWorkAreaService workAreaService;

    /**
     * 跳转到产铝盘存台账历史首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "warehouseH.html";
    }

    /**
     * 跳转到添加产铝盘存台账历史
     */
    @RequestMapping("/warehouseH_add")
    public String warehouseHAdd() {
        return PREFIX + "warehouseH_add.html";
    }

    /**
     * 跳转到修改产铝盘存台账历史
     */
    @RequestMapping("/warehouseH_update/{warehouseHId}")
    public String warehouseHUpdate(@PathVariable Integer warehouseHId, Model model) {
        WarehouseH warehouseH = warehouseHService.selectById(warehouseHId);
        model.addAttribute("item",warehouseH);
        LogObjectHolder.me().set(warehouseH);
        return PREFIX + "warehouseH_edit.html";
    }

    /**
     * 获取产铝盘存台账历史列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String workCode,String potNo) {
        //查询区间槽号
        List<String> listPotno=workAreaService.selectPontos(workCode);

        //查询槽号根据条件
        Wrapper<WarehouseH> wrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(potNo))
            wrapper.like("potno", potNo);

        if (ToolUtil.isNotEmpty(workCode))
            wrapper.in("potno",listPotno);

        return warehouseHService.selectList(wrapper);
    }

    /**
     * 新增产铝盘存台账历史
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WarehouseH warehouseH) {
        warehouseHService.insert(warehouseH);
        return SUCCESS_TIP;
    }

    /**
     * 删除产铝盘存台账历史
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer warehouseHId) {
        warehouseHService.deleteById(warehouseHId);
        return SUCCESS_TIP;
    }

    /**
     * 修改产铝盘存台账历史
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WarehouseH warehouseH) {
        warehouseHService.updateById(warehouseH);
        return SUCCESS_TIP;
    }

    /**
     * 产铝盘存台账历史详情
     */
    @RequestMapping(value = "/detail/{warehouseHId}")
    @ResponseBody
    public Object detail(@PathVariable("warehouseHId") Integer warehouseHId) {
        return warehouseHService.selectById(warehouseHId);
    }
}

package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 产铝量盘存台帐 历史
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-30
 */
@TableName("ecc_warehouse_h")
public class WarehouseH extends Model<WarehouseH> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 槽号
     */
    private String potno;
    /**
     * 工区
     */
    private String workarea;
    /**
     * 工区代码
     */
    private Integer workcode;
    /**
     * 一次日期
     */
    private Date onedate;
    /**
     * 一次盘存量
     */
    private Float onevalue;
    /**
     * 二次日期
     */
    private Date twodate;
    /**
     * 二次盘存量
     */
    private Float twovalue;
    /**
     * 产量
     */
    private Float yield;
    /**
     * 阶段效率
     */
    private Float efficiency;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPotno() {
        return potno;
    }

    public void setPotno(String potno) {
        this.potno = potno;
    }

    public String getWorkarea() {
        return workarea;
    }

    public void setWorkarea(String workarea) {
        this.workarea = workarea;
    }

    public Integer getWorkcode() {
        return workcode;
    }

    public void setWorkcode(Integer workcode) {
        this.workcode = workcode;
    }

    public Date getOnedate() {
        return onedate;
    }

    public void setOnedate(Date onedate) {
        this.onedate = onedate;
    }

    public Float getOnevalue() {
        return onevalue;
    }

    public void setOnevalue(Float onevalue) {
        this.onevalue = onevalue;
    }

    public Date getTwodate() {
        return twodate;
    }

    public void setTwodate(Date twodate) {
        this.twodate = twodate;
    }

    public Float getTwovalue() {
        return twovalue;
    }

    public void setTwovalue(Float twovalue) {
        this.twovalue = twovalue;
    }

    public Float getYield() {
        return yield;
    }

    public void setYield(Float yield) {
        this.yield = yield;
    }

    public Float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Float efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WarehouseH{" +
        "id=" + id +
        ", potno=" + potno +
        ", workarea=" + workarea +
        ", workcode=" + workcode +
        ", onedate=" + onedate +
        ", onevalue=" + onevalue +
        ", twodate=" + twodate +
        ", twovalue=" + twovalue +
        ", yield=" + yield +
        ", efficiency=" + efficiency +
        "}";
    }
}

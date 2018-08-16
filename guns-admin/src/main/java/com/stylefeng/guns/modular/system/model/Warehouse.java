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
 * 产铝量盘存台帐
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-15
 */
@TableName("ecc_warehouse")
public class Warehouse extends Model<Warehouse> {

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
    private Date onedate;
    private Float onevalue;
    private Date twodate;
    private Float twovalue;
    /**
     * 产量
     */
    private Float yield;
    /**
     * 阶段效率
     */
    private String efficiency;


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

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
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

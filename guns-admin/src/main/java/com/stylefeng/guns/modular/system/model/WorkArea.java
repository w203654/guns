package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 车间对槽号
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-28
 */
@TableName("ecc_work_area")
public class WorkArea extends Model<WorkArea> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 中文车间
     */
    private String workarea;
    /**
     * 车间代码
     */
    private Integer workcode;
    /**
     * 槽号
     */
    private Integer potno;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPotno() {
        return potno;
    }

    public void setPotno(Integer potno) {
        this.potno = potno;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WorkArea{" +
        "id=" + id +
        ", workarea=" + workarea +
        ", workcode=" + workcode +
        ", potno=" + potno +
        "}";
    }
}

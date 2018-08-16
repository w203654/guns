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
 * mes天车出铝量
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-30
 */
@TableName("ecc_mes_potnoweight")
public class MesPotnoweight extends Model<MesPotnoweight> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 运行时间
     */
    private String runtime;
    /**
     * 槽号
     */
    private String potno;
    /**
     * 计划量
     */
    private String planweight;
    /**
     * 净重量
     */
    private String zweight;
    private Date createdatetime;
    /**
     * 预测量
     */
    private String forecast;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getPotno() {
        return potno;
    }

    public void setPotno(String potno) {
        this.potno = potno;
    }

    public String getPlanweight() {
        return planweight;
    }

    public void setPlanweight(String planweight) {
        this.planweight = planweight;
    }

    public String getZweight() {
        return zweight;
    }

    public void setZweight(String zweight) {
        this.zweight = zweight;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MesPotnoweight{" +
        "id=" + id +
        ", runtime=" + runtime +
        ", potno=" + potno +
        ", planweight=" + planweight +
        ", zweight=" + zweight +
        ", createdatetime=" + createdatetime +
        ", forecast=" + forecast +
        "}";
    }
}

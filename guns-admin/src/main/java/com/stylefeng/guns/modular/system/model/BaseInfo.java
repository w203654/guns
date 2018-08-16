package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 槽控基础信息表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
@TableName("ecc_base_info")
public class BaseInfo extends Model<BaseInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 运行时间
     */
    //对象的定义中添加注解
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String runtime;
    /**
     * 槽号
     */
    private String potno;
    /**
     * 氧化铝投入量
     */
    private String yhlcnt;
    /**
     * 设定电压
     */
    private String setv;
    /**
     * 工作电压
     */
    private String workv;
    /**
     * 平均电压
     */
    private String averagev;

    /**
     * 氟化铝料量
     */
    private String fhlcnt;
    /**
     * 噪声
     */
    private String zf;
    /**
     * 电解质水平
     */
    private String djzsp;
    /**
     * 电解温度
     */
    private String djwd;
    /**
     * 分子比
     */
    private String fzb;
    private String lsp;
    private String potage;
    private String stopage;
    private String age;

    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 保留字段
     */
    private String obj1;
    /**
     * 保留字段
     */
    private String obj2;
    /**
     * 保留字段
     */
    private String obj3;


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

    public String getYhlcnt() {
        return yhlcnt;
    }

    public void setYhlcnt(String yhlcnt) {
        this.yhlcnt = yhlcnt;
    }

    public String getSetv() {
        return setv;
    }

    public void setSetv(String setv) {
        this.setv = setv;
    }

    public String getWorkv() {
        return workv;
    }

    public void setWorkv(String workv) {
        this.workv = workv;
    }

    public String getFhlcnt() {
        return fhlcnt;
    }

    public void setFhlcnt(String fhlcnt) {
        this.fhlcnt = fhlcnt;
    }

    public String getZf() {
        return zf;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }

    public String getDjzsp() {
        return djzsp;
    }

    public void setDjzsp(String djzsp) {
        this.djzsp = djzsp;
    }

    public String getDjwd() {
        return djwd;
    }

    public void setDjwd(String djwd) {
        this.djwd = djwd;
    }

    public String getFzb() {
        return fzb;
    }

    public void setFzb(String fzb) {
        this.fzb = fzb;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getObj1() {
        return obj1;
    }

    public void setObj1(String obj1) {
        this.obj1 = obj1;
    }

    public String getObj2() {
        return obj2;
    }

    public void setObj2(String obj2) {
        this.obj2 = obj2;
    }

    public String getObj3() {
        return obj3;
    }

    public void setObj3(String obj3) {
        this.obj3 = obj3;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getAveragev() {
        return averagev;
    }

    public void setAveragev(String averagev) {
        this.averagev = averagev;
    }

    public String getLsp() {
        return lsp;
    }

    public void setLsp(String lsp) {
        this.lsp = lsp;
    }

    public String getPotage() {
        return potage;
    }

    public void setPotage(String potage) {
        this.potage = potage;
    }

    public String getStopage() {
        return stopage;
    }

    public void setStopage(String stopage) {
        this.stopage = stopage;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
        "id=" + id +
        ", runtime=" + runtime +
        ", potno=" + potno +
        ", yhlcnt=" + yhlcnt +
        ", setv=" + setv +
        ", workv=" + workv +
         ", averagev=" + averagev +
        ", fhlcnt=" + fhlcnt +
        ", zf=" + zf +
        ", djzsp=" + djzsp +
        ", djwd=" + djwd +
        ", fzb=" + fzb +
        ", createtime=" + createtime +
        ", obj1=" + obj1 +
        ", obj2=" + obj2 +
        ", obj3=" + obj3 +
        "}";
    }
}

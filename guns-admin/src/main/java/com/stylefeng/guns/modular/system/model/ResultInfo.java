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
 * 槽控结果展示表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
@TableName("ecc_result_info")
public class ResultInfo extends Model<ResultInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 运行时间
     */
    private Date runtime;
    /**
     * 槽号
     */
    private String potno;
    /**
     * 氧化铝投入量
     */
    private String yhlcnt;
    /**
     * 平均电压
     */
    private String setv;
    /**
     * 工作电压
     */
    private String workv;
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
    /**
     * 结果
     */
    private String result;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 更新时间
     */
    private Date updatetime;
    /**
     * 保留字段
     */
    private String reason;
    /**
     * 保留字段
     */
    private String step;
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

    public Date getRuntime() {
        return runtime;
    }

    public void setRuntime(Date runtime) {
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
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

    @Override
    public String toString() {
        return "ResultInfo{" +
        "id=" + id +
        ", runtime=" + runtime +
        ", potno=" + potno +
        ", yhlcnt=" + yhlcnt +
        ", setv=" + setv +
        ", workv=" + workv +
        ", fhlcnt=" + fhlcnt +
        ", zf=" + zf +
        ", djzsp=" + djzsp +
        ", djwd=" + djwd +
        ", fzb=" + fzb +
        ", result=" + result +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        ", reason=" + reason +
        ", step=" + step +
        ", obj3=" + obj3 +
        "}";
    }
}

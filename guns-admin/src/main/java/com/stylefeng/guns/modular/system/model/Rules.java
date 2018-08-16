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
 * 槽控规则计算表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
@TableName("ecc_rules")
public class Rules extends Model<Rules> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 电解温度
     */
    private String djwd;
    /**
     * 氟化铝料量
     */
    private String fhlcnt;
    /**
     * 分子比
     */
    private String fzb;
    /**
     * 平均电压
     */
    private String setv;
    /**
     * 电解质水平
     */
    private String djzsp;
    /**
     * 氧化铝投入量
     */
    private String yhlcnt;
    /**
     * 噪声
     */
    private String zf;
    /**
     * 工作电压
     */
    private String workv;
    /**
     * 结果
     */
    private String result;
    /**
     * 别名
     */
    private String alias;
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
    private String Reason;
    /**
     * 保留字段
     */
    private String Step;
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

    public String getDjwd() {
        return djwd;
    }

    public void setDjwd(String djwd) {
        this.djwd = djwd;
    }

    public String getFhlcnt() {
        return fhlcnt;
    }

    public void setFhlcnt(String fhlcnt) {
        this.fhlcnt = fhlcnt;
    }

    public String getFzb() {
        return fzb;
    }

    public void setFzb(String fzb) {
        this.fzb = fzb;
    }

    public String getSetv() {
        return setv;
    }

    public void setSetv(String setv) {
        this.setv = setv;
    }

    public String getDjzsp() {
        return djzsp;
    }

    public void setDjzsp(String djzsp) {
        this.djzsp = djzsp;
    }

    public String getYhlcnt() {
        return yhlcnt;
    }

    public void setYhlcnt(String yhlcnt) {
        this.yhlcnt = yhlcnt;
    }

    public String getZf() {
        return zf;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }

    public String getWorkv() {
        return workv;
    }

    public void setWorkv(String workv) {
        this.workv = workv;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getStep() {
        return Step;
    }

    public void setStep(String Step) {
        this.Step = Step;
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
        return "Rules{" +
        "id=" + id +
        ", djwd=" + djwd +
        ", fhlcnt=" + fhlcnt +
        ", fzb=" + fzb +
        ", setv=" + setv +
        ", djzsp=" + djzsp +
        ", yhlcnt=" + yhlcnt +
        ", zf=" + zf +
        ", workv=" + workv +
        ", result=" + result +
        ", alias=" + alias +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        ", Reason=" + Reason +
        ", Step=" + Step +
        ", obj3=" + obj3 +
        "}";
    }
}

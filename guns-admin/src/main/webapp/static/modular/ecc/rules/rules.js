/**
 * 槽控规则计算管理初始化
 */
var Rules = {
    id: "RulesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Rules.initColumn = function () {
    return [

            {field: 'selectItem', radio: true},
            {title : '行号',formatter : function(value, row, index) {return index + 1;}},
            {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '电解温度', field: 'djwd', visible: true, align: 'center', valign: 'middle'},
            {title: '氟化铝料量', field: 'fhlcnt', visible: true, align: 'center', valign: 'middle'},
            {title: '分子比', field: 'fzb', visible: true, align: 'center', valign: 'middle'},
            {title: '平均电压', field: 'setv', visible: true, align: 'center', valign: 'middle'},
            {title: '电解质水平', field: 'djzsp', visible: true, align: 'center', valign: 'middle'},
            {title: '氧化铝投入量', field: 'yhlcnt', visible: true, align: 'center', valign: 'middle'},
            {title: '噪声', field: 'zf', visible: true, align: 'center', valign: 'middle'},
            /*{title: '工作电压', field: 'workv', visible: true, align: 'center', valign: 'middle'},*/
            {title: '结果代码', field: 'alias', visible: true, align: 'center', valign: 'middle'},
            {title: '结果', field: 'result', visible: true, align: 'center', valign: 'middle'},

           /* {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updatetime', visible: true, align: 'center', valign: 'middle'},*/
            {title: '保留字段', field: 'reason', visible: true, align: 'center', valign: 'middle'},
            {title: '保留字段', field: 'step', visible: true, align: 'center', valign: 'middle'},
            {title: '保留字段', field: 'obj3', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Rules.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Rules.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加槽控规则计算
 */
Rules.openAddRules = function () {
    var index = layer.open({
        type: 2,
        title: '添加槽控规则计算',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/rules/rules_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看槽控规则计算详情
 */
Rules.openRulesDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '槽控规则计算详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/rules/rules_update/' + Rules.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除槽控规则计算
 */
Rules.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/rules/delete", function (data) {
            Feng.success("删除成功!");
            Rules.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("rulesId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询槽控规则计算列表
 */
Rules.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Rules.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Rules.initColumn();
    var table = new BSTable(Rules.id, "/rules/list", defaultColunms);
    table.setPaginationType("client");
    Rules.table = table.init();
});

/**
 * 槽控专家判断库管理初始化
 */
var RulesHelp = {
    id: "RulesHelpTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RulesHelp.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '别名(来自ecc_rules中alias字段)', field: 'alias', visible: true, align: 'center', valign: 'middle'},
            {title: '备注信息', field: 'mark', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updatetime', visible: true, align: 'center', valign: 'middle'},
            {title: '保留字段', field: 'obj1', visible: true, align: 'center', valign: 'middle'},
            {title: '保留字段', field: 'obj2', visible: true, align: 'center', valign: 'middle'},
            {title: '保留字段', field: 'obj3', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
RulesHelp.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        RulesHelp.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加槽控专家判断库
 */
RulesHelp.openAddRulesHelp = function () {
    var index = layer.open({
        type: 2,
        title: '添加槽控专家判断库',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/rulesHelp/rulesHelp_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看槽控专家判断库详情
 */
RulesHelp.openRulesHelpDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '槽控专家判断库详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/rulesHelp/rulesHelp_update/' + RulesHelp.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除槽控专家判断库
 */
RulesHelp.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/rulesHelp/delete", function (data) {
            Feng.success("删除成功!");
            RulesHelp.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("rulesHelpId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询槽控专家判断库列表
 */
RulesHelp.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    RulesHelp.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RulesHelp.initColumn();
    var table = new BSTable(RulesHelp.id, "/rulesHelp/list", defaultColunms);
    table.setPaginationType("client");
    RulesHelp.table = table.init();
});

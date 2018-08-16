/**
 * 槽控机基础信息管理初始化
 */
var BaseInfo = {
    id: "BaseInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BaseInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '运行时间', field: 'runtime', visible: true, align: 'center', valign: 'middle'},
            {title: '槽号', field: 'potno', visible: true, align: 'center', valign: 'middle'},
            {title: '氧化铝投入量', field: 'yhlcnt', visible: true, align: 'center', valign: 'middle'},
            {title: '平均电压', field: 'setv', visible: true, align: 'center', valign: 'middle'},
            {title: '工作电压', field: 'workv', visible: true, align: 'center', valign: 'middle'},
            {title: '氟化铝料量', field: 'fhlcnt', visible: true, align: 'center', valign: 'middle'},
            {title: '噪声', field: 'zf', visible: true, align: 'center', valign: 'middle'},
            {title: '电解质水平', field: 'djzsp', visible: true, align: 'center', valign: 'middle'},
            {title: '电解温度', field: 'djwd', visible: true, align: 'center', valign: 'middle'},
            {title: '分子比', field: 'fzb', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
           /* {title: '保留字段', field: 'obj1', visible: true, align: 'center', valign: 'middle'},
            {title: '保留字段', field: 'obj2', visible: true, align: 'center', valign: 'middle'},
            {title: '保留字段', field: 'obj3', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
BaseInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BaseInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加槽控机基础信息
 */
BaseInfo.openAddBaseInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加槽控机基础信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/baseInfo/baseInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看槽控机基础信息详情
 */
BaseInfo.openBaseInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '槽控机基础信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/baseInfo/baseInfo_update/' + BaseInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除槽控机基础信息
 */
BaseInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/baseInfo/delete", function (data) {
            Feng.success("删除成功!");
            BaseInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("baseInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询槽控机基础信息列表
 */
BaseInfo.search = function () {
    var queryData = {};
    queryData['beginTime'] = $("#beginTime").val();
     queryData['endTime'] = $("#endTime").val();
     queryData['workCode'] = $("#workCode").val();
      queryData['potNo'] = $("#potNo").val();
    BaseInfo.table.refresh({query: queryData});
};

$(function () {
  /**
     * 初始化 日期
     */
  $('#beginTime').val(laydate.now(0, 'YYYY-MM-DD'));
  $('#endTime').val(laydate.now(0, 'YYYY-MM-DD'));
$("#workCode").change(function() {BaseInfo.search();});

 var queryData = {};
  queryData['beginTime'] = $("#beginTime").val();
  queryData['endTime'] = $("#endTime").val();
  queryData['workCode'] = $("#workCode").val();
    var defaultColunms = BaseInfo.initColumn();
    var table = new BSTable(BaseInfo.id, "/baseInfo/list", defaultColunms);
    table.setPaginationType("client");
     table.setQueryParams(queryData);
    BaseInfo.table = table.init();
});

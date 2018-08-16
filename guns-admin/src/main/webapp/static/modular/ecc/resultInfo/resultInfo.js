/**
 * 槽控结果展示管理初始化
 */
var ResultInfo = {
    id: "ResultInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ResultInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: false,visible: false},
            {title: '行号',field: '', formatter: function (value, row, index) {return index+1;}},
            {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '运行时间', field: 'runtime', visible: true, align: 'center', valign: 'middle'},
            {title: '槽号', field: 'potno', visible: true, align: 'center', valign: 'middle'},
            {title: '电解温度', field: 'djwd', visible: true, align: 'center', valign: 'middle'},
            {title: '氟化铝料量', field: 'fhlcnt', visible: true, align: 'center', valign: 'middle'},
            {title: '分子比', field: 'fzb', visible: true, align: 'center', valign: 'middle'},
            {title: '电压', field: 'setv', visible: true, align: 'center', valign: 'middle'},
            {title: '电解质水平', field: 'djzsp', visible: true, align: 'center', valign: 'middle'},
            {title: '氧化铝投入量', field: 'yhlcnt', visible: true, align: 'center', valign: 'middle'},
            {title: '噪声', field: 'zf', visible: true, align: 'center', valign: 'middle'},
            {title: '结果代码', field: 'workv', visible: true, align: 'center', valign: 'middle'},
            {title: '结　果', field: 'result', visible: true, align: 'center', valign: 'middle'},
          /*  {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updatetime', visible: true, align: 'center', valign: 'middle'},*/
           {title: '问题原因', field: 'reason', visible: true, align: 'center', valign: 'middle'},
            {title: '解决方法', field: 'step', visible: true, align: 'center', valign: 'middle'}
             /*{title: '保留字段', field: 'obj3', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
ResultInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ResultInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加槽控结果展示
 */
ResultInfo.openAddResultInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加槽控结果展示',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/resultInfo/resultInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看槽控结果展示详情
 */
ResultInfo.openResultInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '槽控结果展示详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/resultInfo/resultInfo_update/' + ResultInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除槽控结果展示
 */
ResultInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/resultInfo/delete", function (data) {
            Feng.success("删除成功!");
            ResultInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("resultInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询槽控结果展示列表
 */
ResultInfo.search = function () {
    var queryData = {};
   queryData['potNo'] = $("#potNo").val();
   queryData['workCode'] = $("#workCode").val();
   queryData['beginTime'] = $("#beginTime").val();
   queryData['endTime'] = $("#endTime").val();
    ResultInfo.table.refresh({query: queryData});
};

$(function () {

  /**
     * 初始化 日期
     */
  $('#beginTime').val(laydate.now(0, 'YYYY-MM-DD'));
  $('#endTime').val(laydate.now(0, 'YYYY-MM-DD'));
$("#workCode").change(function() {ResultInfo.search();});
$("#potNo").change(function() {ResultInfo.search();});

   var queryData = {};
  queryData['beginTime'] = $("#beginTime").val();
  queryData['endTime'] = $("#endTime").val();
  queryData['workCode'] = $("#workCode").val();
    var defaultColunms = ResultInfo.initColumn();
    var table = new BSTable(ResultInfo.id, "/resultInfo/list", defaultColunms);
    table.setPaginationType("client");
    table.setQueryParams(queryData);
    ResultInfo.table = table.init();
});

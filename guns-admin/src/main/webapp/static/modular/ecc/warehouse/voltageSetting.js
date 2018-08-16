
/**
 * 查询产铝量盘存台账列表
 */
function search() {
     var queryData = {};
     queryData['newDate'] = $("#newDate").val();
     queryData['potNo'] = $("#potNo").val();


    //初始化 雷达图左侧文本框
    initSingleText();
};
$(function () {
//初始化 常用属性
  init();


  //初始化 雷达图左侧文本框
//  initSingleText();
});

//初始化 常用属性
function init(){
$("#startTime").val(laydate.now(0, 'YYYY-MM-DD'));
$("#endTime").val(laydate.now(0, 'YYYY-MM-DD'));
initSelect();
$("#workCode").change(function() {initSelect();Warehouse.search();});
$("#potNo").change(function() {Warehouse.search();});
}



//初始化 选择框
function initSelect(){
var ajax = new $ax(Feng.ctxPath + "/workArea/findPotNoByWorkCode", function (data) {
       Feng.success("获取成功!");
       $("#potNo").empty();
       var option ='';
           $.each(data,function(i,obj){
            option += "<option values="+obj.potno+">"+obj.potno+"</option>";
           });
       $("#potNo").html(option);
   }, function (data) {
       Feng.error("获取失败!"+ data.responseJSON.message + "!");
   });
   ajax.set("workCode",$("#workCode").val());
   ajax.start();
}

//初始化 雷达图左侧文本框
function initSingleText(){
var ajax = new $ax(Feng.ctxPath + "/baseInfo/singleList", function (data) {
       Feng.success("获取成功!");
           $.each(data,function(i,obj){
//           console.log(obj);
           switch(obj.type)
           {
           case 'drcl':
             $("#drcl").val(obj.val);
             break;
           case 'drxl':
              $("#drxl").val(obj.val);
             break;
           case 'ljxl':
             $("#ljxl").val(obj.val);
             break;
           case 'ljdh':
             $("#ljdh").val(obj.val);
             break;
           case 'pjdy':
             $("#pjdy").val(obj.val);
             break;
           case 'zcll':
            $("#zcll").val(obj.val);
            break;
           default:

           }

           });
   }, function (data) {
       Feng.error("获取失败!"+ data.responseJSON.message + "!");
   });
   ajax.set("newDate",$("#newDate").val());
   ajax.set("potNo",$("#potNo").val());
   ajax.start();
}


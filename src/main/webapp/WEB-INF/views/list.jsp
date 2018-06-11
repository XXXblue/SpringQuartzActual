<%--
  Created by IntelliJ IDEA.
  User: xiaojianyu
  Date: 2018/6/5
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/common/res.jsp" %>
</head>
<body>
    <%--操作模块start--%>
    <fieldset class="layui-elem-field site-demo-button"style="padding-left: 20px;margin-left: 20px;margin-right: 20px">
        <legend>操作管理</legend>
        <blockquote class="layui-elem-quote">
            <a href="javascript:;" class="layui-btn layui-btn-small" id="addNewJob">
                <i class="layui-icon">&#xe608;</i>新增任务
            </a>
            <a href="javascript:;" class="layui-btn layui-btn-small" id="editJob" >
                <i class="layui-icon">&#xe640;</i>修改任务
            </a>
            <a href="javascript:;" class="layui-btn layui-btn-small" id="deleteJob" >
                <i class="layui-icon">&#xe640;</i>删除任务
            </a>
        </blockquote>
    </fieldset>
    <%--操作模块end--%>

    <%--表格start--%>
    <fieldset class="layui-elem-field site-demo-button"style="padding-left: 20px;margin-left: 20px;margin-right: 20px">
        <legend>任务列表</legend>
        <%--不要id和lay-filter一起用 表单回刷新两次--%>
        <table id="jobTable" class="layui-table"  lay-filter="jobTable" ></table>
    </fieldset>
    <%--表格end--%>

    <%--时间转换显示--%>
    <script type="text/html" id="createTime">
        {{ dateFormat(d.createTime) }}
    </script>

    <script type="text/html" id="status">
        {{#  if(d.status==1){ }}
        <a class="layui-btn layui-btn-mini" lay-event="open">开启</a>
        {{#  } else { }}
        <a class="layui-btn layui-btn-mini" lay-event="stop">暂停</a>
        {{#  } }}
    </script>

    <script>
        layui.use(['jquery','layer','form','table','laydate'], function() {
                var $ = layui.$;
                var layer = layui.layer;
                var table = layui.table;
                var form = layui.form;
                var laydate = layui.laydate;

            var json_data={
                "name" : "demographics",
                "params" : [1 , 2, 3],
                "items" : [ {"name" : "中文", "value" : 10 },
                    {"name": "item2",  "value" : 20 }
                ]
            };
            $.ajax({
                url : '${baseurl}kk',
                type : 'post',
                data : JSON.stringify(json_data),
                contentType : 'application/json;charset=utf-8'   //中文需要加上charset=utf-8才正确
            });

            //表格展示模块start
            table.render({
                id:'jobTable',
                elem: '#jobTable',
                url: '${baseurl}listData',
                cols:  [[ //标题栏
                    {checkbox: true, LAY_CHECKED: false}
                    ,{field: 'beanName', align:'center',title: '任务bean名称', width: 150}
                    ,{field: 'methodName', align:'center',title: '任务方法名', width: 150}
                    ,{field: 'params', align:'center',title: '参数', width: 150}
                    ,{field: 'cronExpression', align:'center',title: '任务时间表达式', width: 150}
                    ,{field: 'createTime',align:'center', title: '任务创建时间', width: 300,templet: '#createTime'}
                    ,{field: 'statusDic', align:'center',title: '任务状态', width: 150,templet:'#isOpen'}
                    ,{field: 'status', align:'center',title: '操作', width: 150,templet:'#status'}
                ]],
                page:true,
                limits: [10,20,30],
                limit:10,
                height:800
            });

            table.on('tool(jobTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'open'){ //开启
                    $.ajax({
                        url: '${baseurl}updateStatus',
                        type: 'post',
                        data: {id:data.jobId,status:0},
                        dataType: 'json',
                        success: function (resp) {
                           if(resp.ret){
                               table.reload('jobTable', {
                                   url: '${baseurl}listData'
                               });
                           }
                        }
                    });
                }else if(layEvent === 'stop'){ //停止
                    $.ajax({
                        url: '${baseurl}updateStatus',
                        type: 'post',
                        data: {id:data.jobId,status:1},
                        dataType: 'json',
                        success: function (resp) {
                            if(resp.ret){
                                table.reload('jobTable', {
                                    url: '${baseurl}listData'
                                });
                            }
                        }
                    });
                }
            });

            $("#addNewJob").on("click",function () {
                layer.open({
                    title: ['新建任务', 'font-size:18px;'],
                    type: 2,
                    area: ['900px', '600px'],
                    content: '${baseurl}jobForm/-1' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                });
            });

            $("#editJob").on("click",function () {
                var checkStatus = table.checkStatus('jobTable');
                if(checkStatus.data.length>1||checkStatus.data.length===0){
                    layer.msg("请选中一行");
                }else{
                    layer.open({
                        title: ['新建任务', 'font-size:18px;'],
                        type: 2,
                        area: ['900px', '600px'],
                        content: '${baseurl}jobForm/'+checkStatus.data[0].jobId //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                    });
                }
            });
            
            $('#deleteJob').on("click",function () {
                var checkStatus = table.checkStatus('jobTable');
                if(checkStatus.data.length===0){
                    layer.msg("至少选中一行");
                }else{
                    var items = [];
                    for(var i=0;i<checkStatus.data.length;i++){
                        items.push(checkStatus.data[i].jobId);
                    }
                    $.ajax({
                        url: '${baseurl}delete',
                        type: 'post',
                        data: JSON.stringify(items),
                        contentType:"application/json",
                        dataType: 'json',
                        success: function (resp) {
                            if(resp.ret){
                                table.reload('jobTable', {
                                    url: '${baseurl}listData'
                                });
                            }
                        }
                    });
                }
            })

        });
</script>
</body>
</html>

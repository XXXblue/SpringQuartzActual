<%--
  Created by IntelliJ IDEA.
  User: xiaojianyu
  Date: 2018/6/5
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/common/res.jsp" %>
</head>
<body>
<%--表单--%>
<form class="layui-form" action="" style="height: 400px;width:800px;margin: 50px">
        <div class="layui-form-item" hidden>
            <div class="layui-input-block">
                <input type="text" name="scheduleJobEntityCustom.jobId" autocomplete="off" class="layui-input" value="${scheduleJobEntity.jobId}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">类的名称</label>
            <div class="layui-input-block">
                <input type="text" name="scheduleJobEntityCustom.beanName" required  lay-verify="required" placeholder="请输入类名" autocomplete="off" class="layui-input" value="${scheduleJobEntity.beanName}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">方法的名称</label>
            <div class="layui-input-block">
                <input type="text" name="scheduleJobEntityCustom.methodName" required  lay-verify="required" placeholder="请输入方法名" autocomplete="off" class="layui-input" value="${scheduleJobEntity.methodName}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">参数的名称</label>
            <div class="layui-input-block">
                <input type="text" name="scheduleJobEntityCustom.params" placeholder="请输入参数名称" autocomplete="off" class="layui-input" value="${scheduleJobEntity.params}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">时间表达式</label>
            <div class="layui-input-block">
                <input type="text" name="scheduleJobEntityCustom.cronExpression" required  lay-verify="required" placeholder="请输入时间表达式" autocomplete="off" class="layui-input" value="${scheduleJobEntity.cronExpression}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">任务初始态</label>
            <div class="layui-input-block">
                <input type="radio" name="scheduleJobEntityCustom.status" value="0" title="开启">
                <input type="radio" name="scheduleJobEntityCustom.status" value="1" title="暂停" checked>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="jobForm">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
</form>
<script>
    layui.use('form', function(){
        var form = layui.form;
        if(${flag}){
            if(${scheduleJobEntity.status == '0'}){
                $('input:radio[name=scheduleJobEntityCustom\\.status]')[0].checked = true;
                $('input:radio[name=scheduleJobEntityCustom\\.status]')[1].checked = false;
            }else{
                $('input:radio[name=scheduleJobEntityCustom\\.status]')[0].checked = false;
                $('input:radio[name=scheduleJobEntityCustom\\.status]')[1].checked = true;
            }
            form.render('radio');
            //监听提交
            form.on('submit(jobForm)', function(data){
                $.ajax({
                    url: '${baseurl}update',
                    type: 'post',
                    data: data.field,
                    dataType: 'json',
                    success: function (resp) {
                        if(resp.ret){
                            parent.layui.table.reload('jobTable', {
                                url: '${baseurl}listData'
                            });
                            parent.layer.closeAll();
                            parent.layer.msg(resp.msg);
                        }
                    }
                });
                return false;
            });
        }else{
            $("input[name=scheduleJobEntityCustom\\.jobId]").val('');
            //监听提交
            form.on('submit(jobForm)', function(data){
                $.ajax({
                    url: '${baseurl}add',
                    type: 'post',
                    data: data.field,
                    dataType: 'json',
                    success: function (resp) {
                        if(resp.ret){
                            parent.layui.table.reload('jobTable', {
                                url: '${baseurl}listData'
                            });
                            parent.layer.closeAll();
                            parent.layer.msg(resp.msg);
                        }
                    }
                });
                return false;
            });
        }
    });
</script>
</body>
</html>

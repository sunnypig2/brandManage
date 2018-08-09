<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商标小项管理</title>
    <%@include file="./front/js/common/head.jsp" %>
    <style type="text/css">
        body{
            font-family: microsoft yahei;
        }
    </style>
    <link rel="stylesheet" href="${brand}/front/css/common.css"/>

</head>
<body>
    <div id="head"><span class="text">商标管理页面</span></div>
    <div id="background">
        <div class="core">
            <div style="float: left">
                <div>
                    选择组名:
                    <div>
                        <input id="groupName" name="brand" value="--请选择--"/>
                    </div>
                </div>
                <div>
                    选择类别:
                    <div>
                        <input id="type" name="brand"  value="--请选择--"/>
                    </div>
                </div>
                <div>
                    选择类似群:
                    <div>
                        <input id="same" name="brand"  class="easyui-combobox" value="--请选择--"/>
                    </div>
                </div>
                <div>
                    选择商品项:
                    <div >
                        <input id="item" class="easyui-combobox" name="brand" />
                    </div>
                </div>
                <div>
                    <div>
                        <a href="javascript:addBrandManageItem();" class="easyui-linkbutton" iconCls="icon-search" plain="true" >确定</a>
                    </div>
                    <div>
                        <a href="javascript:openWindow();" class="easyui-linkbutton" iconCls="icon-search" plain="true" >添加组</a>
                    </div>
                </div>
            </div>
            <div id="parent" style="display: none;margin-left: 200px;">
                <div>
                    <a href="javascript:addParent()" class="easyui-linkbutton" iconCls="icon-search" plain="true" >添加父类商品项</a>
                    <div >
                        <input id="parentitem" data-options="multiline:true" class="easyui-textbox" name="brand" style="width:300px;height:100px" />
                    </div>
                </div>
                <div>
                    <a href="javascript:addChild()" class="easyui-linkbutton" iconCls="icon-search" plain="true" >添加子类商品项</a>
                    <div >
                        <input id="childitem" data-options="multiline:true" class="easyui-textbox" name="brand" style="width:300px;height:100px"/>
                    </div>
                </div>
            </div>
            <div id="brother" style="display: none;margin-left: 200px;">
                组内商品项:
                <div >
                    <input id="groupitem" data-options="multiline:true" class="easyui-textbox" name="brand" style="width:300px;height:100px"/>
                </div>
            </div>
            <%--对话框--%>
            <div id="win" class="easyui-window" title="添加组" style="width:300px;height:180px;display: none;">
                <form style="padding:10px 20px 10px 40px;" id="ff">
                    <p>类别:
                        <input id="cc" style="width:150px;" value="--请选择--">
                    </p>
                    <p>类型:
                        <select id="dd" class="easyui-combobox" style="width:150px;">
                            <option>父子组</option>
                            <option>兄弟组</option>
                        </select>
                    </p>
                    <div style="padding:5px;text-align:center;">
                        <a href="javascript:addClassGroup()" class="easyui-linkbutton" icon="icon-ok">Ok</a>
                        <a href="javascript:cancelClassGroup()" class="easyui-linkbutton" icon="icon-cancel">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        $(function(){
            $("#win").window("close",true);
            $("#groupName").combobox('setValue',"--请选择--");
            $("#type").combobox('setValue',"--请选择--");
            $("#same").combobox('setValue',"--请选择--");
            $("#item").combobox('setValue',"--请选择--");

        });

        var parent = 0;
        var child = 0;
        var parentItem=[];
        var childItem=[]
        $.ajax({
            url:"${brand}/getBrandRelation",
//            async:false,
            success:function(data){
                console.log("1" + data)
                $("#groupName").combobox({
                    valueField:'id',
                    textField:'relationName',
                    editable:true,
                    required:true,
                    data:data.rows,
                    onChange:function(){
                        var id = $("#groupName").combobox("getText");
                        console.log(id)
                        $("#groupitem").textbox('setText',"")
                        $("#childitem").textbox('setText',"")
                        $("#parentitem").textbox('setText',"")
                        if(id.search("兄弟")!=-1){
                            parent = 0;
                            child = 0;
                            $("#parent").css("display","none");
                            $("#brother").css("display","block");
                        }else if(id.search("父子")!=-1){
                            $("#parent").css("display","block");
                            $("#brother").css("display","none");
                        }
                    }
                })
            }
        })

        $.ajax({
            url:"${brand}/getBrandType",
            async:false,
            success:function(data){

                $("#cc").combobox({
                    valueField:'id',
                    textField:'className',
                    editable:true,
                    required:true,
                    data:data.rows
                });

                $("#type").combobox({
                    valueField:'id',
                    textField:'className',
                    editable:true,
                    required:true,
                    data:data.rows,
                    onChange:function(){
                        var id = $("#type").combobox("getText");

                        console.log('{"classid":' +  $("#type").combobox("getValue") +'}');

                        if($("#type").combobox("getValue") == "--请选择--") return;

                        $.ajax({
                            url:"${brand}/getSameGroup?classid=" + $("#type").combobox("getValue"),
                            async:false,
                            success:function(data){
                                console.log(data)
                                $("#same").combobox({
                                    valueField:'id',
                                    textField:'groupName',
                                    editable:false,
                                    required:true,
                                    data:data.rows,
                                    onChange:function(){
                                        console.log($("#same").combobox("getText"))
                                        console.log('{"groupId":' + $("#same").combobox("getValue")  + '}')

                                        if($("#same").combobox("getValue") == "--请选择--") return;

                                        $.ajax({
                                            url:"${brand}/getItem?groupid=" + $("#same").combobox("getValue"),
                                            async:false,
                                            success:function(data){
                                                console.log(data);
                                                $("#item").combobox({
                                                    valueField:'id',
                                                    textField:'itemName',
                                                    editable:true,
                                                    required:true,
                                                    multiple:true,
                                                    data:data.rows,
                                                    onChange:function(){
                                                        var id = $("#item").combobox("getText");
                                                        console.log(id)
                                                        if(parent == 1){
                                                            $("#parentitem").textbox('setText',id)
                                                            parentItem = $("#item").combobox("getValues");
                                                            console.log(parentItem)
                                                        }else if(child == 1){
                                                            $("#childitem").textbox('setText',id)
                                                            childItem = $("#item").combobox("getValues");
                                                            console.log(childItem)
                                                        }else{
                                                            $("#groupitem").textbox('setText',id)
                                                        }
                                                    }
                                                })
                                                console.log("www" +data.rows[0].id )
                                                $("#item").combobox("setValue",data.rows[0].id);

                                            },
                                            error:function(){
                                                console.log("error")
                                            }
                                        })
                                    }
                                })
                                $("#same").combobox("setValue",data.rows[0].id);

                            }
                        })
                    }
                })
            }
        })

        function addParent(){
            parent = 1;
            child =0;
        }
        function addChild(){
            child = 1;
            parent = 0;
        }

        function addClassGroup(){
            var classid = $("#cc").combobox("getValue");
            var relationName = $("#dd").combobox("getValue");

            if(!isNaN(classid) && classid>0 && classid<46){
            }else{
                return false;
            }

            $.ajax({
                url:"${brand}/addGroup",
                async:false,
                type:'post',
                dataType:"json",
                data:'{"classid":"' + classid + '","relationName":"' + relationName + '"}',
                contentType:'application/json',
                success:function(data) {
                    if(data.state == 'success'){
                        alert("添加成功！");
                        window.location.reload();
                    }else{
                        alert("添加失败！");
                    }

                }
            });

        }

        function cancelClassGroup(){
            $("#win").window("close",true);
        }

        function openWindow(){
            $("#win").window("open");
        }

        function addBrandManageItem(){
            var relationId = $("#groupName").combobox("getValue");
            var relationName = $("#groupName").combobox("getText")
            var items = $("#item").combobox("getValues");
            var data = "";
            console.log(relationId)
            console.log(items)

            if(relationName.search("父子") != -1){
                data = '{"isBrother":"0","relationId":"' + relationId + '","parentItem":"' + parentItem + '","childItem":"' + childItem + '"}';

                if(parentItem.length != 1){
                    alert("选择一个父项");
                    return;
                }else if(childItem.length == 0){
                    alert("选择至少一个子项");
                    return;
                }
            }else{
                data = '{"isBrother":"1","relationId":"' + relationId + '","items":"' + items + '"}';
                if(items.length == 0){
                    alert("选择至少一个项");
                    return;
                }
            }

            console.log(data)

            $.ajax({
                url:'${brand}/addRelationItem',
                data:data,
                type:'post',
                dataType:'json',
                contentType:"application/json",
                success:function(data){
                    if(data.state == "success"){
                        alert("添加成功")
                    }else{
                        alert("添加失败")
                    }
                }
            })

        }
    </script>
</body>
</html>

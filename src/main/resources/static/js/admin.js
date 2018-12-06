$(function () {
    mui('.mui-scroll-wrapper').scroll({
        deceleration: 0.005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
    });

    $('img.lazy').lazyload();
    /*(function ($) {
        $(document).imageLazyload({
            placeholder: '/img/logo/loading.gif'
        });
    })(mui);*/


    var mask = mui.createMask(function () {
        return false;
    });



    //mask.close();//关闭遮罩

    document.getElementById("btn").addEventListener("click", function () {
        document.querySelector(".mui-modal").classList.add("mui-active");
    });
    document.getElementById("logout").addEventListener("click", function () {
        var btnArray = ['否', '是'];
        mui.confirm('退出后下次进入需要重新输入密码，确认退出?', '注销确认', btnArray, function(e) {
            if (e.index == 1) {
                // 注销
                window.location.href = "/logout";
            } else {
                return;
            }
        })
    });
    document.getElementById("modal_close").addEventListener("click", function () {
        document.querySelector(".mui-modal").classList.remove("mui-active");
    });

    $("#p_imgurl").uploadPreview({Img: "ImgPr", Width: 120, Height: 120});

    $(".mui-input-clear").focus(function () {
        $('#'+this.getAttribute("id")).parent().removeClass("showred");
    });


    document.getElementById("submitinfo").addEventListener('tap',function (e) {

        var p_id = $('#p_id').val();
        var p_name = $('#p_name').val();
        var p_price = $('#p_price').val();
        var p_category = $('#p_category').val();
        var isActive = $('#p_isShow').hasClass("mui-active");
        var p_isShow;
        if(isActive){
            p_isShow = 1;
        }else{
            p_isShow = 0;
        }
        var p_order = $('#p_order').val();
        var p_desc = $('#p_desc').val();
        var p_imgurl = $('#p_imgurl').get(0).files[0];
        if (p_name.length < 1){
            mui.toast("产品名称不能为空！");
            $('#p_name').parent().addClass("showred");
            return;
        }
        if (p_price.length < 1 || isNaN(p_price)){
            mui.toast("产品价格不能为空或非数字！");
            $('#p_price').parent().addClass("showred");
            return;
        }
        if (p_order === null || p_order.length < 1){
            p_order = 0;
        }
        if (isNaN(p_order)){
            mui.toast("排序不能为非数字！");
            $('#p_order').parent().addClass("showred");
            return;
        }
        if (p_desc === null || p_desc.length < 1){
            p_desc = "该产品暂无描述";
        }
        if(p_imgurl.size > 3145728){
            mui.toast("上传图片不能大于3M 请重新上传");
            return;
        }
        /* 开始上传 */
        var formData = new FormData();
        formData.append("id",p_id)
        formData.append("p_name",p_name);
        formData.append("p_price",p_price);
        formData.append("p_category",p_category);
        formData.append("p_isShow",p_isShow);
        formData.append("p_order",p_order);
        formData.append("p_desc",p_desc);
        formData.append("image",p_imgurl);

        mui(this).button('loading');
        mui($('#modal_close')).button('loading');
        $.ajax({
            type : "POST",//提交方式
            url : "/addOrUpdateProduct",//提交地址
            data:formData,
            enctype:"multipart/form-data",
            processData:false,
            contentType:false,
            success : function(data) {
                if(data.status){
                    mui.toast("操作成功！");
                    setTimeout(function () {
                        window.location.reload();
                    },500);
                }else{
                    mui(this).button('reset');
                    mui($('#modal_close')).button('reset');
                    alert("操作失败\n"+data.info);
                }
            },
            error : function(request) {
                mui(this).button('reset');
                mui($('#modal_close')).button('reset');
                alert("网络错误！！！联系我解决");
            }
        });
    })

    var btnArray = ['否', '是'];
    $("a.del").click(function () {
        var a = this;
        var p_name = this.getAttribute("p_name");
        var p_id = this.getAttribute("p_id");
        mui.confirm('确定删除产品?\n'+p_name, '删除确认', btnArray, function(e) {
            if (e.index == 1) {
                // 删除
                $.ajax({
                    type : "POST",//提交方式
                    url : "/delProduct",//提交地址
                    data: "p_id="+p_id,
                    success : function(data) {
                        if(data.status){
                            mui.toast("删除成功！");
                            a.parentNode.parentNode.remove();
                        }else{
                            alert("删除失败\n"+data.info);
                        }
                    },
                    error : function(request) {
                        alert("网络错误！！！联系我解决");
                    }
                });
            } else {
                return;
            }
        })
    });

    $("a.update").click(function () {
        var a = this;
        var p_id = this.getAttribute("p_id");
        window.location.href = "/update?id="+p_id;
    });

});
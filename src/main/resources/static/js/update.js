$(function () {

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
        if(typeof(p_imgurl) != "undefined"){
            if(p_imgurl.size > 3145728){
                mui.toast("上传图片不能大于3M 请重新上传");
                return;
            }
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
                        window.location.href = "/admin";
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


});
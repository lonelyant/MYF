mui.init();

$(function() {
    $('img.lazy').lazyload();
    mui('.mui-scroll-wrapper').scroll({
        deceleration: 0.005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
    });

    document.getElementById("telephone").addEventListener('tap', function() {
        window.location.href = "tel://13035368998";
    });


    // $(".navicate").each(function() {
    $(".mui-table-view-cell").each(function() {
        this.addEventListener('tap', function() {
            var myurl = this.getAttribute("href");
            mui('.mui-off-canvas-wrap').offCanvas('close');
            setTimeout(function() {
                window.location.href = myurl;
            }, 300);
        })
    });


    /*(function($) {
        $(document).imageLazyload({
            placeholder: 'img/logo/logo.jpg'
        });
    })(mui);*/


});

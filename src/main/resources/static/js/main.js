layui.use('element', function () {
    var $ = layui.jquery, element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    $("dd>a").click(function (e) {
        var only_id = $(this).attr('id');
        var title = $(this).text();
        var url = $(this).attr('sdf');
        var target_index_filter = 'layadmin-layout-tabs';
        //判断标签是否存在，如果存在就切换标签
        var exist = $("li[lay-id='" + only_id + "']").length;
        if (exist < 1) {
            _tabAdd(target_index_filter, title, url, only_id);
        }
        element.tabChange(target_index_filter, only_id);
    });
    //新增tab
    var _tabAdd = function (target_index_filter, title, url, only_id) {
        var iframeHeight = $(".layui-body").outerHeight() - 100;
        element.tabAdd(target_index_filter, {
            title: title,
            content: '<iframe scrolling="yes" frameborder="0" src="' + url + '"style="width:100%;height: ' + iframeHeight + 'px"></iframe>',
            id: only_id
        });
    }
});
/**
 * 显示延迟加载圈
 * @param content_path
 * @param h_rate 高系数
 * @param w_rate 宽系数
 */
function show_loading(content_path ,h_rate, w_rate, m_top) {
    var height = $(window).height()*h_rate;
    var width = $(window).width()*w_rate;
    if (m_top == '' || m_top == null || m_top == 'undefined') {
        m_top = 300;
    }
    var htmlValue = '<div class="loading_masker" style="height:'+height+'px;width:'+width+'px;' +
        'position:fixed;top:0px;left:0px;text-align:center;verticle-align:middle;">' +
        '<img style="margin-top:'+m_top+'px;" src="'+content_path+'/common/images/load.gif" />' +
        '<div>';
    $("html").append(htmlValue);
}

/**
 * 隐藏加载圈
 * @param time 延迟时间
 */
function hide_loading(time) {
    if (time == '' || time == null || time == "undefined") {
        time = 200;
    }
    setTimeout(function(){
        $(".loading_masker").remove();
    }, time);
}

/**
 *  检查 value 是否为 null
 * @param value
 * @returns {boolean}
 */
function isNull(value) {
    if (value === '' || value === undefined || value.length <= 0){
        return true;
    }else {
        return false;
    }
}

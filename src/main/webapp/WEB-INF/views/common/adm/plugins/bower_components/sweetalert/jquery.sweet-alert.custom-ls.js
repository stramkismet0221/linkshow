/**
 * 自定义提示框
 */
// 提示框
function autoCloseTimer(title, text, timer) {
    swal({
        title: title,
        timer: timer,
        showConfirmButton: false
    });
}
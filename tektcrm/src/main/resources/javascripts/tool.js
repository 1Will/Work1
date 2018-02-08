inputValidNumber = "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u6570\u5b57\uff01";

function isNumber(targetId) {
    var num = document.getElementById(targetId).value;
    s = new String(num);
    var regu = "^[0-9]+$";
    var re = new RegExp(regu);
    if (s.search(re) != -1) {
        return true;
    } else {
        alert(inputValidNumber);
        return false;
    }
}


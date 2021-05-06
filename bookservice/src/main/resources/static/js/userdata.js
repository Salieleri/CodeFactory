$(function (){
    var username = $("#loginname").text();
    var message = {"username":username};
    var option = {
        url: "/user/getuser",
        contentType : "application/json;charset=UTF-8",
        datatype : "json",
        data : JSON.stringify(message),
        async:false,
        type:"POST",
        success: function (obj){
            $("#username").val(obj.username);
            $("#email").val(obj.email);
            $("#phonenum").val(obj.phonenum);
        }
    }
    $.ajax(option);
})
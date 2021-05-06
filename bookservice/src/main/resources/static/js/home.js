$(function (){
    var option = {
        url: "/book/getname",
        contentType:"application/json;charset=UTF-8",
        type:"POST",
        async:false,
        datatype:"json",
        success:function (username){
            $("#loginname").html(username);
        }
    }
    $.ajax(option);
})
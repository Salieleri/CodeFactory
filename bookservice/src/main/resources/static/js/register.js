function issame(r){
    var x = $("#pswd").val();
    var y = $("#pswdagain").val();
    if(x !== y) {
        if(r.id === "pswd"){
            $("#messy").html("密码不一致！");
        }
        else{
            $("#mess").html("密码不一致！");
        }
    }
    else{
        $("#mess").html("");
        $("#messy").html("");
    }
}
function isexist(i){
    var message =$("#uname").val();
    var tele = {"message":message};
    var option ={
        url:"/user/test",
        contentType:"application/json;charset=UTF-8",
        type:"POST",
        async:false,
        datatype: "json",
        data: JSON.stringify(tele),
        success:function (msg){
            message=msg;
            if(msg==="true")$("#acc").html("用户已存在！");
            else $("#acc").html("");
        }
    };
    $.ajax(option);
}
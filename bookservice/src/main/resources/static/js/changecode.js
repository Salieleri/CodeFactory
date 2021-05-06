$(document).on("blur","#password",function (){
    var pswd = $(this).val();
    var pswdagain = $("#passwordagain").val();
    if(pswdagain !== "" && pswdagain !== pswd){
        $("#mess").html("密码不一致！");
    }
    else if (pswdagain === pswd){
        $("#mess").html("");
    }
})
$(document).on("blur","#passwordagain",function (){
    var pswd = $(this).val();
    var pswdagain = $("#password").val();
    if(pswdagain !== "" && pswdagain !== pswd){
        $("#messy").html("密码不一致！");
    }
    else if (pswdagain === pswd){
        $("#messy").html("");
    }
})
$(document).on("click","#change",function (){
    var pswd = $("#password").val();
    var pswdagain = $("#passwordagain").val();
    var username = $("#loginname").text();
    var message = {"username":username,"password":pswd};
    var option = {
        url:"/user/alter",
        contentType:"application/json;charset=UTF-8",
        datatype:"json",
        type:"POST",
        async:false,
        data:JSON.stringify(message),
        success:function (){
            alert("修改密码成功！");
        }
    }
    if(pswd !== pswdagain){
        alert("两次输入的密码不一致！");
    }
    else{
        $.ajax(option);
    }
})
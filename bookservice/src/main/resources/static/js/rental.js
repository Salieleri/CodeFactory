function choosepage(page){
    var message = {"page":page};
    var option = {
        url:"/book/rentlist",
        contentType:"application/json;charset=UTF-8",
        type:"POST",
        async:false,
        data:JSON.stringify(message),
        datatype:"json",
        success:function (list){
            var p1 = parseInt(page);
            p1 = (p1-1)*10;
            $.each(list,function (index,obj){
                var p2 = p1 + parseInt(index) + 1;
                var tr_app = $("<tr></tr>");
                tr_app.append($("<td>"+ p2 +"</td>"));
                tr_app.append($("<td>"+obj.booknum+"</td>"));
                tr_app.append($("<td>"+obj.bookname+"</td>"));
                if(obj.status === 1){
                    tr_app.append("<td>" + "可借阅" + "</td>");
                    tr_app.append($("<button type='button' class='btn btn-primary form-control click'>借阅</button>"));
                }
                else{
                    tr_app.append("<td>" + "不可借阅" + "</td>");
                    tr_app.append($("<button type='button' class='btn btn-danger form-control'>不可借阅</button>"));
                }
                $("#tb_body").append(tr_app);
            })
        },
        error:function (){
            alert("an error has occured!");
        }
    };
    $.ajax(option);
}
var max_page ;
$(function (){
    var max = 10;
    var message = {"max":max};
    var option = {
        url:"/book/getmaxbook",
        contentType:"application/json;charset=UTF-8",
        type:"POST",
        async:false,
        data: JSON.stringify(message),
        datatype:"json",
        success:function (mess){
            max_page = mess;
            var pagination = $(".pagination");
            pagination.append("<li class='pagination_button page_item previous'>" +
            "<a href=\"#\" aria-controls=\"example2\" data-dt-idx=\"0\" tabindex=\"0\" class=\"page-link\" id='paginprevious'>上一页</a>"
            + "</li>");
            pagination.append("<li class='pagination_button page_item active'>" +
                "<a href=\"#\" aria-controls=\"example2\" data-dt-idx=\"0\" tabindex=\"0\" class=\"page-link\" id='pagin'>1</a>"
                + "</li>");
            for(var i=2;i<=mess;i++){
                pagination.append("<li class='pagination_button page_item'>" +
                    "<a href=\"#\" aria-controls=\"example2\" data-dt-idx=\"0\" tabindex=\"0\" class=\"page-link\" id='pagin'>"+ i +"</a>"
                    + "</li>");
            }
            pagination.append("<li class='pagination_button page_item next'>" +
                "<a href=\"#\" aria-controls=\"example2\" data-dt-idx=\"0\" tabindex=\"0\" class=\"page-link\" id='paginext'>下一页</a>"
                + "</li>");
        }
    };
    $.ajax(option);
    choosepage(1);
})
$(document).on("click",".click",function(){
    var username = $("#loginname").text();
    var row = $(this).closest("tr");
    var col1 = row.children("td").eq(1).text();
    var message = {"booknum":col1,"username":username};
    var option = {
        url: "/book/rental",
        type: "POST",
        contentType:"application/json;charset=UTF-8",
        async:false,
        datatype:"json",
        data:JSON.stringify(message),
        success:function (mess){
            if(mess==="true"){
                alert("借阅成功！");
            }
            else{
                alert("借阅失败！");
            }
        }
    }
    $.ajax(option);
})
$(document).on("click","#pagin",function (){
    $("#tb_body").html("");
    $(".active").removeClass("active");
    var pos = $(this).text();//string类型
    choosepage(parseInt(pos));
    var fa = $(this).closest("li");
    fa.addClass("active");
})
$(document).on("click","#paginprevious",function (){
    var pos = $(".active").children("a").text();
    pos = parseInt(pos);
    if(pos !== 1){
        $("#tb_body").html("");
        pos--;
        var actpos = $(".active");
        actpos.prev().addClass("active");
        choosepage(pos);
        actpos.removeClass("active");
    }
})
$(document).on("click","#paginext",function (){
    var pos = $(".active").children("a").text();
    pos = parseInt(pos);
    if(pos !== max_page){
        $("#tb_body").html("");
        pos++;
        var actpos = $(".active");
        actpos.next().addClass("active");
        choosepage(pos);
        actpos.removeClass("active");
    }
})
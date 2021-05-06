var max_page_return ;
function choosepage(page){
    var username = $("#loginname").text();//a p 等文本标签不可以使用val（）取值，而应该使用text（）取值
    var message = {"username":username,"page":page};
    var option = {
        url:"/book/returnlist",
        contentType:"application/json;charset=UTF-8",
        type:"POST",
        async:false,
        datatype:"json",
        data:JSON.stringify(message),
        success:function (list){
            var p1 = parseInt(page);
            p1 = (p1-1)*10;
            $.each(list,function (index,obj){
                console.log(obj.booknum);
                console.log(obj.bookname);
                console.log(obj.rentaltime);
                var p2 = p1 + parseInt(index) + 1;
                var tr_app = $("<tr></tr>");
                tr_app.append($("<td>"+p2+"</td>"));
                tr_app.append($("<td>"+obj.booknum+"</td>"));
                tr_app.append($("<td>"+obj.bookname+"</td>"));
                tr_app.append($("<td>"+obj.rentaltime.slice(0,10)+"</td>"));
                tr_app.append($("<button type='button' class='btn btn-primary form-control return'>归还</button>"));
                //tr_app.append($("<button type='submit' class='btn btn-primary form-control'>操作</button>"));
                $("#tb_body").append(tr_app);
            })
        }
    }
    $.ajax(option);
}
$(document).on("click",".return",function (){
    var username = $("#loginname").text();
    var booknum = $(this).closest("tr").children("td").eq(1).text();
    var message = {"username":username,"booknum":booknum};
    var option = {
        url:"/book/return",
        type:"POST",
        contentType:"application/json;charset=UTF-8",
        datatype:"json",
        data:JSON.stringify(message),
        async:false,
        success:function (){
            alert("归还成功！");
        }
    }
    $.ajax(option);
})
$(function (){
    var username = $("#loginname").text();
    var max = 10;
    var message = {"max":max,"username":username};
    var option = {
        url:"/book/getmaxrental",
        contentType:"application/json;charset=UTF-8",
        type:"POST",
        async:false,
        data: JSON.stringify(message),
        datatype:"json",
        success:function (mess){
            max_page_rental = mess;
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
    if(pos !== max_page_return){
        $("#tb_body").html("");
        pos++;
        var actpos = $(".active");
        actpos.next().addClass("active");
        choosepage(pos);
        actpos.removeClass("active");
    }
})
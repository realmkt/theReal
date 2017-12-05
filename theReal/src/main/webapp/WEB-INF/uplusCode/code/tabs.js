$(document).ready(function(){
    $("ul#alen_protabs li").click(function(e){
        if (!$(this).hasClass("active")) {
            var tabNum = $(this).index();
            var nthChild = tabNum+1;
            $("ul#alen_protabs li.active").removeClass("active");
            $(this).addClass("active");
            $("ul#alen_protab li.active").removeClass("active");
            $("ul#alen_protab li:nth-child("+nthChild+")").addClass("active");
        }
    });
});
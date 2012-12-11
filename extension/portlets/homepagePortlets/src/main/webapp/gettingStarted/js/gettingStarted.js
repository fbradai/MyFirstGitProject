$(function() {

  <!--  $('.delete-action').click(function(){
        $.getJSON("/rest/testServiceStarted/delete");
        location.reload();
    });  -->


    $.getJSON("/rest/getting-started/get", function(items){
        var rate = 0;
        $.each(items, function(i, item){
            var id = item.name.substring(4);
            if(item.value == "true"){
               $("#"+id).addClass("done");
                rate +=20;
            }
            else {
                $("#"+id).removeClass("done");
            }
        });
        updateProgress(rate);
    });

    function updateProgress(rate){
        if (rate > 100){
            rate = 100;
        <!--    $('.delete-action').css("display", "inline");                 -->
        }
        var width= Math.round(160/100*rate);
        $("#progress-rate").attr("style", "width: "+width+"px;");
        $("#progress-label").html(rate+"%");
    };
});
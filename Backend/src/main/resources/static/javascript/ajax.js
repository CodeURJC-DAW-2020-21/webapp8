$(document).ready(function(){
    $("button").click(function(){
        $("#loadMore").load("templates/index.html #forum");
    });
});

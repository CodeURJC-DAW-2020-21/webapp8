function ajaxGet(page) {
    $.get("/?page="+ page, function(data){
        $("#moreEntriesbtn").remove();
        $("#moreEntries").append($(data).find("#entries").html());
    });
}
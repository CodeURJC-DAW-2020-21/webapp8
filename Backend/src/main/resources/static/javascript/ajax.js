$(function () {
    // GET REQUEST
    $("#moreEntriesbtn").on( "click", function() {
        $('#moreEntriesDiv').show(); //muestro mediante id
    });

    // $("#moreEntriesbtn").click(function (event) {
    //     event.preventDefault();
    // });

    // DO GET
    function ajaxGet() {
        let pageToFind = $("#pageToFind").val();
        debugger;
        $.ajax({
            url: "/getPagedEntries",
            type: "GET",
            dataType: "json",
            data: {pageToFind: pageToFind},
            success: function (data){
                debugger;
            },
            error: function (e) {
                debugger;
            }
        });
    }
})
$(document).ready(function() {    
    $(function() {
        $("#tabs").tabs();
        $("#textarea-container").resizable({ handles: 's', alsoResize: 'iframe' });
    });

    $("#executeButton").click(function(event) {
        exec();
    });
});

function exec() {
    var script = editor.getCode();
    DWRGroovyService.eval(script, function(data) {
        $('#output').html("");
        $('#result').html("");
        $('#stacktrace').html("");

        if (data[1] != "") {
            $("#tabs").tabs('select', 1);
            $('#output').html(data[1]).fadeIn();
        } else {
            $('#output').fadeOut();
        }

        if (data[0] != "null") {
            $("#tabs").tabs('select', 0);
            $('#result').html(data[0]).fadeIn();
        } else {
            $('#result').fadeOut();
        }
        if (data[2] != "") {
            $("#tabs").tabs('select', 2);
            $('#stacktrace').html(data[2]).fadeIn();
        } else {
            $('#stacktrace').fadeOut();
        }
    });
}

$j(document).ready(function() {
    $j('#tabs').tabs();
    $j('#textarea-container').resizable({
        handles: 's',
        alsoResize: 'iframe',
        minHeight: 220,
        maxHeight: 475
    });

    $j('#executeButton').click(function(event) {
        exec();
    });
});

function exec() {
    $j('#executeButton').attr('disabled', true);
    $j('#tabs').tabs('select', 1);
    $j('#output').html($j('#running-html').html()).fadeIn();
    var script = editor.getCode();
    DWRGroovyService.eval(script, function(data) {
        $j('#output').html("").fadeIn();
        $j('#result').html("");
        $j('#stacktrace').html("");

        // display result
        if (data[0] == "Insufficient Privileges") {
            $j("#noPrivileges").fadeIn();
        } else if (data[0] != "null") {
            $j('#tabs').tabs('select', 0);
            $j('#result').html(data[0]).fadeIn();
        } else {
            $j('#result').fadeOut();
        }

        // display output
        if (data[1] != "") {
            $j('#tabs').tabs('select', 1);
            $j('#output').html(data[1]).fadeIn();
        } else {
            $j('#output').fadeOut();
        }

        // display stacktrace
        if (data[2] != "") {
            $j('#tabs').tabs('select', 2);
            $j('#stacktrace').html(data[2]).fadeIn();
        } else {
            $j('#stacktrace').fadeOut();
        }
        $j('#executeButton').attr('disabled', false);
    });
}
$(document).ready(function() {    
    $(function() {
        $('#tabs').tabs();
        $('#textarea-container').resizable({
        	handles: 's',
        	alsoResize: 'iframe',
        	minHeight: 220,
        	maxHeight: 475
        });
    });

    $('#executeButton').click(function(event) {
        exec();
    });
});

function exec() {
	$('#executeButton').attr('disabled', true);
	$('#tabs').tabs('select', 1);
	$('#output').html($('#running-html').html()).fadeIn();
    var script = editor.getCode();
    DWRGroovyService.eval(script, function(data) {
        $('#output').html("").fadeIn();
        $('#result').html("");
        $('#stacktrace').html("");

        // display result
        if (data[0] != "null") {
            $('#tabs').tabs('select', 0);
            $('#result').html(data[0]).fadeIn();
        } else {
            $('#result').fadeOut();
        }

        // display output
        if (data[1] != "") {
            $('#tabs').tabs('select', 1);
            $('#output').html(data[1]).fadeIn();
        } else {
            $('#output').fadeOut();
        }

        // display stacktrace
        if (data[2] != "") {
            $('#tabs').tabs('select', 2);
            $('#stacktrace').html(data[2]).fadeIn();
        } else {
            $('#stacktrace').fadeOut();
        }
    	$('#executeButton').attr('disabled', false);
    });
}

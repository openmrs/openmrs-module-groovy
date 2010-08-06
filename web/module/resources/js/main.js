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
    
    $j('#script-name').click(function(event) {
    	$j('#name-dialog').dialog('open');
    });

    var newName = $j("#new-name"),
		allFields = $j([]).add(newName);
    var tips = $j(".validateTips");

    function updateTips(t) {
		tips
			.text(t)
			.addClass('ui-state-highlight');
		setTimeout(function() {
			tips.removeClass('ui-state-highlight', 1500);
		}, 500);
    }

    function checkLength(o,n,min,max) {

		if ( o.val().length > max || o.val().length < min ) {
			o.addClass('ui-state-error');
			updateTips("Length of " + n + " must be between "+min+" and "+max+".");
			return false;
		} else {
			return true;
		}

    }

	function checkRegexp(o,regexp,n) {

		if ( !( regexp.test( o.val() ) ) ) {
			o.addClass('ui-state-error');
			updateTips(n);
			return false;
		} else {
			return true;
		}

	}
		
	$j('#name-dialog').dialog({
		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		resizable: false,
		buttons: {
			'Change Name': function() {
				var bValid = true;
				allFields.removeClass('ui-state-error');

				bValid = bValid && checkLength(newName,'name',2,50);
				bValid = bValid && checkRegexp(newName,/^[a-z]([0-9a-z_])+$/i,"Username may consist of a-z, 0-9, underscores, begin with a letter.");
				
				if (bValid) {
					$j('#name').val(newName.val());
					$j('#script-name').html(newName.val());
					$j(this).dialog('close');
				}
			},
			Cancel: function() {
				$j(this).dialog('close');
			}
		},
		open: function() {
			newName.val($j('#name').val());
			newName.focus();
			newName.select();

		    // Prevent enter key from submitting page in name dialog
		    newName.keydown(function(event) {
		        if (event.which === $j.ui.keyCode.ENTER) {
		        	$j('.ui-dialog-buttonpane > button:first').click();
		            event.preventDefault();
		        }
		    });
		    
		},
		close: function() {
			allFields.val('').removeClass('ui-state-error');
		}
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
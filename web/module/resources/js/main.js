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
        if (data[0] == "Insufficient Privileges") {
            $("#noPrivileges").fadeIn();
        } else if(data[0] != "null") {
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

function saveDialog() {

}
/*
function MirrorFrame(place, options) {
  this.home = document.createElement("DIV");
  if (place.appendChild)
    place.appendChild(this.home);
  else
    place(this.home);

  var self = this;
  function makeButton(name, action) {
    var button = document.createElement("INPUT");
    button.type = "button";
    button.value = name;
    self.home.appendChild(button);
    button.onclick = function(){self[action].call(self);};
  }

  makeButton("Search", "search");
  makeButton("Replace", "replace");
  makeButton("Current line", "line");
  makeButton("Jump to line", "jump");
  makeButton("Indent all", "reindent");
  makeButton("Execute","execute");

  this.mirror = new CodeMirror(this.home, options);
}

MirrorFrame.prototype = {
  search: function() {
    var text = prompt("Enter search term:", "");
    if (!text) return;

    var first = true;
    do {
      var cursor = this.mirror.getSearchCursor(text, first);
      first = false;
      while (cursor.findNext()) {
        cursor.select();
        if (!confirm("Search again?"))
          return;
      }
    } while (confirm("End of document reached. Start over?"));
  },

  replace: function() {
    // This is a replace-all, but it is possible to implement a
    // prompting replace.
    var from = prompt("Enter search string:", ""), to;
    if (from) to = prompt("What should it be replaced with?", "");
    if (to == null) return;

    var cursor = this.mirror.getSearchCursor(from, false);
    while (cursor.findNext())
      cursor.replace(to);
  },

  jump: function() {
    var line = prompt("Jump to line:", "");
    if (line && !isNaN(Number(line)))
      this.mirror.jumpToLine(Number(line));
  },

  line: function() {
    alert("The cursor is currently at line " + this.mirror.currentLine());
    this.mirror.focus();
  },

  macro: function() {
    var name = prompt("Name your constructor:", "");
    if (name)
      this.mirror.replaceSelection("function " + name + "() {\n  \n}\n\n" + name + ".prototype = {\n  \n};\n");
  },

  reindent: function() {
    this.mirror.reindent();
  },
  execute: function() {
    exec();   
  }
};*/

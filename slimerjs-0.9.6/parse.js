var webPage = require('webpage');
var page = webPage.create();

var url = phantom.args[0];
var requestUrl = phantom.args[1];
var requestArgs = phantom.args[2];

page.open(url, function(status) {
	if (status == "success") {
		var links = page.evaluate(function() {
			var result = [];
			
			$("a.vacancy").each(function(key, value) {
				var link = $(value).attr('href'); 
				result.push(link);
			});
			
			return result;
		});

		if (links) {
			links.forEach(function(link, i, arr) {
				
				var httpConf = {};
				httpConf.operation = 'post';	
				httpConf.data = 'url=' + link + requestArgs;	
				
				var wp = require('webpage');	
				var requestPage = wp.create();		
				requestPage.openUrl(requestUrl, httpConf, null, function(status) { });	
				slimer.wait(5 * 1000);
				requestPage.close();		
			});
			slimer.wait(10 * 1000);	
		}

	}	
	phantom.exit();
});
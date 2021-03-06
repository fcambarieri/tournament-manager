modules = {
    application {
        resource url:'js/application.js'
    }
	
	'font-awesome' {
		resource url: [plugin: 'font-awesome-resources', dir: 'css', file: 'font-awesome.css']
	}
	
	adminLTECss {
		resource url: "css/bootstrap.min.css"
//		resource url: "css/font-awesome.min.css"
		resource url: "css/font-awesome.css"
		resource url: "css/ionicons.min.css"
		resource url: "css/AdminLTE.css" 
	}
	
	jqueryJs {
//		resource url: "js/jquery.min.js"
		resource url: "js/jquery.js"
	}

	landingCss {
		resource url: "css/bootstrap.min.css"
		resource url: "css/font-awesome.css"
		resource url: "css/scrolling-nav.css"
	}

	landingJs {
		resource url: "js/jquery.min.js"
		resource url: "js/jquery.easing.min.js"
		resource url: "js/scrolling-nav.js"
		
	}
	
	tree {
		//depenon 'adminLTECss'
		resource url: "js/plugins/tree/jquery.treegrid.js"
		resource url: "js/plugins/tree/jquery.treegrid.bootstrap3.js"
	}
	
	jqueryTree {
		resource url: "css/tree/jquery.treetable.css"
		resource url: "js/plugins/tree/jquery.treetable.js"
	}
}
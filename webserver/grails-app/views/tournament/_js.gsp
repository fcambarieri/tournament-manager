<scrip>

//			'global':	{ range: '*', href: 'css/style.css' },
//			'desktop':	{ range: '481-', href: 'css/style-desktop.css', containers: 1200, grid: { gutters: 25 } },
//			'1000px':	{ range: '481-1200', href: 'css/style-1000px.css', containers: 960, grid: { gutters: 25 }, viewportWidth: 1080 },
//			'mobile':	{ range: '-480', href: 'css/style-mobile.css', containers: '100%', grid: { collapse: true, gutters: 10 }, lockViewport: true }

		var skelGlobal = "${createLink(controller:'resource', action:'css',name:'style')}";
		var skelDesktop = "${createLink(controller:'resource', action:'css',name:'style-desktop')}";
		var skel100px = "${createLink(controller:'resource', action:'css',name:'style-1000px')}";
		var skelMobile = "${createLink(controller:'resource', action:'css',name:'style-mobile')}";
</scrip>
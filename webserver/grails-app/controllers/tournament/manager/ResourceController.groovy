package tournament.manager

class ResourceController {

	def beforeInterceptor = {
		def name = params.name
		def path = params.path?:"css"

		def pattern = /\.\.\//

		if (name =~ pattern || path =~ pattern) {
			response.reset ();

			response.sendError (403, "Forbidden access")
			return false;
		}
	}
	
	private path = {
		response.setContentType ("text/plain")
		
		render ("Servlet Path: " + request.getSession().getServletContext().getRealPath("/") + "\n\n")
		
		File f = new File (".")
		
		render ("Path: " + f.absolutePath + "\n\n")

		render ("Dirs:\n")
		f.eachDir () {dir->
			render (" - " + dir.name + "\n")
			}

		render ("\n")
		
		render ("Files:\n")
		f.eachFile () {file->
			if (!file.isDirectory()) {
				render (" - " + file.name + "\n")
				}
			}
	}

	def css = {
		def name = params.name
		def root = request.getSession().getServletContext().getRealPath("/")
		def path = params.path?:"css"
		
		File f = new File("${root}/${path}/${name}")
		
		if (f.exists()) {
			response.setContentType ("text/css")
			render f.getText()
		} else {
			response.sendError (404, "Hoja de estilo [${name}] no encontrada en el path [${path}]")
		}
	}

	def js = {
		def name = params.name
		def root = request.getSession().getServletContext().getRealPath("/")
		def path = params.path?:"js"
		
		File f = new File("${root}/${path}/${name}")
		
		if (f.exists()) {
			response.setContentType ("text/javascript")
			render f.getText()
		} else {
			response.sendError (404, "JS [${name}] no encontrado en el path [${path}]")
		}
	}
	
	def img = {
		def name = params.name
		def extension = name.substring (name.lastIndexOf (".") + 1)
		def root = request.getSession().getServletContext().getRealPath("/")
		def path = params.path?:"images"
		
		File f = new File("${root}/${path}/${name}")
		
		if (f.exists()) {
			response.reset()
			response.setContentType ("image/$extension")

			FileInputStream fs = new FileInputStream (f)
			
			OutputStream o = response.getOutputStream();
			byte[] buf = new byte[32 * 1024]; // 32k buffer
			int nRead = 0;
			while( (nRead=fs.read(buf)) != -1 ) {
				o.write(buf, 0, nRead);
			}
			o.flush();
			o.close();// *important* to ensure no more jsp output
			
			return;
		} else {
			response.sendError (404, "Imagen [${name}] no encontrada en el path [${path}]")
		}
	}
}

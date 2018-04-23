package org.ingservicios.practicaPaypal;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ingservicios.practicaPaypal.DAOUsuariosInterfaz;
import org.ingservicios.practicaPaypal.DTOUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//Al marcarlo con @Autowired, se inyectará, como una instancia de dao, 
	//un bean de una clase que implemente el interfaz DAOUsuariosInterfaz
	@Autowired
	private DAOUsuariosInterfaz dao;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		Cookie[] cookies= request.getCookies();
		String cookieName = "Nombre";
		String cookieValue = "";
		String url="home";
		
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("Nombre") && cookie.getValue().equals("Admin")) {
					 url="usuario";
				} 
					
				
				
				/*else if(!cookie.getValue().equals("Admin")
						&& !cookie.getValue().equals("JSESSIONID")) {
					url="usuariodatos";
				}*/
				
			}
			
		}else {
			url = "home";
		}
		
		
		return url;
	}
	
	
	@RequestMapping(value = "/Servlet1", method = {RequestMethod.GET,RequestMethod.POST})
	public String servlet1 (HttpServletRequest request, Model model, HttpServletResponse resp) {
		String usuario = request.getParameter("username");
		String pass = request.getParameter("pass");
		//url a asignar dependiendo de si es administrador o no.
		String url="";
		
		Cookie c = new Cookie("Nombre", usuario);
		Cookie c2 = new Cookie ("Password", pass);
		c.setPath("/");
		resp.addCookie(c);
		c2.setPath("/");
		resp.addCookie(c2);
		
		List <DTOUsuarios> lista = dao.leeUsuarios();
		
		if(dao.buscaAdmin(usuario, pass)!=null) {
				
			
				url="usuario";
	
		}else if(dao.buscaUsuario(usuario, pass)!=null){
				DTOUsuarios dto = new DTOUsuarios();
				dto=dao.buscaUsuario(usuario, pass);
				//En Spring se utiliza model.addAttribute en vez de req.setAttribute para 
				//agregar el atributo proporcionado bajo el nombre proporcionado.
				model.addAttribute("dto", dto);
				
				url="usuariodatos";
			}
			
		
		
		//Significa que el usuario no existe
		if(!url.equals("usuario") && !url.equals("usuariodatos")) {
			url="registro";
		}
		
		//En Spring se utiliza model.addAttribute en vez de req.setAttribute para 
		//agregar el atributo proporcionado bajo el nombre proporcionado.
		model.addAttribute("lista", lista);

		return url;
	}
	
	
}

package org.ingservicios.practicaPaypal;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ingservicios.practicaPaypal.DAOUsuariosInterfaz;
import org.ingservicios.practicaPaypal.DTOUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	//Al marcarlo con @Autowired, se inyecta como una instancia de dao, 
	//un bean de una clase que implemente el interfaz DAOUsuariosInterfaz
	@Autowired
	private DAOUsuariosInterfaz dao;
	
	@Autowired
	private DAOArticulosInterfaz dao2;
	
	//DNI para poder guardarlo en una variable a la hora de utilizar para modificar usuario.
	private String guardaDni="";
	//Lo usamos para calcular despu�s la suma del carrito
	private float sumaTotal=0;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Cookie[] cookies= request.getCookies();
		String cookieName = "Nombre";
		String cookieValue = "";
		String cookieNamePassword = "Password";
		String cookieValuePassword = "";

		String url="home";
		
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookieName.equals(cookie.getName())) {
					cookieValue = cookie.getValue();
					
				}
				if(cookieNamePassword.equals(cookie.getName())){
					cookieValuePassword = cookie.getValue();
				}
				
			}
			
			if(cookieValue.equals("Admin") && cookieValuePassword.equals("12345")) {
			List <DTOUsuarios> lista = dao.leeUsuarios();
			model.addAttribute("lista", lista);
			url="usuario";
			}else if(cookieValue.equals("") && cookieValuePassword.equals("")) { 
				//Si no hay cookie de nombre de password creamos una cookie idSesion)
				url="home";	
			
			}else{
				List <DTOArticulos> listaArticulos = dao2.leeArticulos();
				model.addAttribute("listaArticulos", listaArticulos);
				url="listaArticulos";
			}
			
		}else {
			url = "home";
		}
		
		
		
		return url;
	}
	
	@RequestMapping(value = "/Inicio", method = {RequestMethod.GET,RequestMethod.POST})
	public String volverInicio (HttpServletRequest request, Model model, HttpServletResponse resp) {
		return "home";
	}
	
	@RequestMapping(value = "/Servlet1", method = {RequestMethod.GET,RequestMethod.POST})
	public String servlet1 (HttpServletRequest request, Model model, HttpServletResponse resp) {
		HttpSession session = request.getSession(true);
		String usuario = request.getParameter("username");
		String pass = request.getParameter("pass");
		//Dependiendo de si es administrador o no se le asignaremos una url.
		String url="";

		
		List <DTOUsuarios> lista = dao.leeUsuarios();
		List <DTOArticulos> listaArticulos = dao2.leeArticulos();
		
		
		if(dao.buscaAdmin(usuario, pass)!=null) {
			Cookie c = new Cookie("Nombre", usuario);
			Cookie c2 = new Cookie ("Password", pass);
			c.setPath("/");
			c.setMaxAge(-1);
			resp.addCookie(c);
			c2.setPath("/");
			c2.setMaxAge(-1);
			resp.addCookie(c2);
			
			url="usuario";
	
			
		}else if(dao.buscaUsuario(usuario, pass)!=null){
			
				Cookie c = new Cookie("Nombre", usuario);
				Cookie c2 = new Cookie ("Password", pass);
				c.setPath("/");
				c.setMaxAge(-1);
				resp.addCookie(c);
				c2.setPath("/");
				c2.setMaxAge(-1);
				resp.addCookie(c2);
				
				
				DTOUsuarios dto = new DTOUsuarios();
				dto=dao.buscaUsuario(usuario, pass);
				//En Spring se usa model.addAttribute, en vez de req.setAttribute que us�bamos en los servlets, para agregar el atributo proporcionado bajo
				//el nombre proporcionado.
				model.addAttribute("dto", dto);
				
				// Guardamos el DNI al ser clave primaria en la base de datos y utilizarlo para modificar los datos de un usuario.
				guardaDni=dto.getDni();
				
				
				url="listaArticulos";
			}
			
		
		
		//Significa que el usuario no existe
		if(!url.equals("usuario") && !url.equals("listaArticulos")) {
			url="home";
		}
		
		model.addAttribute("lista", lista);
		model.addAttribute("listaArticulos", listaArticulos);
		


		return url;
	}
	
	

	@RequestMapping(value = "/Registro", method = {RequestMethod.GET,RequestMethod.POST})
	public String registro (HttpServletRequest request, Model model, HttpServletResponse resp) {
		
	return "registro";
	}
	

	@RequestMapping(value = "/Servlet2", method = {RequestMethod.GET,RequestMethod.POST})
	public String servlet2 (HttpServletRequest request, Model model, HttpServletResponse resp) {

			HttpSession session = request.getSession(true);
			//Parameter(...) es del html 
			String usuario = request.getParameter("username");
			//Lo a�adimos al model
			model.addAttribute("Nombre", usuario);
			
			String password = request.getParameter("pass");
			//Lo a�adimos al model
			model.addAttribute("Password", password);
			
			String email = request.getParameter("email");
			//Lo a�adimos al model
			model.addAttribute("Email", email);	
			
			String dni = request.getParameter("dni");
			//Lo a�adimos al model
			model.addAttribute("DNI", dni);
			
			
			Cookie c = new Cookie("Nombre", usuario);
			Cookie c2 = new Cookie ("Password", password);
			c.setPath("/");
			c.setMaxAge(-1);
			resp.addCookie(c);
			c2.setPath("/");
			c2.setMaxAge(-1);
			resp.addCookie(c2);
			
			DTOUsuarios usuarioDTO = new DTOUsuarios(usuario,password,email,dni);

			String url="";
			
			if(dao.buscaUsuario(usuario, email, dni)==true || dao.buscaUsuarioEmail(usuario, email)!=null
					|| dao.buscaUsuario(dni)!=null) {//Buscamos para ver si el usuario ya ha sido registrado
					
				    url="usuarioYaRegistrado";
			}else {

					dao.addUsuario(usuarioDTO);
					url="usuarioRegistrado";
				}
			
			return url;
}


		@RequestMapping(value="/Modificar", method= {RequestMethod.GET, RequestMethod.POST})
		public String modificar(HttpServletRequest request, Model model, HttpServletResponse resp) {
			
			return "modificacion";
		}

@RequestMapping(value = "/ServletModificar", method = {RequestMethod.GET,RequestMethod.POST})
public String servletmodificar (HttpServletRequest request, Model model, HttpServletResponse resp) {

	Cookie[] cookies= request.getCookies();
	String cookieName = "Nombre";
	String cookieValue = "";
	String cookieNamePassword = "Password";
	String cookieValuePassword = "";
	
	//Sii no encuentra el usuario seg�n sus cookies
	String url="usuarioNoModificado";
	
	if(cookies != null) {
		for(Cookie cookie: cookies) {
			if(cookieName.equals(cookie.getName())) {
				cookieValue = cookie.getValue();
				
			}
			if(cookieNamePassword.equals(cookie.getName())){
				cookieValuePassword = cookie.getValue();
			}
			
			
		}
		

		
	if(dao.buscaUsuario(cookieValue, cookieValuePassword)!= null) {
		
		String usuario = request.getParameter("username");
		//Lo a�adimos al model
		model.addAttribute("Nombre", usuario);
		
		String password = request.getParameter("pass");
		//Lo a�adimos al model
		model.addAttribute("Password", password);
		
		String email = request.getParameter("email");
		//Lo a�adimos al model
		model.addAttribute("Email", email);	
		
		String dni = request.getParameter("dni");
		//Lo a�adimos al model
		model.addAttribute("DNI", dni);
				
		//El usuario introduce dni que ya est� en la base de datos.
		if((dao.buscaUsuario(dni)!=null && dao.buscaUsuario(dni).getDni().equals(guardaDni)) || 
				dao.buscaUsuario(dni)==null ) {
			DTOUsuarios usuarioDTO = new DTOUsuarios(usuario,password,email,dni);
			Cookie c = new Cookie("Nombre", usuario);
			Cookie c2 = new Cookie ("Password", password);
			c.setPath("/");
			resp.addCookie(c);
			c2.setPath("/");
			resp.addCookie(c2);
			
			
		dao.modificaUsuario(usuarioDTO, guardaDni);
		
		//Volvemos a guardar el DNI por si el usuario quiere hacer otra modificacion
		guardaDni=dni;
		
		url="usuarioModificado";
		}else {
			url="usuarioNoModificado";
		}
		}
	return url;

}
return url;


}


@RequestMapping(value="/Add", method= {RequestMethod.GET, RequestMethod.POST})
public String add(HttpServletRequest request, Model model, HttpServletResponse resp) {
	boolean bool = true;
	HttpSession session = request.getSession(true);
	String accion = request.getParameter("accion_servlet");
	int numeroElementos=0;

	int itemsGuardados[] = (int[]) session.getAttribute("itemsGuardados");

			if (itemsGuardados == null){
			itemsGuardados = new int[3];
			bool = false;
			}

	
	if (accion.equals("accion1")) {
		if(bool!=false) {
			
			numeroElementos = itemsGuardados[0];
			
		}else {
			numeroElementos = 0;
			bool=true;
		}
		
		itemsGuardados[0] = numeroElementos + 1;
		session.setAttribute("itemsGuardados", itemsGuardados);
		}
		if (accion.equals("accion2")) {

			if(bool!=false) {
				numeroElementos = itemsGuardados[1];
				
			}else {
				numeroElementos = 0;
				bool=true;
			}
			
			itemsGuardados[1] = numeroElementos + 1;
			session.setAttribute("itemsGuardados", itemsGuardados);
			}
		
		if (accion.equals("accion3")) {
			if(bool!=false) {
				numeroElementos = itemsGuardados[2];
				
			}else {
				numeroElementos = 0;
				bool=true;
			}
			itemsGuardados[2] = numeroElementos + 1;
			session.setAttribute("itemsGuardados", itemsGuardados);
			}
		

		List <DTOArticulos> listaArticulos = dao2.leeArticulos();
		model.addAttribute("listaArticulos", listaArticulos);
	
	return "listaArticulos";
}


@RequestMapping(value="/Carrito", method= {RequestMethod.GET, RequestMethod.POST})
public String carrito(HttpServletRequest request, Model model, HttpServletResponse resp) {
	String url="";
	int cantidad1=0, cantidad2=0, cantidad3=0;
	float precio1=0, precio2=0, precio3=0;
	HttpSession session = request.getSession(true);
	int itemsGuardados[] = (int[]) session.getAttribute("itemsGuardados");
	
	if (itemsGuardados == null) {
		//jsp diciendo que no hay productos
		model.addAttribute("Cantidad1", cantidad1);
		model.addAttribute("Cantidad2", cantidad2);
		model.addAttribute("Cantidad3", cantidad3);
		url="carritoCompra";
		
		url = "listaArticulos";
		List <DTOArticulos> listaArticulos = dao2.leeArticulos();
		model.addAttribute("listaArticulos", listaArticulos);
		
	}else {
	
		url="carritoCompra";
	
	for(int pos=0; pos<itemsGuardados.length;pos++) {
		if(pos==0) {
			
			cantidad1 = itemsGuardados[pos];
			precio1 = dao2.buscaArticulo(pos).getPrecio();
			sumaTotal= (cantidad1*precio1);
			
		}
		if(pos==1) {
			cantidad2 = itemsGuardados[pos];
			precio2 = dao2.buscaArticulo(pos).getPrecio();
			sumaTotal=  (cantidad1*precio1)+(cantidad2*precio2);
			
		}
		if(pos==2) {
			cantidad3 = itemsGuardados[pos];
			precio3 = dao2.buscaArticulo(pos).getPrecio();
			sumaTotal=  (cantidad1*precio1)+(cantidad2*precio2)+(cantidad3*precio3);
			
		}
	}
	
	model.addAttribute("Cantidad1", cantidad1);
	model.addAttribute("Precio1", precio1);
	model.addAttribute("Cantidad2", cantidad2);
	model.addAttribute("Precio2", precio2);
	model.addAttribute("Cantidad3", cantidad3);
	model.addAttribute("Precio3", precio3);	
	session.setAttribute("sumaTotal", sumaTotal);
	model.addAttribute("Suma", sumaTotal);
	}
	return url;
}


@RequestMapping(value="/sigueComprando", method= {RequestMethod.GET, RequestMethod.POST})
public String sigueComprando(HttpServletRequest request, Model model, HttpServletResponse resp) {
	
	HttpSession session = request.getSession(true);
	
	List <DTOArticulos> listaArticulos = dao2.leeArticulos();
	model.addAttribute("listaArticulos", listaArticulos);
	return "listaArticulos";
	
}



@RequestMapping(value="/cancelaSuma", method= {RequestMethod.GET, RequestMethod.POST})
public String cancelaSuma(HttpServletRequest request, Model model, HttpServletResponse resp) {

	HttpSession session = request.getSession(true);
	int itemsGuardados[] = new int[3];
	
	session.setAttribute("itemsGuardados", itemsGuardados);
	
	
	List <DTOArticulos> listaArticulos = dao2.leeArticulos();
	model.addAttribute("listaArticulos", listaArticulos);
	
	return "listaArticulos";
	
}

@RequestMapping(value="/cerrarSesion", method= {RequestMethod.GET, RequestMethod.POST})
public String cierraSesion(HttpServletRequest request, Model model, HttpServletResponse resp) {

	HttpSession session = request.getSession(true);
	session.invalidate();
	
	//Mandamos las cookies vac�as, (no borrado)
	Cookie c = new Cookie("Nombre", "");
	Cookie c2 = new Cookie ("Password", "");
	c.setPath("/");
	resp.addCookie(c);
	c2.setPath("/");
	resp.addCookie(c2);
	
	
	return "home";
	
}

}

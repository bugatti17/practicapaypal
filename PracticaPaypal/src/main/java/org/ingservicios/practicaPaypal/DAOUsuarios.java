package org.ingservicios.practicaPaypal;


import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class DAOUsuarios implements DAOUsuariosInterfaz {
	
	public JdbcTemplate jdbcTemplate;
	//private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
		public List<DTOUsuarios> leeUsuarios(){
			String sql = "select * from usuarios";
			UsuarioMapper mapper = new UsuarioMapper();
			List<DTOUsuarios> usuarios = this.jdbcTemplate.query(sql,mapper);
			return usuarios;
		}
		
		public DTOUsuarios buscaAdmin(String nombre, String contraseña){ //Nos devuelve el usuario buscado(Admin) o null si no existe
			if(nombre.equals("Admin") && contraseña.equals("12345")) {
			String sql = "select * from usuarios where Nombre = ? AND Password = ?";
			Object[ ] parametros = {nombre, contraseña}; //Array de objetos
			UsuarioMapper mapper = new UsuarioMapper();
			List<DTOUsuarios> usuarios = this.jdbcTemplate.query(sql, parametros, mapper);
			if (usuarios.isEmpty()) return null;
			else return usuarios.get(0);
			}else {
				return null;
			}
			}
		
		
		public DTOUsuarios buscaUsuario(String id){ //Nos devuelve el usuario buscado o null si no existe
			String sql = "select * from usuarios where DNI = ?";
			Object[ ] parametros = {id}; //Array de objetos
			UsuarioMapper mapper = new UsuarioMapper();
			List<DTOUsuarios> usuarios = this.jdbcTemplate.query(sql, parametros, mapper);
			if (usuarios.isEmpty()) return null;
			else return usuarios.get(0);
			}
		
		
		public DTOUsuarios buscaUsuario(String nombre, String contraseña){ //Nos devuelve el usuario buscado o null si no existe
			String sql = "select * from usuarios where Nombre = ? AND Password = ?";
			Object[ ] parametros = {nombre,contraseña}; //Array de objetos
			UsuarioMapper mapper = new UsuarioMapper();
			List<DTOUsuarios> usuarios = this.jdbcTemplate.query(sql, parametros, mapper);
			if (usuarios.isEmpty()) return null;
			else return usuarios.get(0);
			}
		
		public DTOUsuarios buscaUsuarioEmail(String nombre, String email){ //Devuelve el usuario buscado o null si no existe
			String sql = "select * from usuarios where Nombre = ? AND Email = ?";
			Object[ ] parametros = {nombre,email}; //Array de objetos
			UsuarioMapper mapper = new UsuarioMapper();
			List<DTOUsuarios> usuarios = this.jdbcTemplate.query(sql, parametros, mapper);
			if (usuarios.isEmpty()) return null;
			else return usuarios.get(0);
			}
		
		public boolean buscaUsuario(String nombre, String email, String dni){ 
			String sql = "SELECT * FROM usuarios WHERE Nombre= ? AND Email = ? AND DNI = ?";
			Object[ ] parametros = {nombre,email,dni}; //Array de objetos
			UsuarioMapper mapper = new UsuarioMapper();
			List<DTOUsuarios> usuarios = this.jdbcTemplate.query(sql, parametros, mapper);
			if (usuarios.isEmpty()) return false;
			else return true;
			}
		
		public void addUsuario(DTOUsuarios usuario) {			
			String sql = "insert into usuarios values(?,?,?,?)";
			Object[ ] parametros = {usuario.getNombre(),usuario.getPassword(), usuario.getEmail(),usuario.getDni()}; //Array de objetos
			//Para INSERT, UPDATE o DELETE --> jdbcTemplate.update
			this.jdbcTemplate.update(sql,parametros);
		}
		
		public void modificaUsuario(DTOUsuarios usuario, String dni) {			
			String sql = "update usuarios SET Nombre = ?, Password = ?, Email = ?, DNI = ? WHERE DNI = ?";		
			Object[ ] parametros = {usuario.getNombre(),usuario.getPassword(), usuario.getEmail(), usuario.getDni(), dni}; //Array de objetos
			//Para INSERT, UPDATE o DELETE --> jdbcTemplate.update
			this.jdbcTemplate.update(sql,parametros);
		}
		
		
}


package org.ingservicios.practicaPaypal;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//Indica que el bean es un dao
@Repository
public class DAOArticulos implements DAOArticulosInterfaz {
	
		//Añadir libreria spring-jdbc y dependencia junto a la versión
		public JdbcTemplate jdbcTemplate;
		//private DataSource dataSource;
		@Autowired
		public void setDataSource(DataSource dataSource2) {
		this.jdbcTemplate = new JdbcTemplate(dataSource2);
		}
		
			public List<DTOArticulos> leeArticulos(){
				String sql = "select * from articulos";
				ArticulosMapper mapper = new ArticulosMapper();
				List<DTOArticulos> articulos = this.jdbcTemplate.query(sql,mapper);
				return articulos;
			}
						
			
			public DTOArticulos buscaArticulo(int id){ //Devuelve el articulo buscado o null si no existe
				String sql = "select * from articulos where Codigo = ?";
				Object[ ] parametros = {id}; //Array de objetos
				ArticulosMapper mapper = new ArticulosMapper();
				List<DTOArticulos> articulos = this.jdbcTemplate.query(sql, parametros, mapper);
				if (articulos.isEmpty()) return null;
				else return articulos.get(0);
				}
			
			
			public DTOArticulos buscaPrecioArticulo(int id) {
				String sql = "select Precio from articulos where Codigo = ?";
				Object[] parametros = {id};
				ArticulosMapper mapper = new ArticulosMapper();
				List<DTOArticulos> articulos = this.jdbcTemplate.query(sql, parametros, mapper);
				if (articulos.isEmpty()) return null;
				else return articulos.get(0);
			}
			
			
	}
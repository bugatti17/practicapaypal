package org.ingservicios.practicaPaypal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ArticulosMapper implements RowMapper <DTOArticulos>{

		public DTOArticulos mapRow(ResultSet rs, int rowNum) throws SQLException{
		DTOArticulos articulo = new DTOArticulos();
		articulo.setNombre(rs.getString("Nombre"));
		articulo.setPrecio(rs.getFloat("Precio"));
		articulo.setCodigo(rs.getInt("Codigo"));
		//articulo.setImagen(rs.getBlob("Imagen"));
		return articulo;
		}
}

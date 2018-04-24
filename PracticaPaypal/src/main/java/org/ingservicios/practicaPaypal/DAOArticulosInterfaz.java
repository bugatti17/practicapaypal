package org.ingservicios.practicaPaypal;

import java.util.List;

public interface DAOArticulosInterfaz {

	public List<DTOArticulos> leeArticulos();
	
	public DTOArticulos buscaArticulo(int id);
	
}

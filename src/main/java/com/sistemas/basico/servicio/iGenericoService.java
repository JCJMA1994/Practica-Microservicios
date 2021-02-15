package com.sistemas.basico.servicio;

import java.util.List;

public interface iGenericoService<Entidad, ID> {

    Entidad agregar(Entidad entidad);

    List<Entidad> listarTodos();

    Entidad buscar(ID id);

    Entidad actualizar(Entidad entidad);

    void eliminar(ID id);
}

package com.sistemas.basico.servicio.impl;

import com.sistemas.basico.dominio.Tarifa;
import com.sistemas.basico.repositorio.TarifaRepository;
import com.sistemas.basico.servicio.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaServiceImpl implements TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    @Override
    public Tarifa agregar(Tarifa entidad) {
        Tarifa result;

        result = tarifaRepository.saveAndFlush(entidad);
        return result;
    }

    @Override
    public List<Tarifa> listarTodos() {
        List<Tarifa> result = tarifaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        return result;
    }

    @Override
    public Tarifa buscar(Long id) {
        Optional<Tarifa> buscado = tarifaRepository.findById(id);
        Tarifa result = null;

        if (buscado.isPresent()) {
            result = buscado.get();
        }
        return result;

    }

    @Override
    public Tarifa actualizar(Tarifa entidad) {
        Tarifa result;

        result = tarifaRepository.saveAndFlush(entidad);
        return result;
    }

    @Override
    public void eliminar(Long id) {
        tarifaRepository.deleteById(id);
    }
}

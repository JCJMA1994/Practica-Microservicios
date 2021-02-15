package com.sistemas.basico.servicio.impl;

import com.sistemas.basico.servicio.EtapaService;

import com.sistemas.basico.dominio.Etapa;
import com.sistemas.basico.repositorio.EtapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtapaServiceImpl implements EtapaService{
    @Autowired
    private EtapaRepository etapaRepository;

    @Override
    public Etapa agregar(Etapa entidad) {
        Etapa result;

        result = etapaRepository.saveAndFlush(entidad);
        return result;
    }

    @Override
    public List<Etapa> listarTodos() {
        List<Etapa> result = etapaRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));

        return result;
    }

    @Override
    public Etapa buscar(Long id) {
        Optional<Etapa> buscado = etapaRepository.findById(id);
        Etapa result = null;

        if (buscado.isPresent()) {
            result = buscado.get();
        }
        return result;

    }

    @Override
    public Etapa actualizar(Etapa entidad) {
        Etapa result;
        result = etapaRepository.saveAndFlush(entidad);
        return result;
    }

    @Override
    public void eliminar(Long id) {
        etapaRepository.deleteById(id);
    }

}

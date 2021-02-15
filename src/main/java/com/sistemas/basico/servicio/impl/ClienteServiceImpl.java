package com.sistemas.basico.servicio.impl;

import com.sistemas.basico.dominio.Cliente;
import com.sistemas.basico.repositorio.ClienteRepository;
import com.sistemas.basico.servicio.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente agregar(Cliente entidad) {
        Cliente result;

        result = clienteRepository.saveAndFlush(entidad);
        return result;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> result = clienteRepository.findAll(Sort.by(Sort.Direction.ASC,"apellido"));
        return result;
    }

    @Override
    public Cliente buscar(Long id) {
        Optional<Cliente> buscado = clienteRepository.findById(id);
        Cliente result = null;

        if(buscado.isPresent()){
            result = buscado.get();
        }
        return result;
    }

    @Override
    public Cliente actualizar(Cliente entidad) {
        Cliente result;

        result = clienteRepository.saveAndFlush(entidad);

        return result;
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}

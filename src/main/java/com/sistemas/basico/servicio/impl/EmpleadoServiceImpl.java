package com.sistemas.basico.servicio.impl;

import com.sistemas.basico.dominio.Empleado;
import com.sistemas.basico.repositorio.EmpleadoRepository;
import com.sistemas.basico.servicio.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado agregar(Empleado entidad){
        Empleado result;

        result = empleadoRepository.saveAndFlush(entidad);
        return result;
    }

    @Override
    public List<Empleado> listarTodos(){
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado buscar(Long id){
        Optional<Empleado> buscado=empleadoRepository.findById(id);
        Empleado result=null;

        if (buscado.isPresent()){
            result = buscado.get();
        }
        return result;

    }

    @Override
    public Empleado actualizar(Empleado entidad){
        Empleado result;

        result = empleadoRepository.saveAndFlush(entidad);
        return result;
    }

    @Override
    public void eliminar(Long id){
        empleadoRepository.deleteById(id);
    }
}

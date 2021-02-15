package com.sistemas.basico.repositorio;


import com.sistemas.basico.dominio.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Long> {
}

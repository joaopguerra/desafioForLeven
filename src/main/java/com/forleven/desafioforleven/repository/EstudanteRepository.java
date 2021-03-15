package com.forleven.desafioforleven.repository;

import com.forleven.desafioforleven.model.entity.EstudanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudanteRepository extends JpaRepository<EstudanteEntity, Long> {
}

package com.forleven.desafioforleven.repositories;

import com.forleven.desafioforleven.entities.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
}

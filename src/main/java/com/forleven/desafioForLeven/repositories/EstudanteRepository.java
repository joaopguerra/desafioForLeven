package com.forleven.desafioForLeven.repositories;

import com.forleven.desafioForLeven.entities.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
}

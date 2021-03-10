package com.forleven.desafioForLeven.services;

import com.forleven.desafioForLeven.dto.EstudanteDTO;
import com.forleven.desafioForLeven.entities.Estudante;
import com.forleven.desafioForLeven.repositories.EstudanteRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository repository;

    public List<EstudanteDTO> findAll() {
        List<Estudante> list = repository.findAll();
        return list.stream().map(est -> new EstudanteDTO(est)).collect(Collectors.toList());
    }

    public Estudante findByMatricula(Long matricula) {
        Optional<Estudante> obj = repository.findById(matricula);
        return obj.orElseThrow(() -> new NullPointerException("Estudante não encontrado"));
    }

    public EstudanteDTO insert(EstudanteDTO dto) {
        Estudante estudante = new Estudante(
                null, dto.getNome(), dto.getSobrenome()
        );
        estudante = repository.save(estudante);
        return new EstudanteDTO(estudante);
    }

    public void delete(Long matricula) {
        findByMatricula(matricula);
        repository.deleteById(matricula);
    }

}

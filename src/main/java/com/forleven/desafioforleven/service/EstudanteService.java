package com.forleven.desafioforleven.service;

import com.forleven.desafioforleven.exception.EntityNotFound;
import com.forleven.desafioforleven.model.dto.EstudanteRequest;
import com.forleven.desafioforleven.model.dto.EstudanteResponse;
import com.forleven.desafioforleven.model.entity.EstudanteEntity;
import com.forleven.desafioforleven.repository.EstudanteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudanteService {

    private final EstudanteRepository repository;

    public EstudanteService(EstudanteRepository repository) {
        this.repository = repository;
    }

    public List<EstudanteResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(EstudanteResponse::valueOf)
                .collect(Collectors.toList());
    }

    public EstudanteResponse findByMatricula(Long matricula) {
        return repository.findById(matricula)
                .map(EstudanteResponse::valueOf)
                .orElseThrow(() -> new EntityNotFound("Estudante não encontrado"));
    }

    public EstudanteResponse insert(EstudanteRequest estudanteRequest) {
        EstudanteEntity estudanteEntity = EstudanteEntity.builder()
                .nome(estudanteRequest.getNome())
                .sobrenome(estudanteRequest.getSobrenome())
                .telefones(estudanteRequest.getTelefones())
                .build();

        EstudanteEntity newEstudante = repository.save(estudanteEntity);
        return new EstudanteResponse(newEstudante.getId());
    }

    public void update(Long matricula, EstudanteRequest estudanteRequest) {
        EstudanteEntity estudanteEntity = repository.findById(matricula)
                .orElseThrow(() -> new EntityNotFound("Estudante não encontrado"));

        //Fazer da mesma forma que o insert
        estudanteEntity.builder()
                .nome(estudanteEntity.getNome())
                .sobrenome(estudanteEntity.getSobrenome())
                .telefones(estudanteRequest.getTelefones())
                .build();

        repository.save(estudanteEntity);
    }

    public void delete(Long matricula) {
        repository.findById(matricula)
                .ifPresent(estudanteEntity -> repository.deleteById(estudanteEntity.getId()));
    }

}

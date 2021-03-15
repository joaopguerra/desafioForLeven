package com.forleven.desafioforleven.service;

import com.forleven.desafioforleven.exception.NotFoundException;
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
                .orElseThrow(NotFoundException::new);
    }

    public EstudanteResponse insert(EstudanteRequest estudanteRequest) {
        EstudanteEntity newEstudante = repository.save(EstudanteEntity.valueOf(estudanteRequest));
        return new EstudanteResponse(newEstudante.getId());
    }

    public void update(Long matricula, EstudanteRequest estudanteRequest) {
        EstudanteEntity estudanteEntity = repository.findById(matricula)
                .orElseThrow(NotFoundException::new);

        estudanteEntity.setNome(estudanteRequest.getNome());
        estudanteEntity.setSobrenome(estudanteRequest.getSobrenome());
        estudanteEntity.setTelefones(estudanteRequest.getTelefones());

        repository.save(estudanteEntity);
    }

    public void delete(Long matricula) {
        repository.findById(matricula)
                .ifPresent(estudanteEntity -> {
            repository.deleteById(estudanteEntity.getId());
        });
    }

}

package com.forleven.desafioforleven.services;

import com.forleven.desafioforleven.dto.EstudanteDTO;
import com.forleven.desafioforleven.entities.Estudante;
import com.forleven.desafioforleven.repositories.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository repository;

    @Transactional(readOnly = true)
    public List<EstudanteDTO> findAll() {
        List<Estudante> list = repository.findAll();
        return list.stream().map(est -> new EstudanteDTO(est)).collect(Collectors.toList());
    }

    public EstudanteDTO findByMatricula(Long matricula) {
        Optional<Estudante> est = repository.findById(matricula);
        Estudante estudante = est.orElseThrow(() -> new NullPointerException("Estudante não encontrado"));
        return new EstudanteDTO(estudante);
    }

    public EstudanteDTO insert(EstudanteDTO dto) {
        Estudante estudante = new Estudante(
                null, dto.getNome(), dto.getSobrenome()
        );
        estudante.setTelefones(dto.getTelefones());
        estudante = repository.save(estudante);
        return new EstudanteDTO(estudante);
    }

    @Transactional
    public EstudanteDTO update(Long matricula, EstudanteDTO estudanteDTO) {
        try {
            Estudante estudante = repository.getOne(matricula);

            estudante.setNome(estudanteDTO.getNome());
            estudante.setSobrenome(estudanteDTO.getSobrenome());
            estudante.setTelefones(estudanteDTO.getTelefones());

            estudante = repository.save(estudante);
            return new EstudanteDTO(estudante);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Estudante não encontrado");
        }
    }

    public void delete(Long matricula) {
        findByMatricula(matricula);
        repository.deleteById(matricula);
    }

}

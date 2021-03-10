package com.forleven.desafioForLeven.controllers;

import com.forleven.desafioForLeven.dto.EstudanteDTO;
import com.forleven.desafioForLeven.entities.Estudante;
import com.forleven.desafioForLeven.services.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteService service;

    @GetMapping
    public ResponseEntity<List<EstudanteDTO>> findAll() {
        List<EstudanteDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{matricula}")
    public ResponseEntity<EstudanteDTO> findById(@PathVariable Long matricula){
        Estudante estudante = service.findByMatricula(matricula);
        return ResponseEntity.ok().body(new EstudanteDTO(estudante));
    }

    @PostMapping
    public ResponseEntity<EstudanteDTO> insert(@RequestBody EstudanteDTO estudanteDTO) {
        estudanteDTO = service.insert(estudanteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{matricula}").buildAndExpand(estudanteDTO.getMatricula()).toUri();
        return ResponseEntity.created(uri).body(estudanteDTO);
    }

    @DeleteMapping(value = "/{matricula}")
    public ResponseEntity<Void> delete (@PathVariable Long matricula) {
        service.delete(matricula);
        return ResponseEntity.noContent().build();
    }

}

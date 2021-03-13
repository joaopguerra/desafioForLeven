package com.forleven.desafioforleven.controllers;

import com.forleven.desafioforleven.dto.EstudanteDTO;
import com.forleven.desafioforleven.services.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<EstudanteDTO> findByMatricula(@PathVariable Long matricula){
        EstudanteDTO estudanteDTO = service.findByMatricula(matricula);
        return ResponseEntity.ok().body((estudanteDTO));
    }

    @PostMapping
    public ResponseEntity<EstudanteDTO> insert(@Valid @RequestBody EstudanteDTO estudanteDTO) {
        estudanteDTO = service.insert(estudanteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{matricula}").buildAndExpand(estudanteDTO.getMatricula()).toUri();
        return ResponseEntity.created(uri).body(estudanteDTO);
    }

    @PutMapping(value = "/{matricula}")
    public ResponseEntity<EstudanteDTO> update(@PathVariable Long matricula, @RequestBody EstudanteDTO estudanteDTO) {
        estudanteDTO = service.update(matricula, estudanteDTO);
        return ResponseEntity.ok().body((estudanteDTO));
    }

    @DeleteMapping(value = "/{matricula}")
    public ResponseEntity<Void> delete (@PathVariable Long matricula) {
        service.delete(matricula);
        return ResponseEntity.noContent().build();
    }

}

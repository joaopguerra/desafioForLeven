package com.forleven.desafioforleven.controller;

import com.forleven.desafioforleven.model.dto.EstudanteRequest;
import com.forleven.desafioforleven.model.dto.EstudanteResponse;
import com.forleven.desafioforleven.service.EstudanteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/estudantes")
public class EstudanteController {

    private final EstudanteService service;

    public EstudanteController(EstudanteService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EstudanteResponse> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{matricula}")
    @ResponseStatus(HttpStatus.OK)
    public EstudanteResponse findByMatricula(@PathVariable Long matricula) {
        return service.findByMatricula(matricula);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody EstudanteRequest estudanteRequest, HttpServletResponse response) {
        EstudanteResponse estudanteResponse = service.insert(estudanteRequest);
        String uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{matricula}")
                .buildAndExpand(estudanteResponse.getMatricula())
                .toUriString();
        response.addHeader("location", uri);
    }

    @PutMapping(value = "/{matricula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long matricula, @RequestBody EstudanteRequest estudanteRequest) {
        service.update(matricula, estudanteRequest);
    }

    @DeleteMapping(value = "/{matricula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long matricula) {
        service.delete(matricula);
    }

}

package com.example.EntidadEstudiante;

import jdk.internal.loader.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
class EstudianteControlador {

    private final EstudianteRepositorio repositorio;
    private final EstudianteResourceAssembler assembler;

    public EstudianteControlador(EstudianteRepositorio repositorio, EstudianteResourceAssembler assembler) {
        this.repositorio = repositorio;
        this.assembler = assembler;
    }

    @GetMapping("/estudiantes")
    Resources<Resource<Student>> all() {

        List<Resource<Student>> estudiantes = repositorio.findAll().stream().map(assembler::toResource).collect(Collectors.toList());

        return new Resources<>(estudiantes,
                linkTo(methodOn(EstudianteControlador.class).all()).withSelfRel());
    }

    @PostMapping("/estudiantes")
    ResponseEntity<?> nuevoEstudiante(@RequestBody Student newStudent)
            throws URISyntaxException {
        Resource<Student> resource = assembler.toResource(repositorio.save(newStudent));

        return ResponseEntity.
                created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @GetMapping("/estudiantes{id}")
    Resource<Student> one(@PathVariable Long id) {

        Student estudiante = repositorio.findById(id)
                .orElseThrow(() -> new EstudianteNotFoundExcept(id));


        return assembler.toResource(estudiante);
    }

    @PutMapping("/estudiantes/{id}")
    ResponseEntity<?> reemplazarEstudiante(@RequestBody Student nuevoEstudiante, @PathVariable Long id)
            throws URISyntaxException {

        Student updateEstudiante = repositorio.findById(id)
                .map(estudiante -> {
                    estudiante.setNombre(nuevoEstudiante.getNombre());
                    estudiante.setApellido(nuevoEstudiante.getApellido());
                    estudiante.setNota(nuevoEstudiante.getNota());


                })
                .orElseGet(() -> {

                    nuevoEstudiante.setId(id);
                    return repositorio.save(nuevoEstudiante);

                });

        Resource<Student> resource = assembler.toResource(updateEstudiante);

        return ResponseEntity
                .created(new URI(resource.getId().expand()
                        .getHref())).body(resource);

    }


    @DeleteMapping("/estudiantes/{id}")
    ResponseEntity<?> borrarEstudiante(@PathVariable Long id) {

        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


package com.example.EntidadEstudiante;
import org.springframework.stereotype.Component;


import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
class EstudianteResourceAssembler implements ResourceAssembler<Student, Resource>
{
    @Override
    public Resource toResource(Student estudiante){

        return new Resource<>(estudiante,
                linkTo(methodOn(EstudianteControlador.class).one(estudiante.getId())).withSelfRel(),
                linkTo(methodOn(EstudianteControlador.class).all()).withRel("estudiantes"));



    }



}

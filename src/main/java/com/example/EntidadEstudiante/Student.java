package com.example.EntidadEstudiante;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity

class Student {
    private @Id @GeneratedValue Long id;
    private String nombre;
    private String apellido;
    private  String nota;

    Student()
    {}



    Student(String nombre,String apellido,String nota){
        this.nombre=nombre;
        this.apellido=apellido;
        this.nota=nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNota() {
        return nota;
    }
}

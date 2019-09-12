package com.example.EntidadEstudiante;

import jdk.internal.org.jline.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j

public class CargarDatabase {

    @Bean
    CommandLineRunner initDatabase(EstudianteRepositorio repositorio, OrdenadorRespositorio ordenadorRepo){
        return  args -> {
          log.info("Precarga"+repositorio.save(new Student("Carlos","Cabrera","5")));
          log.infor("Precarga"+repositorio.save(new Student("Lalo","Garza","8")));
          ordenadorRepo.save(new Order("Estudiante", Status.COMPLETED));
          ordenadorRepo.save(new Order("Apellido", Status.IN_PROGRESS));
          ordenadorRepo.findAll().forEach(order ->{
              log.info("Preloaded "+order);
          });
        };
    }
}

package com.example.springapp.repositories;

import com.example.springapp.entities.TituloPropiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TituloPropiedadRepository extends JpaRepository<TituloPropiedad, Integer> {
}

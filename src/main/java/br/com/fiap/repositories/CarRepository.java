package br.com.fiap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository<Cars> extends JpaRepository <Cars, Long> {
}

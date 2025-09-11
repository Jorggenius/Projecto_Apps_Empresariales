package com.schoolDays.noche_app.persistenceLayer.repository;

import com.schoolDays.noche_app.persistenceLayer.entity.EvaluacionEntity;
import com.schoolDays.noche_app.persistenceLayer.entity.InscripcionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripciónRepository extends CrudRepository<InscripcionEntity, Long> {
}

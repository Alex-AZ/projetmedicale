package com.m2i.demomedical.repository;

import com.m2i.demomedical.entities.RdvEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RdvRepository extends CrudRepository<RdvEntity, Integer> {
	
	@Query(value=" SELECT month(dateheure) as mois , year(dateheure) as annee , count(*) as nbre FROM rdv GROUP BY month(dateheure) , year(dateheure)", nativeQuery=true)
    List<Object> getRdvStats();
}

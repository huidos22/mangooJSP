package com.huidos.mangooo.repository;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.huidos.mangooo.model.GastoCorte;
import com.huidos.mangooo.model.Municipio;

public interface GastoCorteRepository extends PagingAndSortingRepository<GastoCorte, Short> {

	List<GastoCorte> findAllByMunicipio(Optional<Municipio> findFirst);

	
}

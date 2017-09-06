package com.huidos.mangooo.service;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huidos.mangooo.model.Estado;
import com.huidos.mangooo.repository.EstadoRepository;
@Service
public class EstadosService {
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Map<Short, String> getEstados(){
		
		Map<Short, String> mapEstados = new HashMap<>();
		Iterable<Estado> estados= estadoRepository.findAll();
		for (Estado estado : estados) {
			mapEstados.put(estado.getIdEstado(), estado.getNombre());
		}
		return mapEstados;
	}
}

package com.huidos.mangooo.repository;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.huidos.mangooo.model.Usuario;

@RepositoryRestResource
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {

	Usuario findByUserNameAndPassword(String username, String password);
	Usuario findByUserName(String username);

}

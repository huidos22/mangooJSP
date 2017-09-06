/**
 * 
 */
package com.huidos.mangooo.repository;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huidos.mangooo.model.Cliente;


public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Integer> {

}

/**
 * 
 */
package com.mailorderpharmacy.refillservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mailorderpharmacy.refillservice.model.RefillOrder;


@Repository
public interface RefillOrderRepository extends JpaRepository<RefillOrder, Long>{

}

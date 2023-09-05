package fi.projecttest.testProject.domain;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SupermarketRepository extends CrudRepository<Supermarket, Long> {

    List<Supermarket> findBySName(String sName);
    
}

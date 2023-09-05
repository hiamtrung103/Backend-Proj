package fi.projecttest.testProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, ProductPriceID>{
	
	List<ProductPrice> findByPrice(String sPrice);
	List<ProductPrice> findBySID(Supermarket sID);
	List<ProductPrice> findBySIDAndPID(Supermarket sID, ProductInfo pID);
	
}

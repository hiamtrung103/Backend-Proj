package fi.projecttest.testProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductInfoRepository extends CrudRepository<ProductInfo, Long>{

	List<ProductInfo> findByPName(String pName);
	List<ProductInfo> findByPBrand(String pBrand);
	List<ProductInfo> findByPNameAndPBrand(String pName, String pBrand);
	
}

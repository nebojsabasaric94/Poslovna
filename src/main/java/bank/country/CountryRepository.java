package bank.country;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {

	@Query("select c from Country c where CAST (c.id AS string) like :id and c.name like CONCAT(:name,'%') and c.country_code like CONCAT(:country_code,'%')")
	public List<Country> search(@Param("id")String id,@Param("name")String name,@Param("country_code")String country_code);
	
}
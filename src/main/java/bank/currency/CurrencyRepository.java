package bank.currency;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Long> {

	@Query("select c from Currency c where c.name like CONCAT(:name,'%') and CAST(c.country.id AS string) like :country_id and (c.domicilna = :domicilna or :domicilna is null) and c.official_code like CONCAT(:official_code,'%')")
	public List<Currency> search(@Param("name")String name,@Param("country_id")String country_id,@Param("official_code")String official_code,@Param("domicilna")Boolean domicilna);

	
	@Query("select c from Currency c where c.official_code like :official_code")
	public Currency findByCode(@Param("official_code")String official_code);
}
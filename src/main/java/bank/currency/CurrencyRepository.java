package bank.currency;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Long> {

	@Query("select c from Currency c where c.name like CONCAT(:name,'%') and CAST(c.id AS string) like :id and c.domicilna = :domicilna and c.official_code like CONCAT(:official_code,'%')")
	public List<Currency> search(@Param("name")String name,@Param("id")String id,@Param("official_code")String official_code,@Param("domicilna")boolean domicilna);
}
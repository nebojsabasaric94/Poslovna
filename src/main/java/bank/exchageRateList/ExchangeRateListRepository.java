package bank.exchageRateList;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ExchangeRateListRepository extends PagingAndSortingRepository<ExchangeRateList, Long> {

	@Query("select e from  ExchangeRateList e where CAST(e.commercialBankRate.id AS string) like :bank and e.date like CONCAT(:date,'%') and e.appliedBy like CONCAT(:appliedBy,'%') and CAST(e.numberOfExchangeRateList AS string) like :numberOfExchangeRateList)")
	public List<ExchangeRateList> search(@Param("bank")String bank,@Param("date")String date,@Param("numberOfExchangeRateList")String numberOfExchangeRateList,@Param("appliedBy")String appliedBy);
}
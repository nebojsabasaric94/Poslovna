package bank.currencyRate;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CurrencyRateRepository extends PagingAndSortingRepository<CurrencyRate, Long> {

	@Query("select cr from CurrencyRate cr where CAST(cr.id AS string) like :id and cr.baseCurrency.name like CONCAT(:baseCurrency,'%') and"
			+ " cr.accordingToCurrency.name like CONCAT(:accordingToCurrency,'%') and CAST(cr.currencyInList.id AS string) like :currencyInListId and"
			+ " cast(cr.buyingExchangeRate AS string) like CONCAT(:buyingExchangeRate,'%') and CAST(cr.sellingExchangeRate AS string) like CONCAT(:sellingExchangeRate,'%') and"
			+ " cast(cr.middleExchangeRate AS string) like CONCAT(:middleExchangeRate,'%')")
	public List<CurrencyRate> search(@Param("id")String id,@Param("baseCurrency")String baseCurrency,@Param("accordingToCurrency")String accordingToCurrency,@Param("currencyInListId")String currencyInListId
			,@Param("buyingExchangeRate")String buyingExchangeRate,@Param("sellingExchangeRate")String sellingExchangeRate,@Param("middleExchangeRate")String middleExchangeRate);
}
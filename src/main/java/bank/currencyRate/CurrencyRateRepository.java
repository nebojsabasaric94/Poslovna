package bank.currencyRate;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CurrencyRateRepository extends PagingAndSortingRepository<CurrencyRate, Long> {

	@Query("select cr from CurrencyRate cr where CAST(cr.baseCurrency.id AS string) like :baseCurrency and"
			+ " CAST(cr.accordingToCurrency.id AS string) like :accordingToCurrency and CAST(cr.currencyInList.id AS string) like :currencyInListId and"
			+ " CAST(cr.buyingExchangeRate AS string) like :buyingExchangeRate and CAST(cr.sellingExchangeRate AS string) like :sellingExchangeRate and"
			+ " CAST(cr.middleExchangeRate AS string) like :middleExchangeRate")
	public List<CurrencyRate> search(@Param("baseCurrency")String baseCurrency,@Param("accordingToCurrency")String accordingToCurrency,@Param("currencyInListId")String currencyInListId
			,@Param("buyingExchangeRate")String buyingExchangeRate,@Param("sellingExchangeRate")String sellingExchangeRate,@Param("middleExchangeRate")String middleExchangeRate);
}
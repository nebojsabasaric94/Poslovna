package bank.dailyAccountBalance;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DailyAccountBalanceRepository extends PagingAndSortingRepository<DailyAccountBalance, Long> {

	@Query("select d from DailyAccountBalance d where d.trafficDate like CONCAT(:trafficDate,'%') and CAST(d.previousState AS string) like CONCAT(:previousState,'%') and "
			+ " CAST(d.trafficToBenefit AS string) like CONCAT(:trafficToBenefit,'%') and CAST(d.trafficToTheBurden AS string) like CONCAT(:trafficToTheBurden,'%') and "
			+ " CAST(d.newState AS string) like CONCAT(:newState,'%') and CAST(d.legalEntityAccount.id AS string) like :legalEntityAccountId")
	public List<DailyAccountBalance> search(@Param("trafficDate")String trafficDate,@Param("previousState")String previousState,@Param("trafficToBenefit")String trafficToBenefit,
			@Param("trafficToTheBurden")String trafficToTheBurden,@Param("newState")String newState,@Param("legalEntityAccountId")String legalEntityAccountId);
}
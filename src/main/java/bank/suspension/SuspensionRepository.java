package bank.suspension;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SuspensionRepository extends PagingAndSortingRepository<Suspension, Long> {

	@Query("select s from Suspension s where CAST(s.legalEntityAccount.id AS string) like :accountId and s.transferToAccount like CONCAT(:transferToAccount,'%') and s.date like CONCAT(:date,'%')")
	public List<Suspension> search(@Param("accountId")String accountId,@Param("transferToAccount")String transferToAccount,@Param("date")String date);
}
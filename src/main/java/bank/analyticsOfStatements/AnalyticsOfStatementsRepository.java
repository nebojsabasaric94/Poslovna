package bank.analyticsOfStatements;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AnalyticsOfStatementsRepository extends PagingAndSortingRepository<AnalyticsOfStatements, Long> {

	@Query("select a from  AnalyticsOfStatements a where (a.debtor_originator like CONCAT(:debtor_originator,'%')  or (a.debtor_originator is null and :debtor_originator = ''))"
			+ " and (a.purposeOfPayment like CONCAT(:purposeOfPayment,'%') or (a.purposeOfPayment is null and :purposeOfPayment = ''))  and (a.creditor_recipient like CONCAT(:creditor_recipient,'%') or (a.creditor_recipient is null and :creditor_recipient = ''))"
			+ " and a.dateOfReceipt like CONCAT(:dateOfReceipt,'%') and (a.currencyDate like CONCAT(:currencyDate,'%') or (a.currencyDate is null and :currencyDate = ''))"
			+ "	and (a.debtorAccount like CONCAT(:debtorAccount,'%') or (a.debtorAccount is null and :debtorAccount = '')) and (CAST(a.modelAssigments AS string) like :modelAssigments or (a.modelAssigments is null and :modelAssigments = '%'))"
			+ " and (a.referenceNumberAssigments like CONCAT(:referenceNumberAssigments,'%') or (a.referenceNumberAssigments is null and :referenceNumberAssigments = ''))"
			+ " and (a.accountCreditor like CONCAT(:accountCreditor,'%') or (a.accountCreditor is null and :accountCreditor = ''))"
			+ " and (CAST(a.modelApproval AS string) like :modelApproval or (a.modelApproval is null and :modelApproval = '%')) and (a.referenceNumberCreditor like CONCAT(:referenceNumberCreditor,'%') or (a.referenceNumberCreditor is null and :referenceNumberCreditor = '')) and (a.emergency = :emergency or :emergency is null)"
			+ " and CAST(a.sum AS string) like :sum and (CAST(a.typeOfMistake AS string) like :typeOfMistake or(a.typeOfMistake is null and :typeOfMistake = '%'))  and (a.status like CONCAT(:status,'%') or (a.status is null and :status = ''))"
			+ " and (CAST(a.dailyAccountBalance.id AS string) like :dailyAccountBalance or (a.dailyAccountBalance.id is null and :dailyAccountBalance = '%')) and CAST(a.paymentType.id AS string) like :paymentType and CAST(a.place.id AS string) like :place"
			+ " and CAST(a.paymentCurrency.id AS string) like :paymentCurrency")
	public List<AnalyticsOfStatements> search(@Param("debtor_originator")String debtor_originator,@Param("purposeOfPayment")String purposeOfPayment,@Param("creditor_recipient")String creditor_recipient,
			@Param("dateOfReceipt")String dateOfReceipt,@Param("currencyDate")String currencyDate,@Param("debtorAccount")String debtorAccount,
			@Param("modelAssigments")String modelAssigments,@Param("referenceNumberAssigments")String referenceNumberAssigments,@Param("accountCreditor")String accountCreditor,
			@Param("modelApproval")String modelApproval,@Param("referenceNumberCreditor")String referenceNumberCreditor,@Param("emergency")Boolean emergency,
			@Param("sum")String sum,@Param("typeOfMistake")String typeOfMistake,@Param("status")String status,
			@Param("dailyAccountBalance")String dailyAccountBalance,@Param("paymentType")String paymentType,@Param("place")String place,
			@Param("paymentCurrency")String paymentCurrency);

	@Query("select a from AnalyticsOfStatements a where (a.debtorAccount like :brojRacuna or a.accountCreditor like :brojRacuna) and a.currencyDate = :date")
	public ArrayList<AnalyticsOfStatements> findByDateAndAccount(@Param("brojRacuna")String brojRacuna,@Param("date") Date date);

	@Query("select a from AnalyticsOfStatements a where a.accountCreditor like :brojRacuna and a.currencyDate BETWEEN  :startDate AND :endDate")
	public ArrayList<AnalyticsOfStatements> findIncomeForPeriod(@Param("startDate")Date startDate,@Param("endDate") Date endDate,@Param("brojRacuna")String brojRacuna);
	
	@Query("select a from AnalyticsOfStatements a where a.debtorAccount like :brojRacuna and a.currencyDate BETWEEN  :startDate AND :endDate")
	public ArrayList<AnalyticsOfStatements> findExpenseForPeriod(@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("brojRacuna") String brojRacuna);

	@Query("select a from AnalyticsOfStatements a where (a.debtorAccount like :brojRacuna or a.accountCreditor like :brojRacuna) and a.currencyDate BETWEEN  :startDate AND :endDate")	
	public ArrayList<AnalyticsOfStatements> findAllForPeriod(@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("brojRacuna") String brojRacuna);
	
}
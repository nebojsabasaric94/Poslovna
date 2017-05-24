package bank.analyticsOfStatements;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AnalyticsOfStatementsRepository extends PagingAndSortingRepository<AnalyticsOfStatements, Long> {

	@Query("select a from  AnalyticsOfStatements a where a.debtor_originator like CONCAT(:debtor_originator,'%') and a.purposeOfPayment like CONCAT(:purposeOfPayment,'%') and a.creditor_recipient like CONCAT(:creditor_recipient,'%')"
			+ " and a.dateOfReceipt like CONCAT(:dateOfReceipt,'%') and a.currencyDate like CONCAT(:currencyDate,'%') and a.debtorAccount like CONCAT(:debtorAccount,'%') "
			+ " and CAST(a.modelAssigments AS string) like :modelAssigments and a.referenceNumberAssigments like CONCAT(:referenceNumberAssigments,'%') and a.accountCreditor like CONCAT(:accountCreditor,'%')"
			+ " and CAST(a.modelApproval AS string) like :modelApproval and a.referenceNumberCreditor like CONCAT(:referenceNumberCreditor,'%') and a.emergency = :emergency "
			+ " and CAST(a.sum AS string) like :sum and (CAST(a.typeOfMistake AS string) like :typeOfMistake or(a.typeOfMistake is null and :typeOfMistake = '%'))  and a.status like CONCAT(:status,'%') "
			+ " and CAST(a.dailyAccountBalance.id AS string) like :dailyAccountBalance and CAST(a.paymentType.id AS string) like :paymentType and CAST(a.place.id AS string) like :place"
			+ " and CAST(a.paymentCurrency.id AS string) like :paymentCurrency")
	public List<AnalyticsOfStatements> search(@Param("debtor_originator")String debtor_originator,@Param("purposeOfPayment")String purposeOfPayment,@Param("creditor_recipient")String creditor_recipient,
			@Param("dateOfReceipt")String dateOfReceipt,@Param("currencyDate")String currencyDate,@Param("debtorAccount")String debtorAccount,
			@Param("modelAssigments")String modelAssigments,@Param("referenceNumberAssigments")String referenceNumberAssigments,@Param("accountCreditor")String accountCreditor,
			@Param("modelApproval")String modelApproval,@Param("referenceNumberCreditor")String referenceNumberCreditor,@Param("emergency")Boolean emergency,
			@Param("sum")String sum,@Param("typeOfMistake")String typeOfMistake,@Param("status")String status,
			@Param("dailyAccountBalance")String dailyAccountBalance,@Param("paymentType")String paymentType,@Param("place")String place,
			@Param("paymentCurrency")String paymentCurrency);
	
}
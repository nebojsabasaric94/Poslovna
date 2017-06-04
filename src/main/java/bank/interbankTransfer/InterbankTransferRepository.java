package bank.interbankTransfer;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface InterbankTransferRepository extends PagingAndSortingRepository<InterbankTransfer, Long> {

	@Query("select i from InterbankTransfer i where i.typeOfMessage like CONCAT(:typeOfMessage,'%') and i.date like CONCAT(:date,'%')"
			+ " and CAST(i.sum AS string) like CONCAT(:sum,'%') and CAST(i.bank.id AS string) like :bank and CAST(i.senderBank.id AS string) like :senderBank")
	public List<InterbankTransfer> search(@Param("typeOfMessage")String typeOfMessage,@Param("date")String date,@Param("sum")String sum,@Param("bank")String bank,@Param("senderBank")String senderBank);

	@Query("select i from InterbankTransfer i where i.date like CONCAT(:date,'%') and CAST(i.bank.id AS string) like :bank and CAST(i.senderBank.id AS string) like :senderBank"
			+ " and i.typeOfMessage like 'MT102' and i.processed = 'false'")
	public InterbankTransfer findByDateAndBanks(@Param("date")String date, @Param("senderBank")String senderBank,@Param("bank")String bank);
}
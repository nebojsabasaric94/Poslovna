package bank.legalEntityAccount;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LegalEntityAccountRepository extends CrudRepository<LegalEntityAccount, Long>{

	@Query("select a from LegalEntityAccount a where a.brojRacuna like CONCAT(:broj_racuna,'%') and a.datumOtvaranja like CONCAT(:datum_otvaranja,'%') and (a.vazeci = :vazeci or :vazeci is null)"
			+ " and CAST(a.client.id AS string) like :client_id and CAST(a.bank.id AS string) like :bank_id and CAST(a.currency.id AS string) like :currency_id")
	public List<LegalEntityAccount> search(@Param("broj_racuna")String broj_racuna,@Param("datum_otvaranja")String datum_otvaranja,@Param("vazeci")Boolean vazeci,@Param("client_id")String client_id,@Param("bank_id")String bank_id,@Param("currency_id")String currency_id);

	@Query("select a from LegalEntityAccount a where a.brojRacuna like :brojRacuna")
	public LegalEntityAccount findByAccountNumber(@Param("brojRacuna")String brojRacuna);

	@Query("select a from LegalEntityAccount a where a.bank.id = :id")
	public ArrayList<LegalEntityAccount> findByBank(@Param("id")Long id);

}

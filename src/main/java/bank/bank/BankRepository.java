package bank.bank;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BankRepository extends PagingAndSortingRepository<Bank, Long> {

	@Query("select b from Bank b where  CAST(b.id AS string) like :id and b.bankCode like CONCAT(:bankCode,'%') and b.pib like CONCAT(:pib,'%') and"
			+ " b.name like CONCAT(:name,'%') and b.address like CONCAT(:address,'%') and b.email like CONCAT(:email,'%') and b.web like CONCAT(:web,'%') and"
			+ " b.phone like CONCAT(:phone,'%') and b.fax like CONCAT(:fax,'%') and (b.bank = :bank or :bank is null)")
	public List<Bank> search(@Param("id")String id,@Param("bankCode")String bankCode,@Param("pib")String pib,@Param("name")String name,@Param("address")String address,@Param("email")String email,@Param("web")String web,@Param("phone")String phone,@Param("fax")String fax,@Param("bank")Boolean bank);
}
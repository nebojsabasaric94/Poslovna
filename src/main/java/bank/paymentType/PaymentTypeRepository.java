package bank.paymentType;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PaymentTypeRepository extends PagingAndSortingRepository<PaymentType, Long> {

	@Query("select t from PaymentType t where t.code  like CONCAT(:code,'%') and t.nameOfPaymentType like CONCAT(:name,'%')")
	public List<PaymentType> search(@Param("code")String code,@Param("name")String name);

	@Query("select t from PaymentType t where t.nameOfPaymentType like :name")
	public PaymentType findByName(@Param("name")String name);
	
}
package bank.bussinessActivityCode;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BusinessActivityCodeRepository extends CrudRepository<BusinessActivityCode, Long>{

	
	@Query("select bac from BusinessActivityCode bac where bac.code like CONCAT(:code,'%') and bac.name like CONCAT(:name,'%')")
	public List<BusinessActivityCode> search(@Param("code")String code,@Param("name")String name);
}

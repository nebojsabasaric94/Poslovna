package bank.client;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends CrudRepository<Client, Long>{

	@Query("select c from Client c where c.jmbg like CONCAT(:jmbg,'%') and c.firstName like CONCAT(:firstName,'%') and c.lastName like CONCAT(:lastName,'%')"
			+ " and c.address like CONCAT(:address,'%') and  CAST(c.residence.id AS string) like :place_id and c.typeOfClient like CONCAT(:typeOfClient,'%') and "
			+ " c.email like CONCAT(:email,'%') and c.phone like CONCAT(:phone,'%') and c.addressForStatements like CONCAT(:addressForStatements,'%') and (c.emailStatements = :emailStatements or :emailStatements is null)")
	public List<Client> search(@Param("firstName")String firstName,@Param("lastName")String lastName,@Param("address")String address,@Param("email")String email,
			@Param("phone")String phone,@Param("addressForStatements")String addressForStatements,@Param("jmbg")String jmbg,@Param("place_id")String place_id,
			@Param("typeOfClient")String typeOfClient,@Param("emailStatements")Boolean emailStatements);
	
	@Override
	@Query("select c from Client c where c.typeOfClient like 'Fizicko lice'")
	public List<Client> findAll();
	

}

package bank.legalEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LegalEntityRepository extends CrudRepository<LegalEntity, Long>{

	
	@Query("select c from LegalEntity c where c.naziv_klijenta like CONCAT(:naziv_klijenta,'%') and c.skraceni_naziv_klijenta like CONCAT(:skraceni_naziv_klijenta,'%')"
			+ " and c.fax like CONCAT(:fax,'%') and c.maticni_broj like CONCAT(:maticni_broj,'%') and c.pib like CONCAT(:pib,'%') and c.naziv_organa like CONCAT(:naziv_organa,'%')"
			+ " and CAST(c.businessActivityCode.id AS string) like :businessActivityCode and c.nadlezni_poreski_organ_za_klijenta like CONCAT(:nadlezni_poreski_organ_za_klijenta,'%')  "
			+ "and c.jmbg like CONCAT(:jmbg,'%') and c.firstName like CONCAT(:firstName,'%') and c.lastName like CONCAT(:lastName,'%')"
			+ " and c.address like CONCAT(:address,'%') and  CAST(c.residence.id AS string) like :place_id and c.typeOfClient like CONCAT(:typeOfClient,'%') and "
			+ " c.email like CONCAT(:email,'%') and c.phone like CONCAT(:phone,'%') and c.addressForStatements like CONCAT(:addressForStatements,'%') and (c.emailStatements = :emailStatements or :emailStatements is null)")
	public List<LegalEntity> search(@Param("naziv_klijenta")String naziv_klijenta,@Param("skraceni_naziv_klijenta")String skraceni_naziv_klijenta,
			@Param("fax")String fax,@Param("maticni_broj")String maticni_broj,@Param("pib")String pib,@Param("naziv_organa")String naziv_organa,
			@Param("businessActivityCode")String businessActivityCode,@Param("nadlezni_poreski_organ_za_klijenta")String nadlezni_poreski_organ_za_klijenta,
			@Param("firstName")String firstName,@Param("lastName")String lastName,@Param("address")String address,@Param("email")String email,
			@Param("phone")String phone,@Param("addressForStatements")String addressForStatements,@Param("jmbg")String jmbg,@Param("place_id")String place_id,
			@Param("typeOfClient")String typeOfClient,@Param("emailStatements")Boolean emailStatements);	
}

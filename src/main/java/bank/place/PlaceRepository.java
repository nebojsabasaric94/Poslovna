package bank.place;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends PagingAndSortingRepository<Place, Long> {

	@Query("select p from Place p where CAST(p.id AS string) like :id and p.name like CONCAT(:name,'%') and p.pttNumber like CONCAT(:pttNumber,'%') and p.country.name like CONCAT(:country_name,'%')")
	public List<Place> search(@Param("id")String id,@Param("name")String name,@Param("pttNumber")String ptt,@Param("country_name")String country_name);
}
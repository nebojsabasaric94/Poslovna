package bank.place;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends PagingAndSortingRepository<Place, Long> {

	@Query("select p from Place p where  p.name like CONCAT(:name,'%') and p.pttNumber like CONCAT(:pttNumber,'%') and CAST(p.country.id AS string) like :country_id")
	public List<Place> search(@Param("name")String name,@Param("pttNumber")String ptt,@Param("country_id")String country_id);

	@Query("select p from Place p where  p.name like :name")
	public Place findByName(@Param("name")String name);
}
package bank.itemTransfer;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ItemTransferRepository extends PagingAndSortingRepository<ItemTransfer, Long> {

	
	@Query("select i from ItemTransfer i where CAST(i.analyticsOfStatements.itemNumber AS string) like :analyticsOfStatements and CAST(i.interbankTransfer.id AS string) like :interbankTransfer")
	public List<ItemTransfer> search(@Param("analyticsOfStatements")String analyticsOfStatements,@Param("interbankTransfer")String interbankTransfer);
	
}
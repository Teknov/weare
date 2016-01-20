package ph.teknov.weare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ph.teknov.weare.domain.Person;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Kenneth Mandawe
 *         Date: 1/20/2016
 *         Time: 7:50 PM
 */
@Repository
public interface PeopleRepository extends PagingAndSortingRepository<Person, Integer> {
    List<Person> findByLastName(@Param("lastName") String lastName);
}

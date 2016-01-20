package ph.teknov.weare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ph.teknov.weare.domain.Person;
import ph.teknov.weare.repository.PeopleRepository;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Kenneth Mandawe
 *         Date: 1/20/2016
 *         Time: 7:48 PM
 */
@RestController
@RequestMapping("people")
public class PeopleController {

    private static final Logger LOG = LoggerFactory.getLogger(PeopleController.class);

    private final PeopleRepository peopleRepository;

    @Inject
    public PeopleController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @RequestMapping
    public Iterable<Person> findAll(@RequestParam Optional<String> lastName) {
        if (lastName.isPresent()) {
            LOG.info("Searching by lastName ...");
            return peopleRepository.findByLastName(lastName.get());
        }
        LOG.info("Searching for all ...");
        return peopleRepository.findAll();
    }
}

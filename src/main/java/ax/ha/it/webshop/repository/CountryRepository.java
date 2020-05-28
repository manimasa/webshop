package ax.ha.it.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ax.ha.it.webshop.model.Country;


public interface CountryRepository extends JpaRepository<Country, Long> {

}

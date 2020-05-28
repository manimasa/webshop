package ax.ha.it.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ax.ha.it.webshop.model.Country;
import ax.ha.it.webshop.repository.CountryRepository;

@Service
@Transactional(readOnly=true)
public class CountryService {
	
	@Autowired
	private CountryRepository countryDao;

	@Transactional
	public Country saveCountry(Country country) {
		return countryDao.save(country);
	}

}
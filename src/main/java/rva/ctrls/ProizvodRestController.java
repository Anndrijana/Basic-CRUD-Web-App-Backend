package rva.ctrls;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Proizvod;
import rva.repository.ProizvodRepository;

@Api(tags = {"Proizvod CRUD operacije"})
@RestController
@CrossOrigin
public class ProizvodRestController {
	
	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraca kolekciju svih proizvoda iz baze podataka")
	@GetMapping("proizvod")
	public Collection<Proizvod> getProizvod() {
		return proizvodRepository.findAll();
	}
	
	@ApiOperation(value = "Vraca proizvod iz baze podataka ciji je ID prosledjen kao path varijabla")
	@GetMapping("proizvod/{id}")
	public Proizvod getProizvod(@PathVariable("id") Integer id) {
		return proizvodRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraca kolekciju proizvoda koji u nazivu sadrze string prosledjen kao path varijabla")
	@GetMapping("proizvodNaziv/{naziv}")
	public Collection<Proizvod> getProizvodByNaziv(@PathVariable("naziv") String naziv) {
	return proizvodRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Upisuje proizvod u bazu podataka")
	@PostMapping("proizvod")
	public ResponseEntity<Proizvod> insertProizvod(@RequestBody Proizvod proizvod) {
		if(!proizvodRepository.existsById(proizvod.getId())) {
			proizvodRepository.save(proizvod);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@ApiOperation(value = "Modifikuje proizvod u bazi podataka")
	@PutMapping("proizvod")
	public ResponseEntity<Proizvod> updateProizvod(@RequestBody Proizvod proizvod) {
		if (!proizvodRepository.existsById(proizvod.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		proizvodRepository.save(proizvod);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Brise proizvod iz baze podataka ciji je ID prosledjen kao path varijabla")
	@Transactional
	@DeleteMapping("proizvod/{id}")
	public ResponseEntity<Proizvod> deleteProizvod(@PathVariable ("id") Integer id){
		
		if(!proizvodRepository.existsById(id))
			return new ResponseEntity<Proizvod>(HttpStatus.NO_CONTENT);
		jdbcTemplate.execute("delete from stavka_racuna where proizvod = "+id);
		proizvodRepository.deleteById(id);
		
		/*if (id == -100) {
			jdbcTemplate.execute("INSERT INTO proizvod (\"id\", \"naziv\", \"proizvodjac\") "
					+ "VALUES ('-100', 'Naziv Proizvod', '5') ");
		}*/	
		
		return new ResponseEntity<Proizvod>(HttpStatus.OK);
	}
}

/*Problem - s obzirom da ne radi ovaj deo sa if i -100, moram svaki put da u pg adminu pre nego pokrenem*/
/*testove da ubacim proizvod sa id-jem -100; u suprotnom s obzirom da mi se -100 uvek obrise update za -100*/
/*nece raditi*/
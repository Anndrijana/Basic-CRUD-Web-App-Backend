package rva.ctrls;
import java.util.Collection;
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
import rva.jpa.Proizvodjac;
import rva.repository.ProizvodjacRepository;

@Api(tags = {"Proizvodjac CRUD operacije"})
@RestController
@CrossOrigin
public class ProizvodjacRestController {

	@Autowired
	private ProizvodjacRepository proizvodjacRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraca kolekciju svih proizvodjaca iz baze podataka")
	@GetMapping("proizvodjac")
	public Collection<Proizvodjac> getProizvodjac() {
		return proizvodjacRepository.findAll();
	}
	
	@ApiOperation(value = "Vraca proizvodjaca iz baze podataka ciji je ID prosledjen kao path varijabla")
	@GetMapping("proizvodjac/{id}")
	public Proizvodjac getProizvodjac(@PathVariable("id") Integer id) {
		return proizvodjacRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraca kolekciju proizvodjaca koji u nazivu sadrze string prosledjen kao path varijabla")
	@GetMapping("proizvodjacNaziv/{naziv}")
	public Collection<Proizvodjac> getProizvodjacByNaziv(@PathVariable("naziv") String naziv) {
	return proizvodjacRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Upisuje proizvodjaca u bazu podataka")
	@PostMapping("proizvodjac")
	public ResponseEntity<Proizvodjac> insertProizvodjac(@RequestBody Proizvodjac proizvodjac) {
		if(!proizvodjacRepository.existsById(proizvodjac.getId())) {
			proizvodjacRepository.save(proizvodjac);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@ApiOperation(value = "Modifikuje proizvodjaca u bazi podataka")
	@PutMapping("proizvodjac")
	public ResponseEntity<Proizvodjac> updateProizvodjac(@RequestBody Proizvodjac proizvodjac) {
		if (!proizvodjacRepository.existsById(proizvodjac.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		proizvodjacRepository.save(proizvodjac);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Brise proizvodjaca iz baze podataka ciji je ID prosledjen kao path varijabla")
	@DeleteMapping("proizvodjac/{id}")
	public ResponseEntity<Proizvodjac> deleteProizvodjac(@PathVariable("id") Integer id) {
		if (!proizvodjacRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		proizvodjacRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute(" INSERT INTO \"proizvodjac\" (\"id\", \"naziv\", \"adresa\", \"kontakt\")"
					+ "VALUES (-100, 'Naziv Proizvodjac', 'Adresa Proizvodjac', 'Kontakt Proizvodjac')");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
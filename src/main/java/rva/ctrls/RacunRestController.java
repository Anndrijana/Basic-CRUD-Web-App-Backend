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
import rva.jpa.Racun;
import rva.repository.RacunRepository;

@Api(tags = {"Racun CRUD operacije"})
@RestController
@CrossOrigin
public class RacunRestController {
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraca kolekciju svih racuna iz baze podataka")
	@GetMapping("racun")
	public Collection<Racun> getRacun() {
		return racunRepository.findAll();
	}
	
	@ApiOperation(value = "Vraca racun iz baze podataka ciji je ID prosledjen kao path varijabla")
	@GetMapping("racun/{id}")
	public Racun getRacun(@PathVariable("id") Integer id) {
		return racunRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraca kolekciju racuna koji u nazivu sadrze string prosledjen kao path varijabla")
	@GetMapping("racunNP/{nacinPlacanja}")
	public Collection<Racun> getRacunByNacinPlacanja(@PathVariable("nacinPlacanja") String nacin_placanja) {
	return racunRepository.findByNacinPlacanjaContainingIgnoreCase(nacin_placanja);
	}
	
	@ApiOperation(value = "Upisuje racun u bazu podataka")
	@PostMapping("racun")
	public ResponseEntity<Racun> insertRacun(@RequestBody Racun racun) {
		if(!racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	
	@ApiOperation(value = "Modifikuje racun u bazi podataka")
	@PutMapping("racun")
	public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun) {
		if (!racunRepository.existsById(racun.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		racunRepository.save(racun);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Brise racun iz baze podataka ciji je ID prosledjen kao path varijabla")
	@DeleteMapping("racun/{id}")
	public ResponseEntity<Racun> deleteRacun(@PathVariable("id") Integer id) {
		if (!racunRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		racunRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute(" INSERT INTO \"racun\"(\"id\", \"datum\", \"nacin_placanja\") VALUES (-100, to_date('11.11.2011.', 'dd.mm.yyyy.'), 'Nacin Placanja Racun') ");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
package rva.ctrls;
import java.math.BigDecimal;
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
import rva.jpa.StavkaRacuna;
import rva.repository.RacunRepository;
import rva.repository.StavkaRacunaRepository;

@Api(tags = {"Stavka Racuna CRUD operacije"})
@RestController
@CrossOrigin
public class StavkaRacunaRestController {
	
	@Autowired
	private StavkaRacunaRepository stavkaRacunaRepository;
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraca kolekciju svih stavki racuna iz baze podataka")
	@GetMapping("stavkaRacuna")
	public Collection<StavkaRacuna> getStavkaRacuna() {
		return stavkaRacunaRepository.findAll();
	}
	
	@ApiOperation(value = "Vraca stavku racuna iz baze podataka ciji je ID prosledjen kao path varijabla")
	@GetMapping("stavkaRacuna/{id}")
	public ResponseEntity<StavkaRacuna> getStavkaRacuna(@PathVariable("id") Integer id){
		StavkaRacuna stavkaRacuna = stavkaRacunaRepository.getOne(id);
		return new ResponseEntity<StavkaRacuna>(stavkaRacuna, HttpStatus.OK);
	}
	
	@GetMapping("stavkeZaRacunId/{id}")
	public Collection<StavkaRacuna> stavkaPoRacunuId(@PathVariable("id") int id){
		Racun r = racunRepository.getOne(id);
		return stavkaRacunaRepository.findByRacun(r);
	}
	
	@ApiOperation(value = "Vraca kolekciju stavki racuna koji u nazivu sadrze string prosledjen kao path varijabla")
	@GetMapping("stavkaRacunaCena/{cena}")
	public Collection<StavkaRacuna> getStavkaPorudzbineCena(@PathVariable("cena") BigDecimal cena){
		return stavkaRacunaRepository.findByCenaLessThanOrderById(cena);
	}
	
	@ApiOperation(value = "Upisuje stavku racuna u bazu podataka")
	@PostMapping("stavkaRacuna")
	public ResponseEntity<StavkaRacuna> insertStavkaRacuna(@RequestBody StavkaRacuna stavkaRacuna) {
		if(!stavkaRacunaRepository.existsById(stavkaRacuna.getId())) {
			stavkaRacunaRepository.save(stavkaRacuna);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@ApiOperation(value = "Modifikuje stavku racuna u bazi podataka")
	@PutMapping("stavkaRacuna")
	public ResponseEntity<StavkaRacuna> updateStavkaRacuna(@RequestBody StavkaRacuna stavkaRacuna) {
		if (!stavkaRacunaRepository.existsById(stavkaRacuna.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		stavkaRacunaRepository.save(stavkaRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Brise stavku racuna iz baze podataka ciji je ID prosledjen kao path varijabla")
	@DeleteMapping("stavkaRacuna/{id}")
	public ResponseEntity<StavkaRacuna> deleteStavkaRacuna(@PathVariable("id") Integer id){
		
		if(!stavkaRacunaRepository.existsById(id))
			return new ResponseEntity<StavkaRacuna>(HttpStatus.NO_CONTENT);
		stavkaRacunaRepository.deleteById(id);
		
		if (id == -100) {  
            jdbcTemplate.execute("INSERT INTO stavka_racuna (\"id\", \"redni_broj\", \"kolicina\", \"jedinica_mere\", \"cena\", \"racun\", \"proizvod\") "
                    + "VALUES ('-100', '100', '1', 'komad', '1', '1', '1')");
        }
		
		return new ResponseEntity<StavkaRacuna>(HttpStatus.OK);
	} 
	
}

package com.lojavirtual.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lojavirtual.model.Pessoa;
import com.lojavirtual.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaResources {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa> list() {
		return pessoaRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa save = pessoaRepository.save(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId())
				.toUri();

		return ResponseEntity.created(uri).body(save);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Integer id, @RequestBody Pessoa pessoa) {
		Optional<Pessoa> pessoaBanco = pessoaRepository.findById(id);
		BeanUtils.copyProperties(pessoa, pessoaBanco.get(), "id");
		pessoaRepository.save(pessoaBanco.get());

		return ResponseEntity.ok(pessoa);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		pessoaRepository.deleteById(id);
	}

}

package xyz.hrkami.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.hrkami.app.entities.Endereco;
import xyz.hrkami.app.repositories.EnderecoRepository;
import xyz.hrkami.app.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository repo;

	public Endereco find(Integer id) {

		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto nao encontrado ID : " + id + " , Tipo :" + Endereco.class.getName()));

	}
}

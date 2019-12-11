package br.com.sefaz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Teste {
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.
			      createEntityManagerFactory( "crud-clientes" );
			      EntityManager entitymanager = emfactory.
			      createEntityManager( );
			      entitymanager.getTransaction( ).begin( );
			      
		Cliente cliente = new Cliente();
		cliente.setEmail("victor@.com");
		cliente.setNome("victor");
		cliente.setSenha("123");
		
		Telefone telefone1 = new Telefone();
		telefone1.setDdd(333);
		telefone1.setNumero("99999");
		telefone1.setTipo("casa");
		
		Telefone telefone2 = new Telefone();
		telefone2.setDdd(444);
		telefone2.setNumero("99999");
		telefone2.setTipo("casa");
		entitymanager.persist(telefone1);
		entitymanager.persist(telefone2);
		List<Telefone> telefones = new ArrayList<>();
		telefones.add(telefone1);
		telefones.add(telefone2);
		
		cliente.setTelefones(telefones);
		
		entitymanager.persist(cliente);
		
		entitymanager.getTransaction( ).commit( );
	      entitymanager.close( );
	      emfactory.close( );
	}
}

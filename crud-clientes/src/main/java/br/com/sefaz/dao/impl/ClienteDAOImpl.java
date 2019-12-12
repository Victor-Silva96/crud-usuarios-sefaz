package br.com.sefaz.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.sefaz.dao.ClienteDAO;
import br.com.sefaz.model.Cliente;

public class ClienteDAOImpl implements ClienteDAO {
	
	EntityManagerFactory emfactory = Persistence.
		      createEntityManagerFactory( "crud-clientes" );
		      EntityManager entitymanager = emfactory.
		      createEntityManager();

	@Override
	public Cliente getClienteAutenticado(String email, String senha) {
		try {
		Cliente cliente = (Cliente) entitymanager.createQuery("from Cliente where email= :email and senha= :senha")
		.setParameter("email", email)
		.setParameter("senha", senha).getSingleResult();
		return cliente;
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public boolean alterarCliente(Cliente cliente) {
		try {
		entitymanager.getTransaction( ).begin( );
		entitymanager.merge(cliente);
		entitymanager.getTransaction().commit();
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deletarCliente(Cliente cliente) {
		try {
		entitymanager.getTransaction().begin();
		entitymanager.remove(cliente);
		entitymanager.getTransaction().commit();
		return true;
		}catch(Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listarClientes() {
		try {
		List<Cliente> clientes = entitymanager.createQuery("from Cliente",Cliente.class).getResultList();
		return clientes;
		}catch(Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public boolean inserirCliente(Cliente cliente) {
		try {
			entitymanager.getTransaction().begin();
			entitymanager.persist(cliente);
			entitymanager.getTransaction().commit();
			return true;
		} catch(Exception e) {
			return false;
		}

	}

}

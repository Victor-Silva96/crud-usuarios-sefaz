package br.com.sefaz.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import br.com.sefaz.dao.ClienteDAO;
import br.com.sefaz.model.Cliente;

@Stateful
public class ClienteDAOImpl implements ClienteDAO {
	@Inject
	private EntityManager entitymanager;

	@Override
	public Cliente getClienteAutenticado(String email, String senha) {
		try {
			return (Cliente) entitymanager.createQuery("from Cliente where email= :email and senha= :senha")
					.setParameter("email", email).setParameter("senha", senha).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public boolean alterarCliente(Cliente cliente) {
		try {
			entitymanager.merge(cliente);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deletarCliente(Cliente cliente) {
		try {
			entitymanager.remove(cliente);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Cliente> listarClientes() {
		try {
			return entitymanager.createQuery("from Cliente", Cliente.class).getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public boolean inserirCliente(Cliente cliente) {
		try {
			entitymanager.persist(cliente);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}

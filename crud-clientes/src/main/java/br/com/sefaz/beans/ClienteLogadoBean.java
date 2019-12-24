package br.com.sefaz.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sefaz.bo.ClienteBO;
import br.com.sefaz.model.Cliente;
import br.com.sefaz.session.Session;

@Named(value = "clienteLogado")
@RequestScoped
public class ClienteLogadoBean {
	@Inject
	private ClienteBO clienteBO;
	
	private String email;
	private String senha;
	@Inject
	FacesContext context;
	
	public Cliente getUser() {
	       return (Cliente) Session.getInstance().getAttribute("cliente");
	    }
	
	public String fazerLogin() {
		Cliente cliente = clienteBO.getUsuarioLogado(email, senha);
		
		if(cliente == null) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Cliente nao encontrado"));
			context.validationFailed();
			return "";
		}
		
		Session.getInstance().setAttribute("cliente", cliente);
		return "/home/home-page.xhtml?faces-redirect=true";
	}
	
	public  String fazerLogout() {
		Session.getInstance().encerrarSessao();
		return "/login.xhtml?faces-redirect=true";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}

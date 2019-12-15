package br.com.sefaz.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.sefaz.bo.ClienteBO;
import br.com.sefaz.bo.ClienteBOImpl;
import br.com.sefaz.model.Cliente;
import br.com.sefaz.session.Session;

@ManagedBean(name = "clienteLogado")
@RequestScoped
public class ClienteLogadoBean {
	private ClienteBO clienteBO = new ClienteBOImpl();
	
	private String email;
	private String senha;
	
	public Cliente getUser() {
	       return (Cliente) Session.getInstance().getAttribute("cliente");
	    }
	
	public String fazerLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
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

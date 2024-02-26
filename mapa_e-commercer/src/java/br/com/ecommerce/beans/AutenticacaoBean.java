/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.ecommerce.beans;

import br.com.ecommerce.repositorios.AdministradorRepositorio;
import br.com.ecommerce.repositorios.UsuarioRepositorio;
import br.com.ecommerce.repositorios.VendedorRepositorio;
import br.com.ecommerce.util.JPAUtil;
import br.com.ecommmerce.models.Administrador;
import br.com.ecommmerce.models.Usuario;
import br.com.ecommmerce.models.Vendedor;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Claudenir
 */
@ManagedBean(name = "autenticacaoBean")
@SessionScoped
public class AutenticacaoBean implements Serializable {

    private final UsuarioRepositorio usuarioRepositorio;
    private final AdministradorRepositorio administradorRepositorio;
    private final VendedorRepositorio vendedorRepositorio;

    private String nomeusuario;
    private String senhausuario;
    private String nomevendedor;
    private String senhavendedor;
    private String nomeadmin;
    private String senhaadmin;
    private String sessionId = "";
    private String sessionIdAdmin = "";
    private long sessionIdVendedor;
    

    public AutenticacaoBean() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        usuarioRepositorio = new UsuarioRepositorio(entityManager);
        administradorRepositorio = new AdministradorRepositorio(entityManager);
        vendedorRepositorio = new VendedorRepositorio(entityManager);
    }
    

    //    AUTENTICAR USUÁRIO
    public String autenticar() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        if (session.getAttribute("nomeUsuario") != null) {

            return null;

        } else {

            Usuario usuario = usuarioRepositorio.autenticarUsuario(nomeusuario);

            if (usuario != null) {

                String senhaArmazenada = usuario.getSenhahash();

                if (BCrypt.checkpw(senhausuario, senhaArmazenada)) {

                    if (usuario.getStatus().equals("BLOQUEADO")) {

                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acesso negado para este usuário. Por favor, entre em contato com a nossa equipe.", null));

                        return null;
                    }

                    sessionId = session.getId();

                    session.setAttribute("nomeUsuario", nomeusuario);

                    PrimeFaces.current().executeScript("if(PF('loginDialog').isVisible()) PF('loginDialog').hide();");

                    return "index?faces-redirect=true";

                } else {

                    return null;
                }
            }
        }
        return null;
    }
    

    // VERIFICA USUÁRIO LOGADO
    public Boolean verivicaUsuarioLogado() {

        limparImputs();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session == null || session.getAttribute("nomeUsuario") == null) {

            limparImputs();

            PrimeFaces.current().executeScript("PF('loginDialog').show()");

            return false;
        } else {

            return true;

        }
    }
    

    // AUTENTICAR VENDEDOR
    public String autenticarVendedor() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        if (session.getAttribute("nomeVendedor") != null) {

            return null;

        } else {

            Vendedor vendedor = vendedorRepositorio.autenticarVendedor(nomevendedor);

            if (vendedor != null) {

                String senhaArmazenada = vendedor.getSenhahash();

                if (BCrypt.checkpw(senhavendedor, senhaArmazenada)) {

                    if (vendedor.getStatus().equals("BLOQUEADO")) {

                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acesso negado para este usuário. Por favor, entre em contato com a nossa equipe.", null));

                        return null;
                    }

                    sessionId = session.getId();

                    setSessionIdVendedor(vendedor.getId());

                    session.setAttribute("nomeVendedor", nomevendedor);

                    session.setAttribute("idVendedor", sessionIdVendedor);

                    PrimeFaces.current().executeScript("if(PF('loginDialogVendedor').isVisible()) PF('loginDialogVendedor').hide();");

                    return "painelProdutos?faces-redirect=true";
                }

            } else {

                return null;
            }
        }
        return null;
    }
    

    //    VERIFICA VENDEDOR LOGADO
    public Boolean verificarVendedorLogado() {

        limparImputs();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session == null || session.getAttribute("nomeVendedor") == null) {

            limparImputs();

            PrimeFaces.current().executeScript("PF('loginDialogVendedor').show()");

            return false;

        } else {

            try {

                externalContext.redirect(externalContext.getRequestContextPath() + "/faces/painelProdutos.xhtml");
            } catch (IOException e) {

            }
        }

        return true;
    }
    

    // AUTENTICA ADMINISTRADOR
    public String autenticarAdmin() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        if (session.getAttribute("adminLogado") != null) {

            return null;

        } else {

            Administrador administrador = administradorRepositorio.autenticarAdministrador(nomeadmin);

            if (administrador != null) {

                String senhaArmazenada = administrador.getSenhahash();

                if (BCrypt.checkpw(senhaadmin, senhaArmazenada)) {

                    if (administrador.getStatus().equals("BLOQUEADO")) {

                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acesso negado para este usuário administrador. Por favor, entre em contato com a nossa equipe.", null));

                        return null;
                    }

                    sessionId = session.getId();

                    session.setAttribute("adminLogado", nomeadmin);

                    PrimeFaces.current().executeScript("if(PF('loginDialog').isVisible()) PF('loginDialog').hide();");

                    return "painelUsuarios?faces-redirect=true";

                } else {

                    return null;
                }
            }
        }
        return null;
    }
    

    // Verifica Administrador Logado
    public Boolean verivicaAdminLogado() {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session == null || session.getAttribute("adminLogado") == null) {
            PrimeFaces.current().executeScript("PF('settingsDialog').show()");
            return false;

        } else {

            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/faces/painelUsuarios.xhtml");
            } catch (IOException e) {

            }

            return true;
        }
    }

    
    public String cancelar() {
        PrimeFaces.current().executeScript("if(PF('loginDialog').isVisible()) PF('loginDialog').hide();");
        limparImputs();
        return "null";
    }

    public void limparImputs() {
        setNomeusuario("");
        setSenhausuario("");
    }
    

    // GETTERS E SETTERS
    public String getNomeusuario() {
        return nomeusuario;
    }

    public void setNomeusuario(String nomeusuario) {
        this.nomeusuario = nomeusuario;
    }

    public String getSenhausuario() {
        return senhausuario;
    }

    public void setSenhausuario(String senhausuario) {
        this.senhausuario = senhausuario;
    }

    public String getNomeadmin() {
        return nomeadmin;
    }

    public void setNomeadmin(String nomeadmin) {
        this.nomeadmin = nomeadmin;
    }

    public String getSenhaadmin() {
        return senhaadmin;
    }

    public void setSenhaadmin(String senhaadmin) {
        this.senhaadmin = senhaadmin;
    }

    public UsuarioRepositorio getUsuarioRepositorio() {
        return usuarioRepositorio;
    }

    public String getNomevendedor() {
        return nomevendedor;
    }

    public void setNomevendedor(String nomevendedor) {
        this.nomevendedor = nomevendedor;
    }

    public String getSenhavendedor() {
        return senhavendedor;
    }

    public void setSenhavendedor(String senhavendedor) {
        this.senhavendedor = senhavendedor;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionIdAdmin() {
        return sessionIdAdmin;
    }

    public void setSessionIdAdmin(String sessionIdAdmin) {
        this.sessionIdAdmin = sessionIdAdmin;
    }

    public long getSessionIdVendedor() {
        return sessionIdVendedor;
    }

    public void setSessionIdVendedor(long sessionIdVendedor) {
        this.sessionIdVendedor = sessionIdVendedor;
    }

}

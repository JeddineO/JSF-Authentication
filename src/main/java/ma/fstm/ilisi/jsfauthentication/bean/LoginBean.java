package ma.fstm.ilisi.jsfauthentication.bean;

import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import ma.fstm.ilisi.jsfauthentication.service.UserService;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private String login;
    private String password;

    private final UserService userService;

    public LoginBean() {
        this.userService = new UserService();
    }

    public LoginBean(String login, String password) {
        this.login = login;
        this.password = password;
        this.userService = new UserService();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String authenticate() {
        if (this.userService.authenticate(login, password) ) {
            return "home?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authenification echoué!", "Invalid credentials"));
            return "login";
        }
    }


    public void validerNom(FacesContext context, UIComponent toValidate, Object value) {
        String login = (String) value;
        if (login.length() < 5) {
            FacesMessage message = new FacesMessage("Le nom d'utilisatuer doit contenir 5 caracteres.");
            context.addMessage(toValidate.getClientId(context), message);
            ((UIInput) toValidate).setValid(false);
        }
    }


    public void validerMotDePasse(FacesContext context, UIComponent toValidate, Object value) {
        String password = (String) value;
        if (password.length() < 8) {
            FacesMessage message = new FacesMessage("Le mot de passe doit contenir 8 carateres.");
            context.addMessage(toValidate.getClientId(context), message);
            ((UIInput) toValidate).setValid(false);
        }
    }

}

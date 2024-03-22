package esportsclash.pratique.auth.application.useCases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.domain.viewModel.LoggedInUserViewModel;

public class LoginCommand implements Command<LoggedInUserViewModel> {
    private String emailAddress;
    private String password;

    public LoginCommand(){

    }

    public LoginCommand(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

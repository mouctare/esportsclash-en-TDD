package esportsclash.pratique.auth.application.useCases;


import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;

public class RegisterCommand implements Command<IdResponse> {
    private String emailAddress;
    private String password;

    public RegisterCommand(){

    }
    public RegisterCommand(String emailAdresse,String password){
        this.emailAddress = emailAdresse;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}

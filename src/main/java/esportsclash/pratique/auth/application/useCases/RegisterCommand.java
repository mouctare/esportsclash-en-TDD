package esportsclash.pratique.auth.application.useCases;


import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;

public class RegisterCommand implements Command<IdResponse> {
    private String emailAdresse;
    private String password;

    public RegisterCommand(){

    }
    public RegisterCommand(String emailAdresse,String password){
        this.emailAdresse = emailAdresse;
        this.password = password;
    }

    public String getEmailAdresse() {
        return emailAdresse;
    }

    public String getPassword() {
        return password;
    }
}

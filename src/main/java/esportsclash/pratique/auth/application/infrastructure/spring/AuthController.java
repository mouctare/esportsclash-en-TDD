package esportsclash.pratique.auth.application.infrastructure.spring;

import an.awesome.pipelinr.Pipeline;
import esportsclash.pratique.auth.application.useCases.LoginCommand;
import esportsclash.pratique.auth.application.useCases.RegisterCommand;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;
import esportsclash.pratique.auth.domain.viewModel.LoggedInUserViewModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Transactional
public class AuthController {
    private final Pipeline pipeline;

    public AuthController(Pipeline pipeline){
        this.pipeline = pipeline;
    }

    @PostMapping("/register")
    public ResponseEntity<IdResponse> register(@Valid @RequestBody RegisterDTO registerDTO){
       return new ResponseEntity(
               pipeline.send(new RegisterCommand(registerDTO.getEmailAddress(), registerDTO.getPassword())
               ),
               HttpStatus.CREATED
       );

    }

    @PostMapping("/login")
    public ResponseEntity<LoggedInUserViewModel> login(@Valid @RequestBody LoginDTO loginDTO){
        return  ResponseEntity.ok(
                pipeline.send(new LoginCommand(loginDTO.getEmailAddress(), loginDTO.getPassword())
                )
        );
    }
}

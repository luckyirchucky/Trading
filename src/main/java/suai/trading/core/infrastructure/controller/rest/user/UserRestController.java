package suai.trading.core.infrastructure.controller.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.query.ClientQueryService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final ClientQueryService clientQueryService;

    @PostMapping("/users")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid Client userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("ошибка", "Ошибка при вводе данных"));
        }
        if (!clientQueryService.saveUser(userForm)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("ошибка", "Пользователь уже существует"));
        }
        return ResponseEntity.ok(Map.of("redirect", "/index"));
    }
}

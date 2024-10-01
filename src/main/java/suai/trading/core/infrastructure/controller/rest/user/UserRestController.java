package suai.trading.core.infrastructure.controller.rest.user;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import suai.trading.core.security.currentclient.CurrentClient;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.ClientView;
import suai.trading.core.service.client.query.ClientQueryService;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final ClientQueryService clientQueryService;
    private final CurrentClient currentClient;

    @PostMapping
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid Client userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("ошибка", "Ошибка при вводе данных"));
        }
        if (!clientQueryService.saveUser(userForm)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("ошибка", "Пользователь уже существует"));
        }
        return ResponseEntity.ok(Map.of("redirect", "/index"));
    }

    @GetMapping("/current")
    public ResponseEntity<ClientView> getUser() {
        var clientId = currentClient.getId();
        var client = clientQueryService.getClientById(clientId).orElse(null);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        clientQueryService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        var users = clientQueryService.getAllClients();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody @Valid Client userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка при вводе данных");
        }
        if (!clientQueryService.updateClient(id, userForm)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден");
        }
        return ResponseEntity.ok("Данные пользователя обновлены");
    }
}

package suai.trading.core.infrastructure.controller.rest.admin;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import suai.trading.core.service.client.Client;
import suai.trading.core.service.client.query.ClientQueryService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AdminRestController {

    private final ClientQueryService clientQueryService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
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
            bindingResult.getAllErrors().forEach(System.out::println);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка при вводе данных");
        }
        if (!clientQueryService.updateClient(id, userForm)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден");
        }
        return ResponseEntity.ok("Данные пользователя обновлены");
    }
}

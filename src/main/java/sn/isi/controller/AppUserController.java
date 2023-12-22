package sn.isi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.AppUserDto;
import sn.isi.service.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/api/appuser")
public class AppUserController {
    private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AppUserDto>> listAppUsers() {
        logger.debug("Affichage de la liste des utilisateurs");
        return ResponseEntity.ok(appUserService.getAllUsers());
    }

    @PostMapping("/new")
    public ResponseEntity<AppUserDto> createUser(@RequestBody AppUserDto userDto) {
        AppUserDto createdUser = appUserService.createUser(userDto);
        return ResponseEntity.ok(createdUser); // ou ResponseEntity.created pour un statut HTTP 201
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<AppUserDto> showEditUserForm(@PathVariable int id) {
        AppUserDto userDto = appUserService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody AppUserDto userDto) {
        appUserService.updateUser(id, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        appUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

package sn.isi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.AppRolesDto;
import sn.isi.service.AppRolesService;

import java.util.List;

@RestController
@RequestMapping("/api/approles")
public class AppRolesController {

    private static final Logger logger = LoggerFactory.getLogger(AppRolesController.class);
    private final AppRolesService appRolesService;

    public AppRolesController(AppRolesService appRolesService) {
        this.appRolesService = appRolesService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AppRolesDto>> listAppRoles() {
        logger.debug("Affichage de la liste des rôles");
        return ResponseEntity.ok(appRolesService.getAppRoles());
    }

    @PostMapping("/new")
    public ResponseEntity<AppRolesDto> saveRole(@RequestBody AppRolesDto roleDto) {
        logger.debug("Enregistrement d'un nouveau rôle: {}", roleDto);
        AppRolesDto createdRole = appRolesService.createAppRoles(roleDto);
        return ResponseEntity.ok(createdRole); // ou ResponseEntity.created pour un statut HTTP 201
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<AppRolesDto> showEditRoleForm(@PathVariable int id) {
        logger.debug("Affichage des détails du rôle ID: {}", id);
        AppRolesDto roleDto = appRolesService.getAppRole(id);
        return ResponseEntity.ok(roleDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateRole(@PathVariable int id, @RequestBody AppRolesDto roleDto) {
        logger.debug("Mise à jour du rôle ID: {}", id);
        appRolesService.updateAppRoles(id, roleDto);
        return ResponseEntity.noContent().build(); // Statut HTTP 204
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        logger.debug("Suppression du rôle ID: {}", id);
        appRolesService.deleteAppRoles(id);
        return ResponseEntity.noContent().build();
    }
}

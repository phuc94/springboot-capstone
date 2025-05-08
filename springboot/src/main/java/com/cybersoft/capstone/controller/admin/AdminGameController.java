package com.cybersoft.capstone.controller.admin;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.service.interfaces.AdminGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class AdminGameController {

    private final AdminGameService adminGameService;

    public AdminGameController(AdminGameService adminGameService) {
        this.adminGameService = adminGameService;
    }

    @GetMapping
    public List<AdminGameDTO> getAllGames() {
        return adminGameService.getAllAdminGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminGameDTO> getGameById(@PathVariable int id) {
        return ResponseEntity.ok(adminGameService.getAdminGameById(id));
    }

    @PostMapping
    public ResponseEntity<AdminGameDTO> createGame(@RequestBody AdminGameDTO adminGameDTO) {
        return ResponseEntity.ok(adminGameService.createAdminGame(adminGameDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<AdminGameDTO> updateGame(@PathVariable int id, @RequestBody AdminGameDTO adminGameDTO) {
        return ResponseEntity.ok(adminGameService.updateAdminGame(id, adminGameDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameById(@PathVariable int id) {
        adminGameService.deleteAdminGameById(id);
        return ResponseEntity.noContent().build();
    }
}

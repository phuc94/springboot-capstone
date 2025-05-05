package com.cybersoft.capstone.controller.client;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.GameService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public BaseResponse<List<AdminGameDTO>> getAllGames() {
        List<AdminGameDTO> games = gameService.getAllGames();
        BaseResponse<List<AdminGameDTO>> response = new BaseResponse<>(200, "Lấy danh sách game thành công");
        response.setData(games);
        return response;
    }

    @GetMapping("/{id}")
    public BaseResponse<AdminGameDTO> getGameById(@Valid @PathVariable int id) {
        AdminGameDTO game = gameService.getGameById(id);
        BaseResponse<AdminGameDTO> response = new BaseResponse<>(200, "Lấy game thành công");
        response.setData(game);
        return response;
    }

    @PostMapping
    public BaseResponse<AdminGameDTO> createGame(@Valid @RequestBody AdminGameDTO gameDTO) {
        AdminGameDTO createdGame = gameService.createGame(gameDTO);
        BaseResponse<AdminGameDTO> response = new BaseResponse<>(201, "Tạo game thành công");
        response.setData(createdGame);
        return response;
    }

    @PostMapping("/{id}")
    public BaseResponse<AdminGameDTO> updateGame(@Valid @PathVariable int id, @RequestBody AdminGameDTO gameDTO) {
        AdminGameDTO updatedGame = gameService.updateGame(id, gameDTO);
        BaseResponse<AdminGameDTO> response = new BaseResponse<>(200, "Cập nhật game thành công");
        response.setData(updatedGame);
        return response;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteGameById(@Valid @PathVariable int id) {
        gameService.deleteGameById(id);
        return new BaseResponse<>(200, "Xóa game thành công");
    }

}

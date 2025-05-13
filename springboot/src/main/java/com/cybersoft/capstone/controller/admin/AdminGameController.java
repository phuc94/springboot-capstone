package com.cybersoft.capstone.controller.admin;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.BaseResponse;
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
    public BaseResponse<List<AdminGameDTO>> getAllGames() {
        List<AdminGameDTO> gameDTO = adminGameService.getAllAdminGames();
        BaseResponse<List<AdminGameDTO>> response = new BaseResponse<>(200, "Lấy danh sách game thành công!");
        response.setData(gameDTO);
        return response;
    }

    @GetMapping("/{id}")
    public BaseResponse<AdminGameDTO> getGameById(@PathVariable int id) {
        try {
            AdminGameDTO gameDTO = adminGameService.getAdminGameById(id);
            BaseResponse<AdminGameDTO> response = new BaseResponse<>(200, "Lấy game theo id thành công!");
            response.setData(gameDTO);
            return response;
        } catch (NotFoundException ex) {
            BaseResponse<AdminGameDTO> response = new BaseResponse<>(404, "Game không tồn tại!");
            response.setData(null);
            return response;
        }
    }

    @PostMapping
    public BaseResponse<AdminGameDTO> createGame(@RequestBody AdminGameDTO adminGameDTO) {
        try {
            AdminGameDTO gameDTO = adminGameService.createAdminGame(adminGameDTO);
            BaseResponse<AdminGameDTO> response = new BaseResponse<>(201, "Thêm game thành công!");
            response.setData(gameDTO);
            return response;
        } catch (NotFoundException ex) {
            BaseResponse<AdminGameDTO> response = new BaseResponse<>(500, "Thêm không thành công!");
            response.setData(null);
            return response;
        }
    }

    @PostMapping("/{id}")
    public BaseResponse<AdminGameDTO> updateGame(@PathVariable int id, @RequestBody AdminGameDTO adminGameDTO) {
        try {
            AdminGameDTO updateGame = adminGameService.updateAdminGame(id, adminGameDTO);
            BaseResponse<AdminGameDTO> response = new BaseResponse<>(200, "Update game thành công!");
            response.setData(updateGame);
            return response;
        } catch (NotFoundException ex) {
            BaseResponse<AdminGameDTO> response = new BaseResponse<>(500, "Update không thành công!");
            response.setData(null);
            return response;
        }
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteGameById(@PathVariable int id) {
        try {
            adminGameService.deleteAdminGameById(id);
            BaseResponse<Void> response = new BaseResponse<>(204, "Xóa game thành công");
            response.setData(null);  // Không trả dữ liệu trong trường hợp xóa thành công
            return response;
        } catch (Exception ex) {
            BaseResponse<Void> response = new BaseResponse<>(500, "Xóa game không thành công");
            response.setData(null);
            return response;
        }
    }
}

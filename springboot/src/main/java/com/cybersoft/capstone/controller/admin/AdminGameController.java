package com.cybersoft.capstone.controller.admin;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.request.SearchGameRequest;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.AdminGameService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/game")
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

    @GetMapping("/search")
    public BaseResponse<List<AdminGameDTO>> searchGames(@RequestBody SearchGameRequest request) {
        try {
            Page<AdminGameDTO> searchAdminGame = adminGameService.searchAdminGame(request);
            BaseResponse<List<AdminGameDTO>> response = new BaseResponse<>(200, "Tìm kiếm game thành công!");
            response.setData(searchAdminGame.getContent());
            return response;
        } catch (NotFoundException ex) {
            BaseResponse<List<AdminGameDTO>> response = new BaseResponse<>(404, "Không tìm thấy game nào!");
            response.setData(null);
            return response;
        }
    }

    @PostMapping
    public BaseResponse<AdminGameDTO> createGame(@RequestBody AdminGameDTO adminGameDTO) {
        try {
            adminGameDTO.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            adminGameDTO.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
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
            adminGameDTO.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
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
            adminGameService.softDeleteAdminGameById(id);
            BaseResponse<Void> response = new BaseResponse<>(204, "Xóa game thành công");
            response.setData(null);  // Không trả dữ liệu trong trường hợp xóa thành công
            return response;
        } catch (Exception ex) {
            BaseResponse<Void> response = new BaseResponse<>(500, "Xóa game không thành công");
            response.setData(null);
            return response;
        }
    }

    @PostMapping("/no-auth")
    public BaseResponse<AdminGameDTO> createGameNoAuth(@RequestBody AdminGameDTO adminGameDTO) {
        try {
            adminGameDTO.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            adminGameDTO.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            adminGameDTO.setAvgRating(5);
            return new OkResponse<AdminGameDTO>(adminGameService.createAdminGame(adminGameDTO));
        } catch (NotFoundException ex) {
            BaseResponse<AdminGameDTO> response = new BaseResponse<>(500, "Thêm không thành công!");
            response.setData(null);
            return response;
        }
    }
}

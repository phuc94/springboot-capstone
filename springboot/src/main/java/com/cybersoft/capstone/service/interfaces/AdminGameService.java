package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.payload.request.SearchGameRequest;
import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.AdminGameDTO;
import org.springframework.data.domain.Page;

public interface AdminGameService {
    public List<AdminGameDTO> getAllAdminGames();
    public AdminGameDTO getAdminGameById(int id);
    public AdminGameDTO createAdminGame(@Valid AdminGameDTO adminGameDTO);
    public AdminGameDTO updateAdminGame(int id, AdminGameDTO adminGameDTO);
    public void softDeleteAdminGameById(int id);
    public Page<AdminGameDTO> searchAdminGame(SearchGameRequest request);
}

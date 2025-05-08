package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.AdminGameDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface AdminGameService {
    public List<AdminGameDTO> getAllAdminGames();
    public AdminGameDTO getAdminGameById(int id);
    public AdminGameDTO createAdminGame(@Valid AdminGameDTO adminGameDTO);
    public AdminGameDTO updateAdminGame(int id, AdminGameDTO adminGameDTO);
    public void deleteAdminGameById(int id);
}

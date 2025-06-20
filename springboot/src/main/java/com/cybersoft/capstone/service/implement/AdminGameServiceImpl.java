package com.cybersoft.capstone.service.implement;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.dto.mapper.GameMapper;
import com.cybersoft.capstone.entity.GameDescription;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.entity.Medias;
import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.entity.Sales;
import com.cybersoft.capstone.entity.enums.MediaType;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.request.SearchGameRequest;
import com.cybersoft.capstone.repository.GameDescriptionRepository;
import com.cybersoft.capstone.repository.GameRepository;
import com.cybersoft.capstone.repository.PlatformRepository;
import com.cybersoft.capstone.repository.SaleRepository;
import com.cybersoft.capstone.service.interfaces.AdminGameService;
import com.cybersoft.capstone.service.interfaces.MediaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AdminGameServiceImpl implements AdminGameService {

    private final GameRepository gameRepository;
    private final GameDescriptionRepository gameDescriptionRepository;
    private final PlatformRepository platformRepository;
    private final SaleRepository saleRepository;
    private final MediaService mediaService;
    private final GameMapper gameMapper;

    public AdminGameServiceImpl(
        GameRepository gameRepository,
        GameDescriptionRepository gameDescriptionRepository,
        PlatformRepository platformRepository,
        SaleRepository saleRepository,
        GameMapper gameMapper,
        MediaService mediaService
    ) {
        this.gameRepository = gameRepository;
        this.gameDescriptionRepository = gameDescriptionRepository;
        this.platformRepository = platformRepository;
        this.saleRepository = saleRepository;
        this.gameMapper = gameMapper;
        this.mediaService = mediaService;
    }

    @Override
    public List<AdminGameDTO> getAllAdminGames() {
        return gameRepository.findAll()
            .stream().map(gameMapper::toAdminGameDTO).collect(Collectors.toList());
    }

    @Override
    public AdminGameDTO getAdminGameById(int id) {
        return gameRepository.findById(id)
                .map(gameMapper::toAdminGameDTO)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Transactional
    @Override
    public AdminGameDTO createAdminGame(AdminGameDTO adminGameDTO) {
        // Tìm platform dựa trên platformId từ DTO
        Platforms platform = platformRepository.findById(adminGameDTO.getPlatformId())
                .orElseThrow(() -> new RuntimeException("Platform not found"));

        // Tạo mới mô tả game (GameDescription)
        GameDescription gameDescription = new GameDescription();
        gameDescription.setDescription(adminGameDTO.getDescription()); // Set mô tả từ DTO
        gameDescriptionRepository.save(gameDescription);

        // Tạo mới game (Games)
        Games game = new Games();
        game.setTitle(adminGameDTO.getTitle());
        game.setPrice(adminGameDTO.getPrice());
        game.setStock(adminGameDTO.getStock());
        game.setPlatform(platform);
        game.setGameDescription(gameDescription); // Liên kết Game với GameDescription
        Sales sale = new Sales();
        sale.setId(1); // default sale
        game.setSale(sale);

        // Lưu game vào DB (Game sẽ được lưu vào bảng `games`)
        Games savedGame = gameRepository.save(game);

        Medias media = new Medias();
        media.setUrl(adminGameDTO.getMedia());
        media.setPrimary(true);
        media.setMedia_type(MediaType.IMAGE);
        media.setGame(savedGame);
        media = mediaService.createMedia(media);

        adminGameDTO.setId(savedGame.getId());
        adminGameDTO.setDescription(gameDescription.getDescription());

        // Trả về DTO đã được cập nhật thông tin
        return adminGameDTO;
    }

    @Transactional
    @Override
    public AdminGameDTO updateAdminGame(int id, AdminGameDTO adminGameDTO) {
        // Tìm game cần cập nhật
        Games game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // Tìm platform tương ứng
        Platforms platform = platformRepository.findById(adminGameDTO.getPlatformId())
                .orElseThrow(() -> new RuntimeException("Platform not found"));

        // Tìm sale dựa trên saleId từ DTO
        Sales sales = saleRepository.findById(adminGameDTO.getSaleId())
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        // Cập nhật dữ liệu cơ bản
        game.setTitle(adminGameDTO.getTitle());
        game.setPrice(adminGameDTO.getPrice());
        game.setStock(adminGameDTO.getStock());
        game.setPlatform(platform);
        game.setSale(sales);

        // Cập nhật phần mô tả (GameDescription)
        GameDescription description = game.getGameDescription();
        if (description == null) {
            // Nếu chưa có description, tạo mới
            description = new GameDescription();
            description.setDescription("Game chưa có mô tả.");
            gameDescriptionRepository.save(description);
            game.setGameDescription(description);
        } else {
            // Nếu có rồi, cập nhật mô tả hiện tại
            description.setDescription(adminGameDTO.getDescription());
            gameDescriptionRepository.save(description);
        }

        // Lưu game đã cập nhật
        Games updated = gameRepository.save(game);

        // Trả kết quả
        adminGameDTO.setId(updated.getId());
        adminGameDTO.setDescription(updated.getGameDescription().getDescription());
        return adminGameDTO;
    }

    @Transactional
    @Override
    public void softDeleteAdminGameById(int id) {
        gameRepository.findById(id)
            .ifPresentOrElse(game -> {
                game.setDeletedOn(Timestamp.valueOf(LocalDateTime.now()));
            }, () -> {throw new RuntimeException("Game not found");});
    }

    @Override
    public Page<AdminGameDTO> searchAdminGame(SearchGameRequest request) {
        Specification<Games> specification = (root, query, criteriaBuilder) -> {
            if (request.getTitle() != null && !request.getTitle().isEmpty()) {
                return criteriaBuilder.like(root.get("title"), "%" + request.getTitle() + "%");
            }
            return criteriaBuilder.conjunction();
        };
        Pageable pageable = PageRequest.of(request.getNumPage(), request.getPageSize());
        return gameRepository.findAll(specification, pageable)
                .map(gameMapper::toAdminGameDTO);
    }

}

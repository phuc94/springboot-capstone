// package com.cybersoft.capstone.controller.client;
//
// import jakarta.validation.Valid;
//
// import com.cybersoft.capstone.entity.GameDescription;
// import com.cybersoft.capstone.payload.response.BaseResponse;
// import com.cybersoft.capstone.service.interfaces.GameDescriptionService;
//
// import org.springframework.http.MediaType;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;
//
//
// @RestController
// @RequestMapping("/api/game_description")
// public class ClientGameDescriptionController {
//
//     private final GameDescriptionService gameDescriptionService;
//
//     public ClientGameDescriptionController(GameDescriptionService gameDescriptionService) {
//         this.gameDescriptionService = gameDescriptionService;
//     }
//
//     @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
//     @ResponseBody
//     public BaseResponse<GameDescription> getClientGameDescriptionById(@Valid @PathVariable int id) {
//         return gameDescriptionService.getGameDescriptionById(id);
//     }
// }
//
//

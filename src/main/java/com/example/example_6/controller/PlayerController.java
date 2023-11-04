package com.example.example_6.controller;


import com.example.example_6.exception.NotFoundException;
import com.example.example_6.request.PlayerRequest;
import com.example.example_6.response.*;
import com.example.example_6.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Player sinifinin api nesnesidir.
 * Responselarin statuslari -> 200 = "success" , 400 = "bad request"
 */
@RestController
@RequestMapping("players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService service;

    @GetMapping
    private ResponseEntity<BaseResponse<PlayerResponseList>> getPlayers() {
        try {
            return ResponseEntity.ok(BaseResponse.success
                    (new PlayerResponseList(service.findAll().getPlayerDtoList()), "Oyuncular Basariyla Getirildi"));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/teamId/{id}")
    private ResponseEntity<BaseResponse<PlayerResponseList>> getPlayersByTeamId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(BaseResponse.success
                    (new PlayerResponseList(service.findPlayersByTeamId(id).getPlayerDtoList()), "Oyuncular Basariyla Getirildi"));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/playerId/{id}")
    private ResponseEntity<BaseResponse<PlayerResponse>> getPlayerById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(BaseResponse.success
                    (PlayerResponse.toResponse(service.findById(Long.parseLong(id))), "Oyuncu Bulundu"));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    private ResponseEntity<BaseResponse<PlayerResponse>> save(@RequestBody PlayerRequest request) {
        try {
            return ResponseEntity.ok(BaseResponse.success(PlayerResponse.toResponse(service.save(request.toDto())), "Kayit Basarili"));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }

    @PutMapping("{id}")
    private ResponseEntity<BaseResponse<PlayerResponse>> update(@PathVariable(name = "id") String id, @RequestBody PlayerRequest request) {
        try {
            return ResponseEntity.ok(BaseResponse.success
                    (PlayerResponse.toResponse(service.update(Long.parseLong(id), request.toDto())), "Update Basarili"));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    private ResponseEntity<BaseResponse<Void>> delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok(BaseResponse.success(service.delete(Long.parseLong(id)), "Silme İslemi Basarili"));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }
}

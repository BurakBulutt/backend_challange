package com.example.example_6.controller;

import com.example.example_6.exception.NotFoundException;
import com.example.example_6.request.TeamRequest;
import com.example.example_6.response.BaseResponse;
import com.example.example_6.response.TeamResponse;
import com.example.example_6.response.TeamResponseList;
import com.example.example_6.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Team sinifinin api nesnesidir.
 * Responselarin statuslari -> 200 = "success" , 400 = "bad request"
 */
@RestController
@RequestMapping("teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService service;

    @GetMapping
    private ResponseEntity<BaseResponse<TeamResponseList>> getTeams() {
        try {
            return ResponseEntity.ok(BaseResponse.success
                    (new TeamResponseList(service.findAll().getTeamDtoList()), "Takimlar Basariyla Getirildi"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }

    @GetMapping("{id}")
    private ResponseEntity<BaseResponse<TeamResponse>> getTeamById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(BaseResponse.success(TeamResponse.toResponse(service.findById(id)), "Takim Bulundu"));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    private ResponseEntity<BaseResponse<TeamResponse>> save(@RequestBody TeamRequest request) {
        try {
            return ResponseEntity.ok(BaseResponse.success(TeamResponse.toResponse(service.save(request.toDto())), "Kayit Basarili"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }

    @PutMapping("{id}")
    private ResponseEntity<BaseResponse<TeamResponse>> update(@PathVariable String id, @RequestBody TeamRequest request) {
        try {
            return ResponseEntity.ok(BaseResponse.success(TeamResponse.toResponse(service.update(id, request.toDto())), "Update Basarili"));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    private ResponseEntity<BaseResponse<Void>> delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok(BaseResponse.success(service.delete(id), "Silme İslemi Basarili"));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(BaseResponse.error(e.getMessage()));
        }
    }
}

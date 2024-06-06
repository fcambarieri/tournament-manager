package com.fcctech.tournament.controller;

import com.fcctech.tournament.payload.request.BeltRequest;
import com.fcctech.tournament.payload.response.BeltCategory;
import com.fcctech.tournament.payload.response.EntityIdResponse;
import com.fcctech.tournament.security.UserPrincipal;
import com.fcctech.tournament.service.BeltService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/tournaments/{tournamentId}")
public class BeltController {

    @Autowired
    private BeltService beltService;

    @PostMapping("/belts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityIdResponse createBelt(@AuthenticationPrincipal UserPrincipal principal,
                                       @PathVariable("tournamentId") Long tournamentId,
                                       @RequestBody BeltRequest request) {
        com.fcctech.tournament.entity.BeltCategory category = beltService.createBelt(principal.getUserId(), tournamentId, request);
        return EntityIdResponse.builder().id(category.getId()).build();
    }

    @PutMapping("/belts/{beltId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateBelt(@AuthenticationPrincipal UserPrincipal principal,
                           @PathVariable("tournamentId") Long tournamentId,
                           @PathVariable("beltId") Long beltId,
                           @RequestBody BeltRequest request) {

        beltService.updateBelt(principal.getUserId(), tournamentId, beltId, request);
    }

    @GetMapping("/belts/{beltId}")
    @ResponseStatus(code = HttpStatus.OK)
    public BeltCategory getBelt(@PathVariable("tournamentId") Long tournamentId,
                                @PathVariable("beltId") Long beltId) {

        com.fcctech.tournament.entity.BeltCategory beltCategory = beltService.findTournamnetBelt(tournamentId, beltId);
        return BeltCategory.builder().id(beltId).name(beltCategory.getName()).build();
    }

    @GetMapping("/belts")
    @ResponseStatus(code = HttpStatus.OK)
    public List<BeltCategory> getBelt(@PathVariable("tournamentId") Long tournamentId) {

        List<com.fcctech.tournament.entity.BeltCategory> belts = beltService.findAllBeltsByTournamentId(tournamentId);
        return belts.stream().map(beltCategory -> BeltCategory.builder().name(beltCategory.getName()).id(beltCategory.getId()).build()).collect(Collectors.toList());
    }
}

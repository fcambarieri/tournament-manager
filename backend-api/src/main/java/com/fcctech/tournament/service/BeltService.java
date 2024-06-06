package com.fcctech.tournament.service;

import com.fcctech.tournament.entity.BeltCategory;
import com.fcctech.tournament.payload.request.BeltRequest;

import java.util.List;

public interface BeltService {

    BeltCategory createBelt(Long userId, Long tournament, BeltRequest request) ;

    void updateBelt(Long userId, Long tournamentId, Long beltId, BeltRequest request);

    BeltCategory findTournamnetBelt(Long tournamentId, Long beltId);

    List<BeltCategory> findAllBeltsByTournamentId(Long tournamentId);
}

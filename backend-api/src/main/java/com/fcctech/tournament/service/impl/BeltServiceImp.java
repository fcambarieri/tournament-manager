package com.fcctech.tournament.service.impl;

import com.fcctech.tournament.entity.BeltCategory;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.exception.TournamentNotFoundException;
import com.fcctech.tournament.payload.request.BeltRequest;
import com.fcctech.tournament.repository.BeltCategoryRepository;
import com.fcctech.tournament.repository.TournamentRepository;
import com.fcctech.tournament.service.BeltService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class BeltServiceImp implements BeltService {

    @Autowired
    private BeltCategoryRepository beltCategoryRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public BeltCategory createBelt(Long userId, Long tournamentId, BeltRequest request) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(TournamentNotFoundException::new);
        tournament.assertOwner(userId);

        BeltCategory beltCategory = new BeltCategory();
        beltCategory.setName(request.getName());
        beltCategory.setTournament(tournament);
        return beltCategoryRepository.save(beltCategory);
    }

    @Override
    public void updateBelt(Long userId, Long tournamentId, Long beltId, BeltRequest request) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(TournamentNotFoundException::new);
        tournament.assertOwner(userId);

        BeltCategory beltCategory = beltCategoryRepository.findById(beltId).orElseThrow(() -> new NotFoundException("belt not found"));
        beltCategory.setName(request.getName());

        beltCategoryRepository.save(beltCategory);
    }

    @Override
    public BeltCategory findTournamnetBelt(Long tournamentId, Long beltId) {
        BeltCategory beltCategory = beltCategoryRepository.findById(beltId).orElseThrow(() -> new NotFoundException("belt not found"));
        if (!beltCategory.getTournament().getId().equals(tournamentId)) {
            throw new BadRequestException("belt doesnt belong to tournament");
        }
        return beltCategory;
    }

    @Override
    public List<BeltCategory> findAllBeltsByTournamentId(Long tournamentId) {
        return beltCategoryRepository.findAllBeltByTournamentId(tournamentId);
    }
}

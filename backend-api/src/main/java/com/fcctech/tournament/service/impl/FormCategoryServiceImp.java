package com.fcctech.tournament.service.impl;

import com.fcctech.tournament.entity.FormCategory;
import com.fcctech.tournament.entity.Tournament;
import com.fcctech.tournament.exception.BadRequestException;
import com.fcctech.tournament.exception.NotFoundException;
import com.fcctech.tournament.exception.TournamentNotFoundException;
import com.fcctech.tournament.payload.request.FormCategoryRequest;
import com.fcctech.tournament.repository.FormCategoryRepository;
import com.fcctech.tournament.repository.TournamentRepository;
import com.fcctech.tournament.service.FormCategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class FormCategoryServiceImp implements FormCategoryService {

    @Autowired
    private FormCategoryRepository formCategoryRepository;

    @Autowired
    private TournamentRepository tournamentRepository;
    @Override
    public FormCategory createFormCategory(Long userId, Long tournamentId, FormCategoryRequest request) {
        if (request.getMinAge() >= request.getMaxAge()) {
            throw new BadRequestException("min_age is equal or grater than max_age");
        }

        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(TournamentNotFoundException::new);
        tournament.assertOwner(userId);

        List<FormCategory> categories = formCategoryRepository.findAllByTournamentId(tournamentId);
        validateAgeRange(request.getMinAge(), request.getMaxAge(), categories);

        FormCategory category = new FormCategory();
        category.setSex(request.getSex());
        category.setName(request.getName());
        category.setType(request.getType());
        category.setTournament(tournament);
        category.setMaxAge(request.getMaxAge());
        category.setMinAge(request.getMinAge());
        category.setMaxParticipant(request.getMaxParticipants());
        category.setFormat(request.getFormat());

        return formCategoryRepository.save(category);
    }

    @Override
    public void updateFormCategory(Long userId, Long tournamentId, Long categoryId, FormCategoryRequest request) {
        if (request.getMinAge() >= request.getMaxAge()) {
            throw new BadRequestException("min_age is equal or grater than max_age");
        }
        FormCategory category = formCategoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        if (!category.getTournament().getId().equals(tournamentId)) {
            throw new BadRequestException("the category doesn't belong to tournament");
        }

        List<FormCategory> categories = formCategoryRepository.findAllByTournamentId(tournamentId);
        categories.remove(category);
        validateAgeRange(request.getMinAge(), request.getMaxAge(), categories);

        category.setSex(request.getSex());
        category.setName(request.getName());
        category.setType(request.getType());
        category.setMaxAge(request.getMaxAge());
        category.setMinAge(request.getMinAge());
        category.setMaxParticipant(request.getMaxParticipants());
        category.setFormat(request.getFormat());

        formCategoryRepository.save(category);
    }

    @Override
    public FormCategory getFormCategory(Long tournamentId, Long categoryId) {
        FormCategory category = formCategoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        if (!category.getTournament().getId().equals(tournamentId)) {
            throw new BadRequestException("the category doesn't belong to tournament");
        }
        return category;
    }

    @Override
    public List<FormCategory> getAllFormCategory(Long tournamentId) {
        return formCategoryRepository.findAllByTournamentId(tournamentId);
    }

    public void validateAgeRange(int reqMin, int reqMax, List<FormCategory> categories) {

        categories.forEach(category -> {
            int min = category.getMinAge();
            int max = category.getMaxAge();
            if (reqMin >= min && reqMin <= max) {
                throw new BadRequestException(String.format("min [%d] value has conflict with in category %s range [%d, %d]",
                        reqMin, category.getName(), category.getMinAge(), category.getMaxAge()));
            }

            if (reqMax >= min && reqMax <= max) {
                throw new BadRequestException(String.format("max [%d] value has conflict with in category %s range [%d, %d]",
                        reqMax, category.getName(), category.getMinAge(), category.getMaxAge()));
            }

            if (reqMin < min && reqMax > max) {
                throw new BadRequestException(String.format("max [%d] value has conflict with in category %s range [%d, %d]",
                        reqMax, category.getName(), category.getMinAge(), category.getMaxAge()));
            }

        });
    }
}

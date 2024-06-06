package com.fcctech.tournament.entity;

import com.fcctech.tournament.domain.competition.MatchStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "matches")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    @Column(name = "date_started")
    private LocalTime start;

    @Column(name = "date_ended")
    private LocalTime end;

    @OneToOne
    @JoinColumn(name="next_match_id")
    private Match next;

    @OneToOne
    @JoinColumn(name="preview_left_match_id")
    private Match leftMatch;

    @OneToOne
    @JoinColumn(name="preview_right_match_id")
    private Match rightMatch;

    @OneToOne
    @JoinColumn(name="left_participant_id")
    private Participant left;

    @OneToOne
    @JoinColumn(name="right_participant_id")
    private Participant right;

    @OneToOne
    @JoinColumn(name="winner_participant_id")
    private Participant winner;

    private MatchStatus status;

    private Integer round;

    @ManyToOne
    @JoinColumn(name="bracket_id", nullable=false)
    private BracketCompetition bracket;

    @Transient
    public Long nextMatchId() {
        return next != null ? next.id : null;
    }

    @Transient
    public Long winnerId() {
        return winner != null ? winner.getId() : null;
    }

    @Transient
    public List<Long> dependsOnMatches() {
        List<Long> ids = new ArrayList<>();
        if (leftMatch != null) {
            ids.add(leftMatch.getId());
        }
        if (rightMatch != null) {
            ids.add(rightMatch.getId());
        }
        return ids;
    }
    @Transient
    public Long leftParticipantId() {
        return left != null ? left.getId() : null;
    }
    @Transient
    public Long rightParticipantId() {
        return right != null ? right.getId() : null;
    }

    @Transient
    public Long leftMatchId() {
        return leftMatch != null ? leftMatch.getId() : null;
    }
    @Transient
    public Long rightMatchId() {
        return rightMatch != null ? rightMatch.getId() : null;
    }

}

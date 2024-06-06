create schema tournament;

create table users (
    id int primary key AUTO_INCREMENT,
    email varchar(60) unique not null,
    user_name varchar(60) unique not null,
    password varchar(60) not null,
    date_created timestamp DEFAULT current_timestamp,
    status varchar(60)
);

create table roles (
    id int primary key AUTO_INCREMENT,
    name varchar(60) unique not null
);

create table user_roles(
    user_id integer not null,
    role_id integer not null,
    constraint relation primary key (user_id, role_id)
);

create table accounts (
      id int primary key AUTO_INCREMENT,
      association_name varchar(60) unique not null,
      phone_number varchar(20) unique not null,
      street_name varchar(60) ,
      street_number varchar(10),
      city_name varchar(60),
      state_name varchar(60),
      country_code varchar(3),
      owner_id integer not null,
      index(owner_id),
          FOREIGN KEY (owner_id)
                  REFERENCES users(id)
                  ON DELETE CASCADE
);

create table countries (
    name varchar(60) unique not null,
    code varchar(3) unique primary key
);

create table tournament_type (
    name varchar(60) unique primary key
);


create table tournament (
    id int primary key AUTO_INCREMENT,
    owner_id int not null,
    name varchar(60) not null,
    description varchar(300),
    date_started date not null,
    date_end date not null,
    status varchar(60) not null,
    date_created timestamp DEFAULT current_timestamp,
    date_updated timestamp,
    type varchar(60) not null,

    street_address varchar(60) ,
    street_number varchar(60),
    city_name varchar(60),
    state_name varchar(60),
    country_code varchar(3),
    geolocation varchar(60),

    index(owner_id),
    FOREIGN KEY (owner_id)
            REFERENCES users(id)
            ON DELETE CASCADE,
    FOREIGN KEY (type)
                REFERENCES tournament_type(name)
                ON DELETE CASCADE
)ENGINE=INNODB;

create table participant_teams (
    id int primary key AUTO_INCREMENT,
    tournament_id integer not null,
    name varchar(60) not null,
    email varchar(60) not null,
    user_subscriber_id integer not null,
    date_created timestamp DEFAULT current_timestamp,
    index(tournament_id),
        FOREIGN KEY (tournament_id)
                REFERENCES tournament(id)
                ON DELETE CASCADE,
    index(user_subscriber_id),
            FOREIGN KEY (user_subscriber_id)
                    REFERENCES users(id)
);



create table age_categories (
    id int primary key AUTO_INCREMENT,
    tournament_id integer not null,
    name varchar(60) not null,
    min_age int not null,
    max_age int not null,

    index(tournament_id),
        FOREIGN KEY (tournament_id)
                REFERENCES tournament(id)
                ON DELETE CASCADE
);



create table belt_categories (
    id int primary key AUTO_INCREMENT,
    tournament_id integer not null,
    name varchar(60) not null,
    index(tournament_id),
        FOREIGN KEY (tournament_id)
                REFERENCES tournament(id)
                ON DELETE CASCADE
);


create table base_categories (
    id int primary key AUTO_INCREMENT,
    tournament_id integer not null,
    name varchar(60),
    sex varchar(20) not null,
    type varchar(20) not null,
    format varchar(40) not null,
    num_participant integer not null default 1,
    index(tournament_id),
        FOREIGN KEY (tournament_id)
                REFERENCES tournament(id)
                ON DELETE CASCADE
);

create table combat_categories (
    id int primary key,
    min_weight int not null,
    max_weight int not null,
    age_category_id integer not null,
    index(age_category_id),
        FOREIGN KEY (age_category_id)
                REFERENCES age_categories(id),
    FOREIGN KEY (id)
          REFERENCES base_categories(id)

);

create table form_categories (
    id int primary key,
    min_age integer not null,
    max_age integer not null,
    FOREIGN KEY (id)
      REFERENCES base_categories(id)

);



create table participants (
    id int primary key AUTO_INCREMENT,
    tournament_id integer not null,
    name varchar(60) not null,
    last_name varchar(60) not null,
    doc_type varchar(60),
    doc_number varchar(60),
    nationality varchar(60),
    email varchar(60) not null,
    birth_date date not null,
    height integer,
    weight integer,
    sex varchar(20) not null,
    status varchar(20) not null,
    participant_team_id integer not null,
    belt_category_id integer not null,
    date_created timestamp DEFAULT current_timestamp,
    date_updated timestamp,

    index(participant_team_id),
    index(tournament_id),
    FOREIGN KEY (tournament_id)
            REFERENCES tournament(id)
            ON DELETE CASCADE
);

create table participant_modalities (
    id int primary key AUTO_INCREMENT,
    participant_id integer not null,
    type varchar(20) not null,
    category_id integer not null,
    index(category_id),
    index(participant_id),
    FOREIGN KEY (participant_id)
            REFERENCES participants(id)
            ON DELETE CASCADE
);

create table competitions (
    id int primary key AUTO_INCREMENT,
    tournament_id integer not null,
    belt_category_id integer not null,
    sex varchar(20) not null,
    type varchar(20) not null,
    status varchar(20) not null,
    category_id integer,
    index(category_id),
    index(tournament_id),
    FOREIGN KEY (tournament_id)
            REFERENCES tournament(id)

);

create table brackets (
    id int primary key,
    FOREIGN KEY (id)
            REFERENCES competitions(id)
);

create table matches (
    id int primary key AUTO_INCREMENT,
    number integer,
    date_started timestamp ,
    date_ended timestamp ,
    next_match_id integer,
    preview_left_match_id integer,
    preview_right_match_id integer,
    left_participant_id integer,
    right_participant_id integer,
    winner_participant_id integer,
    status varchar(20) not null,
    round integer not null,
    bracket_id integer not null,
    FOREIGN KEY (bracket_id)
                REFERENCES brackets(id)
);

create table competition_stages (
    id int primary key,
    number_rounds integer not null,
    FOREIGN KEY (id)
            REFERENCES competitions(id)

);

create table stages (
    id int primary key AUTO_INCREMENT,
    stage_competition_id integer not null,
    round integer not null,
    status varchar(20) not null,
    index(stage_competition_id),
    FOREIGN KEY (stage_competition_id)
            REFERENCES competition_stages(id)
);

create table participant_stages (
    id int primary key AUTO_INCREMENT,
    stage_id integer not null,
    participant_id integer not null,
    score integer,
    index(stage_id),
    FOREIGN KEY (stage_id)
            REFERENCES stages(id)
);
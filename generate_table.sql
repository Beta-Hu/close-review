create table if not exists cr_user(
    id int(16) not null auto_increment primary key,
    username varchar(16) not null,
    password varchar(256) not null,
    email varchar(32) not null,
    organization varchar(64) not null
);

create table if not exists cr_organization(
    id int(8) not null auto_increment primary key,
    name varchar(128) not null,
    admin int(16) unique,
    address varchar(256)
);

create table if not exists cr_submission(
    id int(64) not null auto_increment primary key,
    title varchar(64) not null unique,
    author json not null,
    corresponding int(16) not null,
    conference int(8) not null,
    main_area int(8) not null,
    second_area json,
    file varchar(128) not null,
    support_material varchar(128) default null,
    status int(4) not null default 0,     # 0-not available, 1-accept, 2-reject, 3-withdraw
    submit_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp
);

create table if not exists cr_review(
    reviewer int(16) not null,
    submission int(64) not null,
    initial_score int(4) not null,
    initial_comment json not null,
    confidence int(4) not null,
    rebuttal json,
    final_score int(4) not null ,
    final_comment json not null,
    primary key (reviewer, submission)
);

create table if not exists cr_conference(
    id int(8) not null auto_increment primary key,
    name varchar(64) not null unique,
    chair json not null,
    email varchar(32) not null,
    year int(4) not null
);

create table if not exists cr_area(
    name varchar(32) not null,
    conference_id int(8) not null,
    area_chair int(16) not null
);
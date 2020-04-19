create table comment2
(
    id            BIGINT auto_increment,
    parent_id     BIGINT              not null,
    type          INT                 not null,
    commentator   BIGINT default NULL not null,
    content       VARCHAR(1024)       not null,
    gmt_create    BIGINT              not null,
    gmt_modified  BIGINT,
    like_count    BIGINT default 0,
    comment_count INT    default 0,
    constraint comment2_pk
        primary key (id)
);
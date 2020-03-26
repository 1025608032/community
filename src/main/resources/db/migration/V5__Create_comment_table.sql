  create table comment
(
	id BIGINT auto_increment,
	parent_id BIGINT not null,
	type INT not null,
	commentator INT not null,
	content VARCHAR(1024) not null,
	gmt_create BIGINT not null,
	gmt_modified BIGINT,
	like_count BIGINT default 0,
	constraint comment_pk
		primary key (id)
);
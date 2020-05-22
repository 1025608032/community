create table keys
(
	ID BIGINT auto_increment,
	KEY VARCHAR(50) not null,
	status int default 0 not null,
	constraint keys_pk
		primary key (ID)
);

create unique index keys_KEY_uindex
	on keys (KEY);

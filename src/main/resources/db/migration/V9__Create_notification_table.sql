create table notification
(
	id BIGINT auto_increment,
	notifier BIGINT not null,
	receiver BIGINT not null,
	outerId BIGINT not null,
	type INT not null,
	gmt_create BIGINT not null,
	status INT default 0 not null,
	notifier_name VARCHAR(100),
	outer_title VARCHAR(256),
	constraint notification_pk
		primary key (id)
);


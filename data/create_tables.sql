CREATE TABLE IF NOT EXISTS user_web_socket (
	id serial primary key not null,
	email varchar(100) unique not null,
	first_name varchar not null,
	last_name varchar not null,
	password varchar(60) not null,
	role varchar(60) not null,
	status varchar(60) not null
);
CREATE TABLE IF NOT EXISTS friends(
	id_chat_room integer not null,
	my_id integer not null,
	friend_id integer not null,
	friend_first_name varchar not null,
	friend_last_name varchar not null,
	id serial primary key not null
);
CREATE TABLE IF NOT EXISTS message(
	id_chat_room integer  not null,
	from_user varchar not null,
	message varchar not null,
	id_message serial primary key not null
);

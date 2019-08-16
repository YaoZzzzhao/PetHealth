-- DROP SEQUENCE IF EXISTS user_id_seq;
-- DROP SEQUENCE IF EXISTS pet_id_seq;
-- DROP SEQUENCE IF EXISTS account_id_seq;


-- DROP TABLE IF EXISTS users CASCADE;
-- DROP TABLE IF EXISTS pets CASCADE;
-- DROP TABLE IF EXISTS cats CASCADE;
-- DROP TABLE IF EXISTS dogs CASCADE;



-- create sequence user_id_seq start with 1;
-- create sequence pet_id_seq start with 1;

create table users(
	id					BIGSERIAL not null PRIMARY KEY,
	full_name			varchar(30) not null,
	password			varchar(30) not null,
	email				varchar(30),
	regis_date			date not null,
	pet_type			char(3) not null,
	pet_num				int not null
);

comment on column users.pet_type is 'CAT or DOG';


create table pets(
	id				bigserial not null PRIMARY KEY,
	owner_id		bigint not null,
	name		    varchar(30) not null,
	type			char(3) not null,
	color			varchar(15),
	breed			varchar(50),
	age				int
);

ALTER TABLE pets
    ADD CONSTRAINT fk_users_pets
    FOREIGN KEY (owner_id)
    REFERENCES users(id);


comment on column pets.type is 'CAT or DOG';
comment on column pets.age is 'in year';

create table cats(
	id					    BIGSERIAL not null PRIMARY KEY,
	owner_id				bigint not null,
	name				    varchar(30) not null,
	Spay_neuter				char(1),
	Deworm					char(1),
	Panl        			char(1),
	Rhi         			char(1),
	Calici					char(1),
	Rabies					char(1)
);

ALTER TABLE cats
    ADD CONSTRAINT fk_pets_cats
    FOREIGN KEY (id)
    REFERENCES pets(id);

create table dogs(
	id					    BIGSERIAL not null PRIMARY KEY,
	owner_id				bigint not null,
	name				    varchar(30) not null,
	Spay_neuter				char(1),
	Rabies					char(1),
	Distemper				char(1),
	Parvo					char(1),
	Adenovirus				char(1),
	Bordetella				char(1)
);

ALTER TABLE dogs
    ADD CONSTRAINT fk_pets_dogs
    FOREIGN KEY (id)
    REFERENCES pets(id);
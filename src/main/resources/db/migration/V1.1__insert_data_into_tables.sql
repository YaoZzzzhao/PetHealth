insert into
	users(id, full_name, password, email, regis_date, pet_type, pet_num) values
	(1, 'YaoZhao', 'kkmacs213', 'yaozhao95@gmail.com', '2019/06/21', 'CAT', 2);
commit;

insert into
	pets(id, owner_id, pet_name, type, color, breed, age) values
	(1,1,'Justin','CAT','white', 'Domestic Short Hair', 0.4),
	(2,1,'Judd','CAT','black','Domestic Short Hair',0.4)
;
commit;

insert into
	cats(id, owner_id, cat_name, spay_neuter, deworm, Panleukopenia, Rhinotracheitis, Calici, Rabies) values
	(1,1,'Justin','Y','Y','Y','N','N','Y'),
	(2,1,'Judd','Y','Y','Y','N','N','Y')
;
commit;

insert into
	dogs(dog_id, owner_id, dog_name, spay_neuter, Rabies,Distemper, Parvo, Adenovirus, Bordetella) values
	(2,1,'Jinmu','Y','Y','Y','Y','Y')
;
commit;
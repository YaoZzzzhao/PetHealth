alter table dogs drop constraint fk_pets_dogs;
alter table dogs rename dog_id to pet_id;
alter table dogs add constraint fk_pets_dogs foreign key (pet_id) references pets(id);

alter table cats drop constraint fk_pets_cats;
alter table cats rename cat_id to pet_id;
alter table cats add constraint fk_pets_cats foreign key (pet_id) references pets(id);

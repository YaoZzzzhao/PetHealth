-- insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
-- ('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
-- ('User', '/pet,/cat,/dog', 'Y', 'Y', 'N', 'N')
-- ;
-- commit;

insert into users_role (user_id, role_id)values
(1, 1),
(2, 2),
(3, 2),
(1, 2)
;
commit;


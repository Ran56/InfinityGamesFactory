insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/compay,/companies,/games,/gams,/consol,/consoles', 'Y', 'Y', 'Y', 'N'),
('user', '/consoles,/consol,/games,/gams', 'Y', 'N', 'N', 'N')
;
commit;
insert into users (name, password, first_name, last_name, email) values
('ran', '25f9e794323b453885f5181f1b624d0b', 'ryan', 'zhang', 'zr743331546@gmail.com'),
('august', '25f9e794323b453885f5181f1b624d0b', 'august', 'zhang', 'zr743331546@163.com')
;
commit;
insert into users_role values
(1, 1),
(2, 2),
(1, 2)
;
commit;

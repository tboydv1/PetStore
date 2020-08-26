SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE pet_animal;


INSERT INTO pet_animal(`id`, `name`, `breed`, `types`, `sex`, `age`)
VALUES(200, 'bobby', 'german shepherd', 'DOG', 'MALE', 15),
(201, 'bobby', '', 'CAT', 'MALE', 15),
(202, 'bobby', '', 'HAMSTER', 'MALE', 15),
(203, 'bobby', 'german shepherd', 'DOG', 'MALE', 15),
(204, 'bobby', 'rattle', 'SNAKE', 'MALE', 15),
(205, 'bobby', '', 'GOAT', 'MALE', 15),
(206, 'bobby', 'parrot', 'BIRD', 'MALE', 15),
(207, 'bobby', 'german shepherd', 'DOG', 'MALE', 15);


SET FOREIGN_KEY_CHECKS = 1;
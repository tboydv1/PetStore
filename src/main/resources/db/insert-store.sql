SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE store;
TRUNCATE TABLE pet_animal;


INSERT INTO store(`id`, `store_name`, `store_number`, `address`, `city`, `state`, `country`)
VALUES(500, 'Good shepherd', 125, '12, good street', 'Yaba', 'Lagos', 'Nigeria'),
      (501, 'Pet store', 124, '13, pet street',    'VI', 'Lagos', 'Nigeria'),
      (502, 'Puppy lovers', 245, '16, puppy street', 'Lekki', 'Lagos', 'Nigeria');


SET FOREIGN_KEY_CHECKS = 1;
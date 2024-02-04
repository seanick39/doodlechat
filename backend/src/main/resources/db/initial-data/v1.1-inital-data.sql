insert into "user" (name)
values ('Joffrey'),
       ('NINJA'),
       ('I am mister brilliant'),
       ('martin57'),
       ('Patricia'),
       ('DoodleUser');

insert into message (data, user_id, created_at)
values ('Brilliant', (select id from "user" where name = 'Joffrey'), (now()::timestamp - interval '9 minutes')),
       ('Great resource, thanks', (select id from "user" where name = 'NINJA'), (now()::timestamp - interval '8 minutes')),
       ('THANKS!!!!!', (select id from "user" where name = 'I am mister brilliant'), (now()::timestamp - interval '7 minutes')),
       ('Thanks Peter', (select id from "user" where name = 'martin57'), (now()::timestamp - interval '6 minutes')),
       ('Sounds good to me!', (select id from "user" where name = 'Patricia'), (now()::timestamp - interval '5 minutes')),
       ('Hey folks! I wanted to get in touch with you regarding the project. Please, let me know how you plan to contribute',
        (select id from "user" where name = 'DoodleUser'), (now()::timestamp - interval '4 minutes'));


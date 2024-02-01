create table if not exists "user" (
  id                uuid primary key default gen_random_uuid(),
  name              varchar not null
);

create table if not exists message (
  id                uuid primary key default gen_random_uuid(),
  data              varchar not null,
  user_id           uuid references "user"(id) not null,
  created_at        timestamp without time zone not null default now()
);


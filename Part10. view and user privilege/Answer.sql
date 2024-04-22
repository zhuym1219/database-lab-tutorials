-- Exercise1
revoke create on schema public from public;
revoke select on all tables in schema public from public;
grant select on public.five_year_posts to staff01;

-- Exercise2
create user manage superuser password '123456';

create user staff01 password '123456';
create schema s01 authorization staff01;
alter user staff01 set search_path to s01,public;

create user staff02 password '123456';
create schema s02 authorization staff02;
alter user staff02 set search_path to s02,public;

grant select on all tables in schema public to public;
revoke create on schema public from public;
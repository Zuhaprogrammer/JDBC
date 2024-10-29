create function add_card(i_name character varying, i_card_number character varying, i_expiry_date character varying, i_password character varying, i_user_id integer, i_balance double precision DEFAULT 0)
    returns TABLE(id integer, name character varying, card_number character varying, expiry_date character varying, balance double precision, user_id integer, username character varying, user_email character varying, user_phone_number character varying, created_date timestamp without time zone)
    language plpgsql
as
$$
declare
v_id int := 0;
begin
insert into card(name, card_number, expiry_date, password, balance, user_id)
values (i_name, i_card_number, i_expiry_date, i_password, i_balance, i_user_id) returning id into v_id;
return query select c.name,
                            c.card_number,
                            c.expiry_date,
                            c.balance,
                            u.username,
                            u.email,
                            u.phone_number,
                            c.created_date
                         from card c left join users u on c.user_id = u.id where c.id = v_id;

exception
            when others then
                raise notice 'Card number is already exist';
end;
    $$;

alter function add_card(varchar, varchar, varchar, varchar, integer, double precision) owner to postgres;

create function add_user(i_username character varying, i_password character varying, i_phone_number character varying, i_email character varying)
    returns TABLE(id integer, username character varying, phone_number character varying, email character varying, created_date timestamp without time zone, updated_date timestamp without time zone)
    language plpgsql
as
$$
declare
v_id int := 0;
begin
insert into users(username, password, phone_number, email)
values (i_username, i_password, i_phone_number, i_email) returning id into v_id;
return query select u.id,
                            u.username,
                            u.phone_number,
                            u.email,
                            u.created_date,
                            u.updated_date
                     from users u where u.id = v_id;

exception
            when others then
                raise notice 'Username or email is already exist';
end;
    $$;

alter function add_user(varchar, varchar, varchar, varchar) owner to postgres;

create function check_user(i_username character varying, i_password character varying)
    returns TABLE(id integer, username character varying, phone_number character varying, email character varying, created_date timestamp without time zone, updated_date timestamp without time zone)
    language plpgsql
as
$$
begin
return query select u.id,
                            u.username,
                            u.phone_number,
                            u.email,
                            u.created_date,
                            u.updated_date from users u
                                           where u.username = i_username and u.password = i_password and u.active = true;
end;
    $$;

alter function check_user(varchar, varchar) owner to postgres;

create function delete_my_card(i_user_id integer, i_card_id integer) returns void
    language plpgsql
as
$$
BEGIN
update card set active = false where user_id = i_user_id and id = i_card_id and active = true;
end;
    $$;

alter function delete_my_card(integer, integer) owner to postgres;

create function get_my_cards(i_user_id integer)
    returns TABLE(id integer, name character varying, card_number character varying, expiry_date character varying, balance double precision, created_date timestamp without time zone)
    language plpgsql
as
$$
begin
return query select c.id,
                            c.name,
                            c.card_number,
                            c.expiry_date,
                            c.balance,
                            c.created_date from card c where c.user_id = i_user_id and c.active = true;
end;
    $$;

alter function get_my_cards(integer) owner to postgres;


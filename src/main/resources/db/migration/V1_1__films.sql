create table Films (id bigint not null auto_increment, name_original varchar(225),
                    imdb_id varchar(255), name_ru varchar(255), rating_imdb integer,
                    year integer, primary key (id))
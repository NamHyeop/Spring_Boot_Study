drop table customer;
CREATE TABLE customer(
    id mediumint(8) unsigned NOT NULL auto_increment,
    firstName varchar(255) default null,
    lastName varchar(255) default null,
    birthdate varchar(255),
    PRIMARY KEY (id)
) AUTO_INCREMENT=1;

select id, firstName, lastName, birthdate from customer where firstName like ? order by lastName, firstName
select * from customer where firstName like ? order by lastName, firstName
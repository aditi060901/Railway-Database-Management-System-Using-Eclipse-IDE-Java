create database railwaym;
use railway;
create table person(PID int primary key,Name varchar(30), Age int, Gender varchar(10));
Create table statiTriggerson(SName varchar(30) primary key);
Create table train(TNO int primary key, TName varchar(30));
create table ticket(TID int primary key, PID int, foreign key(PID) references person(PID), TNO int, foreign key(TNO) references train(TNO), From_Station varchar(30), foreign key(From_Station) references station(SName), To_Station varchar(30), foreign key(To_Station) references station(SName), Coach_Type varchar(10) , Date_Of_Journey  date, Amount int,Refund int);
create table user(username varchar(30) primary key,pass varchar(30));
select *  from person;
select * from train;
select * from station;
select * from ticket;
select * from user;
alter table ticket modify column Amount int default null;
alter table ticket drop column Refund;
delimiter //
create procedure up()
begin
declare coach varchar(30);
declare v int default 0;
declare id int;
declare curr cursor for select PID,Coach_Type from ticket;
declare continue handler for not found set v=1;
open curr;
repeat
fetch curr into id,coach;
if coach='AC' then
update ticket set Amount=1700 where PID=id;
elseif coach='Sleeper' then
update ticket set Amount=700 where PID=id;
end if;
until v
end repeat;
close curr;
end //
delimiter ;
call up();
alter table ticket modify TID int auto_increment;
delimiter //
create trigger tr after delete on ticket for each row
begin
delete from perpersonson where PID=old.PID;
end //
delimiter ;





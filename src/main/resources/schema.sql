create table if not exists hotel(
    id int primary key AUTO_INCREMENT,
    name text,
    location text,
    rating int
);
create table if not exists room(
    id int primary key AUTO_INCREMENT,
    roomNumber text,
    type text,
    price float,
    hotelId int,
    foreign key (hotelId)references hotel(id)
);
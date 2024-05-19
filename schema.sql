pragma foreign_keys = on;

drop table if exists item;
drop table if exists `order`;
drop table if exists order_item;
drop table if exists customer;

create table item (
  id varchar(36) not null unique primary key,
  price double not null,
  name text not null,
  stock int not null check (stock > 0),
  rating int not null
);

create table `order` (
  id varchar(36) not null unique primary key,
  customer varchar(36) not null,
  foreign key (customer) references customer(id)
);

create table order_item (
  `order` varchar(36) not null,
  item varchar(36) not null,
  quantity int not null,
  foreign key (`order`) references `order`(id),
  foreign key (item) references item(id),
  primary key (`order`, item)
);

create table customer (
  id varchar(36) not null unique primary key,
  fname text not null,
  lname text not null,
  phone text not null,
  add_l1 text not null,
  add_l2 text not null,
  add_town text not null,
  add_postcode text not null
);


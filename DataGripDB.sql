-- we don't know how to generate schema MiBankDB (class Schema) :(
create table Employee
(
	id int not null
		primary key,
	password varchar(255) null,
	user_name varchar(255) null
)
;

create table User
(
	dni varchar(255) not null
		primary key,
	address varchar(255) null,
	created_at datetime null,
	email varchar(255) null,
	name varchar(255) null,
	password varchar(255) null,
	phone int null,
	phone_prefix varchar(255) null,
	surname varchar(255) null,
	updated_at datetime null
)
;

create table Account
(
	created_at datetime default CURRENT_TIMESTAMP null,
	currency varchar(255) null,
	bank int not null,
	control int not null,
	office int not null,
	id int(10) not null,
	user_dni varchar(255) null,
	primary key (bank, control, office, id),
	constraint FK_Account_user_dni
		foreign key (user_dni) references User (dni)
)
;

create index FK_Account_user_dni
	on Account (user_dni)
;

create table Transfer
(
	id int not null
		primary key,
	amount bigint null,
	created_at datetime null,
	description varchar(255) null,
	account_id int null,
	account_bank int null,
	account_office int null,
	account_control int null,
	from_account_control int null,
	from_account_id int null,
	from_account_office int null,
	from_account_bank int null,
	employee_involved int null,
	constraint FK_Transfer_account_bank
		foreign key (account_bank, account_control, account_office, account_id) references Account (bank, control, office, id),
	constraint FK_Transfer_from_account_bank
		foreign key (from_account_bank, from_account_control, from_account_office, from_account_id) references Account (bank, control, office, id),
	constraint FK_Transfer_employee_involved
		foreign key (employee_involved) references Employee (id)
)
;

create index FK_Transfer_account_bank
	on Transfer (account_bank, account_control, account_office, account_id)
;

create index FK_Transfer_employee_involved
	on Transfer (employee_involved)
;

create index FK_Transfer_from_account_bank
	on Transfer (from_account_bank, from_account_control, from_account_office, from_account_id)
;

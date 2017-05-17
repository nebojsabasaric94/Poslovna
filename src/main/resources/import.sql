insert into national_bank (common_name, country, email, organization, organization_unit) values ('NBS','Srbija','nbs@nbs','org','orgU');

insert into bank (bank_code, pib, name, address, email, web, phone, fax, bank) values ('123', '0123456789', 'Erste', 'blababa', 'fafa@fa.com', 'afafa', '+415151567', '+415627', true);
insert into bank (bank_code, pib, name, address, email, web, phone, fax, bank) values ('124', '0163456789', 'Erste2', 'blabfaba', 'fafha@fa.com', 'afjafa', '+415251567', '+411627', true);

insert into country(country_code,name) values ('srb','Srbija');
insert into country(country_code,name) values ('bih','Bosna');
insert into country(country_code,name) values ('cro','Hrvatska');

insert into place(name,ptt_number,country_country_id) values ('Kragujevac','34000','1');
insert into place(name,ptt_number,country_country_id) values ('Novi Sad','21000','1');
insert into place(name,ptt_number,country_country_id) values ('Zagreb','11010','3');
insert into place(name,ptt_number,country_country_id) values ('Sarajevo','12121','2');

insert into currency(official_code,name,dom) values ('din','dinar',1);
insert into currency(official_code,name,dom) values ('eur','euro',0);

insert into exchange_rate_list(date, number_of_exchange_rate_list,applied_by,commercial_bank_rate_id) values ('2017-05-03',123,'2017-04-04',1);
insert into exchange_rate_list(date, number_of_exchange_rate_list,applied_by,commercial_bank_rate_id) values ('2017-05-03',123,'2017-04-04',2);




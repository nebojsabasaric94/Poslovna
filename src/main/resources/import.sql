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

insert into currency(official_code,name,domicilna) values ('din','dinar',1);
insert into currency(official_code,name,domicilna) values ('eur','euro',0);
insert into currency(official_code,name,domicilna) values ('jua','juan',1);
insert into currency(official_code,name,domicilna) values ('usd','dollar',0);
insert into currency(official_code,name,domicilna) values ('kkk','marka',1);

insert into exchange_rate_list(date, number_of_exchange_rate_list,applied_by,commercial_bank_rate_id) values ('2017-05-03',123,'2017-04-04',1);
insert into exchange_rate_list(date, number_of_exchange_rate_list,applied_by,commercial_bank_rate_id) values ('2017-05-03',123,'2017-04-04',2);



insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-01','1','2017-05-01','1');
insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-18','2','2017-05-18','1');
insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-19','3','2017-05-19','1');
insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-20','4','2017-05-20','1');
insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-21','5','2017-05-21','1');
insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-17','6','2017-05-17','2');
insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-18','7','2017-05-18','2');
insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-19','8','2017-05-19','2');
insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-20','9','2017-05-20','2');
insert into exchange_rate_list(date,number_of_exchange_rate_list,applied_by,commercial_bank_rate_id)values('2017-05-21','10','2017-05-21','2');


insert into currency_rate(buying_exchange_rate,middle_exchange_rate,selling_exchange_rate,base_currency_currency_id,currency_in_list_id,according_to_currency_currency_id) values('123','124','125','1','1','2');
insert into currency_rate(buying_exchange_rate,middle_exchange_rate,selling_exchange_rate,base_currency_currency_id,currency_in_list_id,according_to_currency_currency_id) values('61','62','63','1','1','5');
insert into currency_rate(buying_exchange_rate,middle_exchange_rate,selling_exchange_rate,base_currency_currency_id,currency_in_list_id,according_to_currency_currency_id) values('10','11','12','2','1','3');


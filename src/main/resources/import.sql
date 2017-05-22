
insert into national_bank (common_name, country, email, organization, organization_unit) values ('NBS','Srbija','nbs@nbs','org','orgU');

insert into bank (bank_code, pib, name, address, email, web, phone, fax, bank) values ('123', '0123456789', 'Erste', 'blababa', 'fafa@fa.com', 'afafa', '+415151567', '+415627', true);
insert into bank (bank_code, pib, name, address, email, web, phone, fax, bank) values ('124', '0163456789', 'Erste2', 'blabfaba', 'fafha@fa.com', 'afjafa', '+415251567', '+411627', true);

insert into country(country_code,name) values ('srb','Srbija');
insert into country(country_code,name) values ('bih','Bosna');
insert into country(country_code,name) values ('cro','Hrvatska');
insert into country(country_code,name) values ('USA','Sjedinjene americke drzave');
insert into country(country_code,name) values ('chn','Kina');
insert into country(country_code,name) values ('ITA','Italija');

insert into place(name,ptt_number,country_country_id) values ('Kragujevac','34000','1');
insert into place(name,ptt_number,country_country_id) values ('Novi Sad','21000','1');
insert into place(name,ptt_number,country_country_id) values ('Zagreb','11010','3');
insert into place(name,ptt_number,country_country_id) values ('Sarajevo','12121','2');

insert into currency(country_country_id,official_code,name,domicilna) values ('1','din','dinar',1);
insert into currency(country_country_id,official_code,name,domicilna) values ('4','eur','euro',0);
insert into currency(country_country_id,official_code,name,domicilna) values ('5','jua','juan',1);
insert into currency(country_country_id,official_code,name,domicilna) values ('6','usd','dollar',0);
insert into currency(country_country_id,official_code,name,domicilna) values ('2','kkk','marka',1);

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

insert into business_activity_code(name,code) values('Delatnosti restorana i pokretnih ugostiteljskih objekta','561');
insert into business_activity_code(name,code) values('Ketering i ostale usluge pripremanja i posluživanja hrane','562');
insert into business_activity_code(name,code) values('Trgovina neka','475');


insert into payment_type(code,name_of_payment_type) values ('001','Nalog za prenos');
insert into payment_type(code,name_of_payment_type) values ('002','Nalog za uplatu');
insert into payment_type(code,name_of_payment_type) values ('003','Nalog za isplatu');
insert into payment_type(code,name_of_payment_type) values ('004','Nalog za naplatu');


insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Vuka karadzica 5','Vuka karadzica 5','aa@a.com',1,'Janko','Jankovic','1234567891234','000000000','Fizicko lice','1')
insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Bulevar oslobodjenja 10','Bulevar Oslobodjenja 10 ','cc@c.com',1,'Scepan','Scekic','9876543219876','063555555','Fizicko lice','2')
insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Brace ribnikar 1','Vuka karadzica 10 ','bb@b.com',0,'Marko','Markovic','0000000000000','063333333','Fizicko lice','1')
insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Strazilovksa','Vuka karadzica 10 ','dd@d.com',0,'Bogoljub','Gagic','1122334455667','064444444','Fizicko lice','1')

insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Vuka Karadzica 53','Vuka karadzica 53 ','novi@most.com',0,'Danilo','Acimovic','0101119145452','062922222','Pravno lice','1')
insert into legal_entity(business_activity_code_business_activity_code_id,naziv_klijenta,skraceni_naziv_klijenta,fax,maticni_broj,nadlezni_poreski_organ_za_klijenta,naziv_organa,pib,client_id)values('1','Novi Most DA','N.M.D.A','021456123','123456','Poreski organ','Naziv organa','192145321','5')
insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Drinska 10','Drinska 10 ','lasta@prevoz.com',0,'Milos','Milosevic','1144778899445','062933333','Pravno lice','1')
insert into legal_entity(business_activity_code_business_activity_code_id,naziv_klijenta,skraceni_naziv_klijenta,fax,maticni_broj,nadlezni_poreski_organ_za_klijenta,naziv_organa,pib,client_id)values('2','Lasta','Lasta','0123456','654321','Poreski organ','Naziv organa','221456123','6')
insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Pozeska 2','Pozeska 2 ','sintelon@s.com',0,'Sima','Simic','0903996773631','064787898','Pravno lice','1')
insert into legal_entity(business_activity_code_business_activity_code_id,naziv_klijenta,skraceni_naziv_klijenta,fax,maticni_broj,nadlezni_poreski_organ_za_klijenta,naziv_organa,pib,client_id)values('3','Sintelon','Sintelon','777888','655445','Poreski organ','Naziv organa','787844777','7')

insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('103121231231231111','2017-01-21',1,'1','1','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('103187878745784542','2017-02-28',1,'1','2','2');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('103454698745632122','2017-03-30',1,'1','3','3');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('202454654789541254','2017-04-29',1,'2','4','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('202745485455555555','2017-01-18',1,'2','5','2');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('202145487447474747','2017-02-27',1,'2','6','3');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('202145745874545555','2017-03-01',1,'2','7','4');

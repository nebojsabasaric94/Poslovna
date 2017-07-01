insert into bank (bank_code, pib, name, address, email, web, phone, fax, bank) values ('200', '0123456789', 'Erste bank', 'Bulevar Oslobodjenja 4', 'erste@fbank.com', 'www.erste.com', '021333444', '021333443', true);
insert into bank (bank_code, pib, name, address, email, web, phone, fax, bank) values ('300', '1921681234', 'Uni credit bank', 'Narodnog fronta 5', 'unicredit@bank.com', 'www.unicredit.com', '021123123', '021123122', true);
insert into bank (bank_code, pib, name, address, email, web, phone, fax, bank) values ('400', '1221221211', 'Komercijalna banka', 'Ulica Modene', 'komercijalna@banka.com', 'www.komercijalna.com', '021456789', '021456788', true);
insert into bank (bank_code, pib, name, address, email, web, phone, fax, bank) values ('402', '1921921788', 'Pro credit bank', 'Vuka Karadzica bb', 'procredit@bank.com', 'www.procredit.com', '011122111', '011122112', true);
insert into bank (bank_code, pib, name, address, email, web, phone, fax, bank) values ('205', '1631637894', 'Raiffeisen bank', 'Cara Dusana 5', 'raiffeisen@bank.com', 'ww.raiffeisen.', '011454444', '011454445', true);

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

insert into currency(country_country_id,official_code,name,domicilna) values (1,'din','dinar',1);
insert into currency(country_country_id,official_code,name,domicilna) values (4,'eur','euro',0);
insert into currency(country_country_id,official_code,name,domicilna) values (5,'jua','juan',1);
insert into currency(country_country_id,official_code,name,domicilna) values (6,'usd','dollar',0);
insert into currency(country_country_id,official_code,name,domicilna) values (2,'kkk','marka',1);

insert into exchange_rate_list(date, number_of_exchange_rate_list,applied_by,commercial_bank_rate_id) values ('2017-05-03',111,'2017-04-04',1);
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


insert into currency_rate(buying_exchange_rate,middle_exchange_rate,selling_exchange_rate,base_currency_currency_id,currency_in_list_id,according_to_currency_currency_id) values('123.0','124.0','125.0','1','1','2');
insert into currency_rate(buying_exchange_rate,middle_exchange_rate,selling_exchange_rate,base_currency_currency_id,currency_in_list_id,according_to_currency_currency_id) values('61.1','62.12','63.55','1','1','5');
insert into currency_rate(buying_exchange_rate,middle_exchange_rate,selling_exchange_rate,base_currency_currency_id,currency_in_list_id,according_to_currency_currency_id) values('10.05','11.50','12.90','2','1','3');

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
insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Cara Dusana 12','Cara dusana 12','kostana@v.com',0,'Bosko','Boskovic','0102967773631','064787898','Pravno lice','1')
insert into legal_entity(business_activity_code_business_activity_code_id,naziv_klijenta,skraceni_naziv_klijenta,fax,maticni_broj,nadlezni_poreski_organ_za_klijenta,naziv_organa,pib,client_id)values('3','Kostana','Kostana','777888','655445','Poreski organ','Naziv organa','192168778','8')

insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Bulevar Mihajla Pupina 12','Bulevar Mihajla Pupina 12 ','ozren@s.com',0,'Ozren','Soldatovic','0405945884541','064945457','Fizicko lice','1')
insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Bulevar Oslobodjenja 12','Bulevar Oslobodjenja 12','zivan@r.com',0,'Zivan','Radosavljevic','0402956121245','060456123','Fizicko lice','1')
insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Tolstojeva 2','olstojeva 2','gavrilo@g.com',0,'Gavrilo','Gavrilovic','1212940124477','063565478','Fizicko lice','1')
insert into client(address,address_for_statements,email,email_statements,first_name,last_name,jmbg,phone,type_of_client,residence_id)values ('Topolska 18','Topolska 18','malina@v.com',0,'Malina','Vojvodic','1210959556612','064112211','Fizicko lice','1')


insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('200121231231231111','2016-01-21',0,'1','1','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('200187878745784542','2016-02-28',0,'1','2','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('200454698745632122','2016-03-30',1,'1','3','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('300454654789541254','2016-04-29',0,'2','4','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('300745485455555519','2016-01-18',1,'2','5','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('300132487447474747','2016-02-27',1,'2','6','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('400198745874743524','2016-03-01',1,'3','7','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('400141445874949539','2016-03-01',1,'3','8','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('400141245971545547','2016-03-01',1,'4','9','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('402146545674541523','2016-03-01',1,'4','10','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('402145745276542512','2016-03-01',1,'4','11','1');
insert into legal_entity_account(broj_racuna, datum_otvaranja, vazeci, bank_id, client_client_id, currency_currency_id)values('205145745874545418','2016-03-01',1,'5','12','1');

insert into user(username, password) values ('duca','$2a$10$UteruuYLEll8yHbjCM7Q8OhIvB0EIzT9ErS7Wqqltj1gk37qEHkpW');


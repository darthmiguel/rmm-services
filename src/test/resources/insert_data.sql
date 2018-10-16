delete from service;
delete from device_type;
delete from service_type;
delete from role;
delete from customer;

-- device types
insert into device_type (id, name)
  select 1, 'Windows Workstation';

insert into device_type (id, name)
  select 2, 'Windows Server';

insert into device_type (id, name)
  select 3, 'Mac';

-- service types
insert into service_type (id, name)
  select 1, 'Device';

insert into service_type (id, name)
  select 2, 'Antivirus';

insert into service_type (id, name)
  select 3, 'Cloudberry';

insert into service_type (id, name)
  select 4, 'PSA';

insert into service_type (id, name)
  select 5, 'TeamViewer';

-- users
insert into customer (id, login, name, password, enabled)
  select 1, 'mmarmol', 'Miguel Marmol', '$2a$10$w/oUvhPPGXPW4nvAsaVcbexTzWH1rI5lKR.h4e1YTf88JdE2ztYvq', true;

insert into customer (id, login, name, password, enabled)
  select 2, 'emestanza', 'Erika Mestanza', '$2a$10$kpbQKb5fjmsC6.KkQge/ruPtR5d1LyQiNWXWdrK30nRvcaEL1lH/S', true;

insert into customer (id, login, name, password, enabled)
  select 3, 'ifaier', 'Iris Faier', '$2a$10$1SGgXNIifaSGczgvK18q.eJyiJBFp4hJY0Tcf2mZaISSc/YpbJuF.', true;

insert into customer (id, login, name, password, enabled)
  select 4, 'ppanama', 'Patricia Panama', '$2a$10$1SGgXNIifaSGczgvK18q.eJyiJBFp4hJY0Tcf2mZaISSc/YpbJuF.', true;

insert into customer (id, login, name, password, enabled)
  select 5, 'billUser', 'Bill User', '$2a$10$1SGgXNIifaSGczgvK18q.eJyiJBFp4hJY0Tcf2mZaISSc/YpbJuF.', true;

--available services
insert into service (id, cost, service_type_id, device_type_id)
  select 1, 4, 1, 1;

insert into service (id, cost, service_type_id, device_type_id)
  select 2, 4, 1, 2;

insert into service (id, cost, service_type_id, device_type_id)
  select 3, 4, 1, 3;

insert into service (id, cost, service_type_id, device_type_id)
  select 4, 5, 2, 1;

insert into service (id, cost, service_type_id, device_type_id)
  select 5, 5, 2, 2;

insert into service (id, cost, service_type_id, device_type_id)
  select 6, 7, 2, 3;

insert into service (id, cost, service_type_id, device_type_id)
  select 7, 3, 3, 1;

insert into service (id, cost, service_type_id, device_type_id)
  select 8, 3, 3, 2;

insert into service (id, cost, service_type_id, device_type_id)
  select 9, 3, 3, 3;

insert into service (id, cost, service_type_id, device_type_id)
  select 10, 2, 4, 1;

insert into service (id, cost, service_type_id, device_type_id)
  select 11, 2, 4, 2;

insert into service (id, cost, service_type_id, device_type_id)
  select 12, 2, 4, 3;

insert into service (id, cost, service_type_id, device_type_id)
  select 13, 1, 5, 1;

insert into service (id, cost, service_type_id, device_type_id)
  select 14, 1, 5, 2;

insert into service (id, cost, service_type_id, device_type_id)
  select 15, 1, 5, 3;

-- device
insert into device(id, system_name, customer_id, device_type_id)
  select 100, 'Device Test', 1, 1;
-- monthly cost
insert into service_device(id, device_id, service_id)
  select 100, 100, 1;

-- device
insert into device(id, system_name, customer_id, device_type_id)
  select 101, 'Device Test', 2, 1;
-- monthly cost
insert into service_device(id, device_id, service_id)
  select 101, 101, 1;

-- device
insert into device(id, system_name, customer_id, device_type_id)
  select 102, 'Device Test', 3, 1;
-- monthly cost
insert into service_device(id, device_id, service_id)
  select 102, 102, 1;

insert into service_device(id, device_id, service_id)
  select 103, 102, 4;


-- data for bill

--device 1
insert into device(id, system_name, customer_id, device_type_id)
  select 200, 'Device for Bill Win 1', 5, 1;
--monthly cost
insert into service_device(id, device_id, service_id)
  select 200, 200, 1;
--antivirus cost
insert into service_device(id, device_id, service_id)
  select 201, 200, 4;
--cloudberry cost
insert into service_device(id, device_id, service_id)
  select 202, 200, 7;
--teamviewer cost
insert into service_device(id, device_id, service_id)
  select 203, 200, 13;

--device 2
insert into device(id, system_name, customer_id, device_type_id)
  select 201, 'Device for Bill Win 2', 5, 1;
--monthly cost
insert into service_device(id, device_id, service_id)
  select 204, 201, 1;
--antivirus cost
insert into service_device(id, device_id, service_id)
  select 205, 201, 4;
--cloudberry cost
insert into service_device(id, device_id, service_id)
  select 206, 201, 7;
--teamviewer cost
insert into service_device(id, device_id, service_id)
  select 207, 201, 13;

--device 3
insert into device(id, system_name, customer_id, device_type_id)
  select 202, 'Device for Bill Mac 1', 5, 3;
--monthly cost
insert into service_device(id, device_id, service_id)
  select 208, 202, 3;
--antivirus cost
insert into service_device(id, device_id, service_id)
  select 209, 202, 6;
--cloudberry cost
insert into service_device(id, device_id, service_id)
  select 210, 202, 9;
--teamviewer cost
insert into service_device(id, device_id, service_id)
  select 211, 202, 15;

--device 4
insert into device(id, system_name, customer_id, device_type_id)
  select 203, 'Device for Bill Mac 2', 5, 3;
--monthly cost
insert into service_device(id, device_id, service_id)
  select 212, 203, 3;
--antivirus cost
insert into service_device(id, device_id, service_id)
  select 213, 203, 6;
--cloudberry cost
insert into service_device(id, device_id, service_id)
  select 214, 203, 9;
--teamviewer cost
insert into service_device(id, device_id, service_id)
  select 215, 203, 15;

--device 5
insert into device(id, system_name, customer_id, device_type_id)
  select 204, 'Device for Bill Mac 3', 5, 3;
--monthly cost
insert into service_device(id, device_id, service_id)
  select 216, 204, 3;
--antivirus cost
insert into service_device(id, device_id, service_id)
  select 217, 204, 6;
--cloudberry cost
insert into service_device(id, device_id, service_id)
  select 218, 204, 9;
--teamviewer cost
insert into service_device(id, device_id, service_id)
  select 219, 204, 15;


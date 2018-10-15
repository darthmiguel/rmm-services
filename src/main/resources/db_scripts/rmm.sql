-- device types
insert into device_type (id, name)
  select 1, 'Windows Workstation'
where not exists (
  select * from device_type where id = 1
);

insert into device_type (id, name)
  select 2, 'Windows Server'
where not exists (
  select * from device_type where id = 2
);

insert into device_type (id, name)
  select 3, 'Mac'
where not exists (
  select * from device_type where id = 3
);

-- service types
insert into service_type (id, name)
  select 1, 'Device'
where not exists (
  select * from service_type where id = 1
);

insert into service_type (id, name)
  select 2, 'Antivirus'
where not exists (
  select * from service_type where id = 2
);

insert into service_type (id, name)
  select 3, 'Cloudberry'
where not exists (
  select * from service_type where id = 3
);

insert into service_type (id, name)
  select 4, 'PSA'
where not exists (
  select * from service_type where id = 4
);

insert into service_type (id, name)
  select 5, 'TeamViewer'
where not exists (
  select * from service_type where id = 5
);


-- users
insert into customer (id, login, name, password, enabled)
  select 1, 'mmarmol', 'Miguel Marmol', '$2a$10$w/oUvhPPGXPW4nvAsaVcbexTzWH1rI5lKR.h4e1YTf88JdE2ztYvq', true
where not exists (
  select * from customer where id = 1
);

insert into customer (id, login, name, password, enabled)
  select 2, 'emestanza', 'Erika Mestanza', '$2a$10$kpbQKb5fjmsC6.KkQge/ruPtR5d1LyQiNWXWdrK30nRvcaEL1lH/S', true
where not exists (
  select * from customer where id = 2
);

insert into customer (id, login, name, password, enabled)
  select 3, 'ifaier', 'Iris Faier', '$2a$10$1SGgXNIifaSGczgvK18q.eJyiJBFp4hJY0Tcf2mZaISSc/YpbJuF.', true
where not exists (
  select * from customer where id = 3
);

insert into role (id, role_name, customer_id)
  select 1, 'ROLE_USER', 1
where not exists (
  select * from role where id = 1
);

insert into role (id, role_name, customer_id)
  select 2, 'ROLE_USER', 2
where not exists (
  select * from role where id = 2
);

insert into role (id, role_name, customer_id)
  select 3, 'ROLE_USER', 3
where not exists (
  select * from role where id = 3
);

--available services
insert into service (id, cost, service_type_id, device_type_id)
  select 1, 4, 1, 1
where not exists (
  select * from service where id = 1
);

insert into service (id, cost, service_type_id, device_type_id)
  select 2, 4, 1, 2
where not exists (
  select * from service where id = 2
);

insert into service (id, cost, service_type_id, device_type_id)
  select 3, 4, 1, 3
where not exists (
  select * from service where id = 3
);

insert into service (id, cost, service_type_id, device_type_id)
  select 4, 5, 2, 1
where not exists (
  select * from service where id = 4
);

insert into service (id, cost, service_type_id, device_type_id)
  select 5, 5, 2, 2
where not exists (
  select * from service where id = 5
);

insert into service (id, cost, service_type_id, device_type_id)
  select 6, 7, 2, 3
where not exists (
  select * from service where id = 6
);

insert into service (id, cost, service_type_id, device_type_id)
  select 7, 3, 3, 1
where not exists (
  select * from service where id = 7
);

insert into service (id, cost, service_type_id, device_type_id)
  select 8, 3, 3, 2
where not exists (
  select * from service where id = 8
);

insert into service (id, cost, service_type_id, device_type_id)
  select 9, 3, 3, 3
where not exists (
  select * from service where id = 9
);

insert into service (id, cost, service_type_id, device_type_id)
  select 10, 2, 4, 1
where not exists (
  select * from service where id = 10
);

insert into service (id, cost, service_type_id, device_type_id)
  select 11, 2, 4, 2
where not exists (
  select * from service where id = 11
);

insert into service (id, cost, service_type_id, device_type_id)
  select 12, 2, 4, 3
where not exists (
  select * from service where id = 12
);

insert into service (id, cost, service_type_id, device_type_id)
  select 13, 1, 5, 1
where not exists (
  select * from service where id = 13
);

insert into service (id, cost, service_type_id, device_type_id)
  select 14, 1, 5, 2
where not exists (
  select * from service where id = 14
);

insert into service (id, cost, service_type_id, device_type_id)
  select 15, 1, 5, 3
where not exists (
  select * from service where id = 15
);
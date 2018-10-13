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

-- users
insert into customer (id, login, name, password)
  select 1, 'mmarmol', 'Miguel Marmol', '$2a$10$w/oUvhPPGXPW4nvAsaVcbexTzWH1rI5lKR.h4e1YTf88JdE2ztYvq'
where not exists (
  select * from customer where id = 1
);

insert into customer (id, login, name, password)
  select 2, 'emestanza', 'Erika Mestanza', '$2a$10$kpbQKb5fjmsC6.KkQge/ruPtR5d1LyQiNWXWdrK30nRvcaEL1lH/S'
where not exists (
  select * from customer where id = 2
);

insert into customer (id, login, name, password)
  select 3, 'ifaier', 'Iris Faier', '$2a$10$1SGgXNIifaSGczgvK18q.eJyiJBFp4hJY0Tcf2mZaISSc/YpbJuF.'
where not exists (
  select * from customer where id = 3
);

--available services
-- insert into service (id, cost, name, type_id)
--   select 1, 4, 'Windows Workstation Device', 1
-- where not exists (
--   select * from service where id = 1
-- );

-- insert into service (id, cost, name, type_id)
--   select 2, 4, 'Windows Server Device', 2
-- where not exists (
--   select * from service where id = 2
-- );

-- insert into service (id, cost, name, type_id)
--   select 3, 4, 'Mac Device', 3
-- where not exists (
--   select * from service where id = 3
-- );

-- insert into service (id, cost, name, type_id)
--   select 4, 5, 'Windows Workstation Antivirus', 1
-- where not exists (
--   select * from service where id = 4
-- );

-- insert into service (id, cost, name, type_id)
--   select 5, 5, 'Windows Server Antivirus', 2
-- where not exists (
--   select * from service where id = 5
-- );

-- insert into service (id, cost, name, type_id)
--   select 6, 7, 'Mac Antivirus', 3
-- where not exists (
--   select * from service where id = 6
-- );

-- insert into service (id, cost, name, type_id)
--   select 7, 3, 'Windows Workstation Cloudberry', 1
-- where not exists (
--   select * from service where id = 7
-- );

-- insert into service (id, cost, name, type_id)
--   select 8, 3, 'Windows Server Cloudberry', 2
-- where not exists (
--   select * from service where id = 8
-- );

-- insert into service (id, cost, name, type_id)
--   select 9, 3, 'Mac Cloudberry', 3
-- where not exists (
--   select * from service where id = 9
-- );

-- insert into service (id, cost, name, type_id)
--   select 10, 2, 'Windows Workstation PSA', 1
-- where not exists (
--   select * from service where id = 10
-- );

-- insert into service (id, cost, name, type_id)
--   select 11, 2, 'Windows Server PSA', 2
-- where not exists (
--   select * from service where id = 11
-- );

-- insert into service (id, cost, name, type_id)
--   select 12, 2, 'Mac PSA', 3
-- where not exists (
--   select * from service where id = 12
-- );

-- insert into service (id, cost, name, type_id)
--   select 13, 1, 'Windows Workstation TeamViewer', 1
-- where not exists (
--   select * from service where id = 13
-- );

-- insert into service (id, cost, name, type_id)
--   select 14, 1, 'Windows Server TeamViewer', 2
-- where not exists (
--   select * from service where id = 14
-- );

-- insert into service (id, cost, name, type_id)
--   select 15, 1, 'Mac TeamViewer', 3
-- where not exists (
--   select * from service where id = 15
-- );

--- OTHER MODEL

insert into service (id, cost, name)
  select 1, 4, 'Device'
where not exists (
  select * from service where id = 1
);

insert into service (id, cost, name)
  select 2, 5, 'Windows Antivirus'
where not exists (
  select * from service where id = 2
);

insert into service (id, cost, name)
  select 3, 7, 'Mac Antivirus'
where not exists (
  select * from service where id = 3
);

insert into service (id, cost, name)
  select 4, 3, 'Cloudberry'
where not exists (
  select * from service where id = 4
);

insert into service (id, cost, name)
  select 5, 2, 'PSA'
where not exists (
  select * from service where id = 5
);

insert into service (id, cost, name)
  select 6, 1, 'Teamviewer'
where not exists (
  select * from service where id = 6
);
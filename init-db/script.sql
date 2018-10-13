create user ninjarmm
  with
    encrypted password 'ninjarmm'
    login
    superuser
    inherit
    createdb
    createrole
    replication;

create database ninjarmm
  with
    owner = ninjarmm
    encoding = 'UTF8'
    connection limit = -1;
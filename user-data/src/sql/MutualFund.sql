create table if not exists mutual_fund(
    id integer primary key,
    mf_code text not null unique,
    mf_name text not null unique,
    amc text not null,
    type text not null,
    created_at timestamp not null default current_timestamp,
    modified_at timestamp not null default current_timestamp,
    active boolean not null default true
);
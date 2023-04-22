CREATE TABLE calendar_dates
(
    id             serial  NOT NULL,
    date           timestamp WITHOUT TIME ZONE,
    service_id     varchar(255),
    exception_type integer,
    CONSTRAINT pk_calendar_dates PRIMARY KEY (id)
);

CREATE TABLE route
(
    id               integer NOT NULL,
    agency_id        integer,
    route_id         integer,
    route_short_name varchar(255),
    route_long_name  varchar(255),
    route_type       integer,
    route_sort_order integer,
    CONSTRAINT pk_route PRIMARY KEY (id)
);

CREATE TABLE frequencies
(
    id           integer NOT NULL,
    trip_id      varchar(255),
    start_time   time WITHOUT TIME ZONE,
    end_time     time WITHOUT TIME ZONE,
    headway_secs integer,
    exact_times  integer,
    CONSTRAINT pk_frequencies PRIMARY KEY (id)
);
drop table if exists frequencies;
drop table if exists stop_time;
drop table if exists stop;
drop table if exists calendar_dates;
drop table if exists trip;
drop table if exists shape;
drop table if exists route;


CREATE TABLE calendar_date
(
    id             BIGINT NOT NULL,
    date           TIMESTAMP WITHOUT TIME ZONE,
    service_id_id  BIGINT,
    exception_type INTEGER,
    CONSTRAINT pk_calendar_dates PRIMARY KEY (id)
);

CREATE TABLE route
(
    route_id         integer NOT NULL,
    route_short_name varchar(255),
    route_long_name  varchar(255),
    route_type       integer,
    route_sort_order integer,
    CONSTRAINT pk_route PRIMARY KEY (route_id)
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

CREATE TABLE shape
(
    id                 BIGINT           NOT NULL,
    gtfs_id            BIGINT           NOT NULL,
    pt_sequence        INTEGER          NOT NULL,
    distance_travelled DOUBLE PRECISION NOT NULL,
    pt_latitude        DOUBLE PRECISION NOT NULL,
    pt_longitude       DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_shape PRIMARY KEY (id)
);


CREATE TABLE trip
(
    id                    BIGINT NOT NULL,
    route_id_route_id     BIGINT,
    service_id            BIGINT NOT NULL,
    trip_id               VARCHAR(255),
    head_sign             VARCHAR(255),
    direction_id          INTEGER,
    shape_id_id           BIGINT,
    wheelchair_accessible INTEGER,
    bike_allowed          INTEGER,
    CONSTRAINT pk_trip PRIMARY KEY (id)
);


CREATE TABLE stop_time
(
    id                   BIGINT NOT NULL,
    trip_id_id           BIGINT,
    arrival_time         time WITHOUT TIME ZONE,
    departure_time       time WITHOUT TIME ZONE,
    stop_id_id           BIGINT,
    stop_sequence        INTEGER,
    pickup_type          INTEGER,
    dropoff_type         INTEGER,
    shape_dist_travelled DOUBLE PRECISION,
    CONSTRAINT pk_stop_time PRIMARY KEY (id)
);

CREATE TABLE stop
(
    id                BIGINT NOT NULL,
    stop_id           BIGINT,
    stop_name         VARCHAR(255),
    stop_lat          DOUBLE PRECISION,
    stop_lon          DOUBLE PRECISION,
    location_type     INTEGER,
    parent_station_id BIGINT,
    zone_id           BIGINT,
    CONSTRAINT pk_stop PRIMARY KEY (id)
);

ALTER TABLE calendar_date
    ADD CONSTRAINT FK_CALENDAR_DATES_ON_SERVICEID FOREIGN KEY (service_id_id) REFERENCES trip (id);

ALTER TABLE stop
    ADD CONSTRAINT FK_STOP_ON_PARENTSTATION FOREIGN KEY (parent_station_id) REFERENCES stop (id);

ALTER TABLE stop_time
    ADD CONSTRAINT FK_STOP_TIME_ON_STOPID FOREIGN KEY (stop_id_id) REFERENCES stop (id);

ALTER TABLE stop_time
    ADD CONSTRAINT FK_STOP_TIME_ON_TRIPID FOREIGN KEY (trip_id_id) REFERENCES trip (id);

ALTER TABLE trip
    ADD CONSTRAINT FK_TRIP_ON_ROUTEID_ROUTE FOREIGN KEY (route_id_route_id) REFERENCES route (route_id);

ALTER TABLE trip
    ADD CONSTRAINT FK_TRIP_ON_SHAPEID FOREIGN KEY (shape_id_id) REFERENCES shape (id);

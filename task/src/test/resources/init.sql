CREATE SEQUENCE task_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

CREATE TABLE Task
(
    id              BIGINT PRIMARY KEY DEFAULT nextval('task_seq'),
    version         BIGINT,
    pattern         VARCHAR(255),
    input           VARCHAR(255),
    status          VARCHAR(20),
    progress        INT,
    result_position INT,
    result_typos    INT
);
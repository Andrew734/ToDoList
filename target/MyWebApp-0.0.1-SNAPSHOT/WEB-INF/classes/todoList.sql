CREATE TABLE projects (
		id SERIAL PRIMARY KEY,
		name varchar(20) NOT NULL

);

CREATE TABLE task (
		id SERIAL PRIMARY KEY,
		name varchar(20) NOT NULL
);


CREATE TABLE projects_tasks (
		project_id integer NOT NULL,
		task_id integer NOT NULL,
		FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
                FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE
);

INSERT INTO projects VALUES
        (NEXTVAL('projects_id_seq'), 'SR-01'),
        (NEXTVAL('projects_id_seq'), 'SR-02');

INSERT INTO task VALUES
        (NEXTVAL('task_id_seq'), 'Coillot'),
        (NEXTVAL('task_id_seq'), 'Eckford'),
        (NEXTVAL('task_id_seq'), 'Arango'),
        (NEXTVAL('task_id_seq'), 'Robstone'); 
 
INSERT INTO projects_tasks VALUES
        (1, 1),
        (1, 2),
        (2, 3),
        (2, 4);

CREATE TABLE projects (
		id SERIAL PRIMARY KEY,
		name varchar(30) NOT NULL

);

CREATE TABLE task (
		id SERIAL PRIMARY KEY,
		name varchar(30) NOT NULL,
		date TIMESTAMP
);


CREATE TABLE projects_tasks (
		project_id integer NOT NULL,
		task_id integer NOT NULL,
		FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
                FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE
);

INSERT INTO projects VALUES
        (NEXTVAL('projects_id_seq'), 'Make the test task'),
        (NEXTVAL('projects_id_seq'), 'For home');

INSERT INTO task VALUES
        (NEXTVAL('task_id_seq'), 'Write code', '2017-05-16 11:30:00'),
        (NEXTVAL('task_id_seq'), 'Fix bugs', '2017-05-12 11:30:00'),
        (NEXTVAL('task_id_seq'), 'Eat', '2017-05-13 11:30:00'),
        (NEXTVAL('task_id_seq'), 'Clean dish', '2017-05-14 11:30:00'); 
 
INSERT INTO projects_tasks VALUES
        (1, 1),
        (1, 2),
        (2, 3),
        (2, 4);
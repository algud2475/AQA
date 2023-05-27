SELECT (SELECT name FROM project WHERE id = test.project_id) AS project, name, MIN(TIMESTAMPDIFF(SECOND, start_time, end_time)) as min_working_time FROM test GROUP BY name ORDER BY project, name;


SELECT browser, COUNT(id) AS tests_count FROM test WHERE browser IN ('chrome', 'firefox') GROUP BY browser;
DELETE FROM posts;

ALTER TABLE posts ALTER COLUMN id RESTART WITH 1;

INSERT INTO posts (title, content) VALUES
('topic_1', 'thdrthdbth'),
('topic_2', 'dfghndhd'),
('topic_3', 'imi ty tt htj ');
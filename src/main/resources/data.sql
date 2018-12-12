DELETE FROM posts;

alter sequence blog_seq restart with 1;

INSERT INTO posts (title, content) VALUES
('topic_1', 'thdrthdbth'),
('topic_2', 'dfghndhd'),
('topic_3', 'imi ty tt htj ');
CREATE SCHEMA reference AUTHORIZATION sa;
CREATE TABLE reference.SUBJECT (
    SUBJECT_ID BIGINT NOT NULL auto_increment,
    SUBJECT_TITLE VARCHAR2(500),
    SUBJECT_DURATION INT,
    PRIMARY KEY(SUBJECT_ID)
    );
    
CREATE TABLE reference.BOOK (
	BOOK_ID BIGINT auto_increment,
	BOOK_TITLE VARCHAR2(500),
	BOOK_PRICE DECIMAL,
	BOOK_VOLUME INT,
	BOOK_PUBLISHED_DT DATE,
	SUBJECT_ID INT,
	PRIMARY KEY(BOOK_ID),
	FOREIGN KEY (subject_id) 
    REFERENCES reference.subject(subject_id)
);

--ALTER TABLE reference.book
--    ADD FOREIGN KEY (SUBJECT_ID) 
---    REFERENCES reference.SUBJECT(SUBJECT_ID);
ALTER TABLE UrlToSync ADD updateTime DATETIME DEFAULT '1970-01-01 08:00:00' NOT NULL;
ALTER TABLE UrlToSync ADD message VARCHAR(100) DEFAULT '' NOT NULL;

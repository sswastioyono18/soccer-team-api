CREATE DATABASE IF NOT EXISTS soccer_api;
use soccer_api;

CREATE TABLE `team` (
	`team_id` BIGINT NOT NULL,
	`team_name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`team_id`)
);

CREATE TABLE `player` (
	`player_id` BIGINT NOT NULL AUTO_INCREMENT,
	`player_no` INT(2) NOT NULL,
	`player_name` VARCHAR(50) NOT NULL,
	`team_id` BIGINT NOT NULL,
	PRIMARY KEY (`player_id`),
	FOREIGN KEY (team_id) REFERENCES team(team_id)
);

ALTER TABLE player
ADD CONSTRAINT uq#player_no_team_id UNIQUE (player_no, team_id);

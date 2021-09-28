create database selab character set utf8 default collate utf8_general_ci;

select * from member;

UPDATE member
SET member_role = 'ADMIN'
WHERE member_id = 1;
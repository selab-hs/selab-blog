create
database selab character set utf8 default collate utf8_general_ci;

drop
database selab;

useselab;

show
tables;

UPDATE member
SET member_role = 'ADMIN'
WHERE member_id = 1;
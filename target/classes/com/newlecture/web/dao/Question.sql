select * from Question
select * from SubjectUnit
select * from QuestionLevel

create table SubjectUnit(
	id bigint,
	subjectId bigint,
	name nvarchar(MAX)
)
create table QuestionLevel(
	id bigint,
	name nvarchar(MAX)
)

insert into member(id, name, email, pwd)
values('flwj', '홍성철', 'flwj@naver.com', '$2a$10$zpE1ThBwaRlZM2uMMShksurhrRjw/QtUZXB4cfON4.owLTFqkyQx.')

commit;

insert into member(id, name, email, pwd)
values('seol', '슬사장', 'seol@naver.com', '$2a$10$zpE1ThBwaRlZM2uMMShksurhrRjw/QtUZXB4cfON4.owLTFqkyQx.');


select top 5 * from Member

select * from Member
order by regDate desc
offset 5 rows
fetch next 5 rows only;

select * from Member
where name like '%홍%'
order by regDate desc
offset #{(param3-1)}*10 rows
fetch next 10 rows only

//커럼 3개를 지정해줘야 한다.
select id, password, enabled
from ?;




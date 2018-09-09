# 테이블 컬럼 정보 보기
# show full columns from 테이블이름;

# 테이블에 저장된 데이터 보기
# select * from 테이블이름;

# 테이블 삭제하기
# drop table 테이블이름;

# 외래키 추가
alter table 추가할테이블이름 add
constraint 제약조건명 foreign key (컬럼명) references 부모테이블명 (PK컬럼명) on delete cascade;

alter table mi_teacher add
constraint fk_miteacher_migroup_groupname foreign key (group_name) references mi_group (group_name) on delete cascade;

alter table mi_student add
constraint fk_mistudent_migroup_groupname foreign key (group_name) references mi_group (group_name) on delete cascade;

alter table music_template_assignment add
constraint fk_musictemplateassignment_mifile_innerfilename foreign key (inner_filename) references mi_file (inner_filename) on delete cascade;

# 외래키 확인
select * from information_schema.table_constraints where table_name='테이블이름';
select * from information_schema.table_constraints where constraint_schema = '데이터베이스명';

# 왜래키 옵션
# on delete cascade : 외래 키에서 참조하는 키가 포함된 행을 삭제하려고 하면 해당 외래 키가 포함되어 있는 모든 행도 삭제
# on update cascade : 외래 키에서 참조하는 키 값이 포함된 행에서 키 값을 업데이트 하면 해당 외래 키를 구성하는 모든 값도 키에 지정된 새 값으로 업데이트 되도록 지정

# left join
SELECT column_name(s)
FROM table1
LEFT JOIN table2 ON table1.column_name = table2.column_name;

select email, username from mi_user left join music_template on mi_user.email = music_template.owner;

# right join
SELECT column_name(s)
FROM table1
RIGHT JOIN table2 ON table1.column_name = table2.column_name;

select music_template_id,
owner, music_title, musician, guide
from mi_user right join music_template
on mi_user.email = music_template.owner
where mi_user.email='namolppam@pocket.mon';

# id 가 두 테이블의 유일한 공통 열 이름인 경우
select column_name(s)
from 테이블1 natural join 테이블2

select music_template_id, play_time, comment
from music_template natural join music_template_guide
where music_template_guide.music_template_id = 1;

select inner_filename, music_template_id,
to_do_count, done_count, success_percent
from music_template natural join music_template_assignment
where music_template.music_template_id = 1;

select music_template_id, owner, music_title, musician
from music_template natural join mi_notification
where music_template.music_template_id = 1;

select owner, inner_filename, outter_filename
from music_template_practice natural join mi_file
where music_template_practice.inner_filename = 'namolnamolsong.mp3';

# full outer join
SELECT column_name(s)
FROM table1
FULL OUTER JOIN table2 ON table1.column_name = table2.column_name;

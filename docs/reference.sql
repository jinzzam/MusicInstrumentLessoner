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

# right join
SELECT column_name(s)
FROM table1
RIGHT JOIN table2 ON table1.column_name = table2.column_name;

# full outer join
SELECT column_name(s)
FROM table1
FULL OUTER JOIN table2 ON table1.column_name = table2.column_name;

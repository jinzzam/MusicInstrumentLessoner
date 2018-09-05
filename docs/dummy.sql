# 테이블 컬럼 정보 보기
# show full columns from 테이블이름;

# mi_user
insert into mi_user values('jinzzam@namol.ppam', '123', '박유진');
insert into mi_user values('namolppam@pocket.mon', '1234', '나몰빼미');

# select * from mi_user WHERE username like '%유%';

# delete table mi_user;

# music_template
insert into music_template values(NULL, 'namolppam@pocket.mon', '나몰나몰송', '나몰빼미');
insert into music_template values(NULL, 'jinzzam@namol.ppam', '유진송', '노래짱짱유진');

# music_template_guide
insert into music_template_guide values(1, '00:03:49', '나몰나몰나몰나모올몰몰나몰');
insert into music_template_guide values(2, '00:04:01', '깨끗하게 밝게 자신있게');

# music_template_practice
insert into music_template_practice values(1, 1, 'namolppam@pocket.mon', 'namolnamolsong.mp3');

# mi_notification
insert into mi_notification values(1, 1, NULL, 'template', '선생님이 템플릿을 등록하였습니다.');
insert into mi_notification values(2, 2, NULL, 'template', '선생님이 과제를 등록하였습니다.');

# music_template_assignment
insert into music_template_assignment values('namolnamolsong.mp3', 1);

# music_template_wrong
insert into music_template_wrong values('namolnamolsong.mp3', '00:00:30', '00:01:01');
insert into music_template_wrong values('namolnamolsong.mp3', '00:02:02', '00:02:19');

# mi_file
insert into mi_file values('namolppam@pocket.mon', 'namolnamolsong.mp3', '/storage/self/primary/Music/놀람 교향곡/T/namolnamolsong.mp3');

# mi_group
insert into mi_group values('피아노리브레 강남센터',
  '모든 임직원들이 음악을 전공한 전문가들이며 격이 다른 커리큘럼으로 레슨만 받아도 실력이 향샹되는 검증된 교육시스템을 갖추고 있습니다.',
  '플룻, 리코더, 바이올린',
  '오케스트라, 클래식, 관악, 현악'
);

# mi_teacher
insert into mi_teacher values('namolppam@pocket.mon', '피아노리브레 강남센터');

# mi_student
insert into mi_student values('jinzzam@namol.ppam', '피아노리브레 강남센터');

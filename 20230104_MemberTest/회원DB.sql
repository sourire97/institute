
-- 테이블 생성

create table member(

	mem_idx		int,
	mem_name	varchar2(100) 	not null,
	mem_id		varchar2(100) 	not null,
	mem_pwd		varchar2(100) 	not null,
	mem_zipcode	varchar2(10) 	not null,
	mem_address	varchar2(500) 	not null,
	mem_grade	varchar2(100) 	default '일반',
	mem_regdate	date			default sysdate
	
	);
	
-- 기본키
alter table member
	add constraint pk_member_mem_idx primary key(mem_idx);
	
-- unique제약
alter table member
	add constraint unique_mem_id unique(mem_id);

-- check 제약
alter table member
	add constraint check_member_mem_grade check(mem_grade in('일반', '관리자'));
	
-- sample data
insert into member values( (select nvl(max(mem_idx),0)+1 from member),
							'일길동',
							'one',
							'1234',
							'12345',
							'서울시 관악구 시흥대로552',
							default,
							default);

insert into member values( (select nvl(max(mem_idx),0)+1 from member),
							'김관리',
							'admin',
							'1234',
							'12345',
							'서울시 관악구 시흥대로552',
							'관리자',
							default);

--JDBC용 SQL
insert into  member values( (select nvl(max(mem_idx),0)+1 from member),?,?,?,?,?,default,default)                           

insert into  member values( (select nvl(max(mem_idx),0)+1 from member),
                            '김관리',
                            'admin',
                            '1234',
                            '12345',
                            '서울시 관악구 시흥대로552',
                            '관리자',
                            default  
                           );

select * from member

select * from member where mem_idx=1

select * from member where mem_id='admin'

select * from member where mem_id='one'

delete from member

update member set mem_name=?, mem_pwd=?, mem_zipcode=?, mem_address=? where mem_idx=?
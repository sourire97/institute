/*

--������(Sequence) : �Ϸù�ȣ������ü
                (��������: seq_table��_�÷���) 
create sequence seq_sungtb_idx

--����������
drop sequence seq_sungtb_idx

--������ �����
select seq_sungtb_idx.nextVal, seq_sungtb_idx.currVal from dual



--�������̺�

create table sungtb
(
   idx  int,					--�Ϸù�ȣ 
   name varchar2(100) not null,	--�̸�
   kor  int default 0,			--����
   eng  int default 0,			--����
   mat  int default 0			--����
)

--�⺻Ű����
alter table sungtb
  add constraint pk_sungtb_idx primary key(idx) ;
  
--check����(����/����/����:0~100)  

alter table sungtb
  add constraint ck_sungtb_kor  check( kor between 0 and 100 );
  
alter table sungtb
  add constraint ck_sungtb_eng  check( eng between 0 and 100 );
  
alter table sungtb
  add constraint ck_sungtb_mat  check( mat between 0 and 100 );  

--sample data
insert into sungtb  values( seq_sungtb_idx.nextVal,'�ϱ浿',88,99,77) ;
insert into sungtb  values( seq_sungtb_idx.nextVal,'�̱浿',98,99,77) ;
insert into sungtb  values( seq_sungtb_idx.nextVal,'��浿',98,99,97) ;
insert into sungtb  values( seq_sungtb_idx.nextVal,'��浿',100,100,100) ;
insert into sungtb  values( seq_sungtb_idx.nextVal,'���浿',78,79,77) ;

commit



--���� ��ȸ�� �����

create or replace view sungtb_view
as
	select
	   idx,name,kor,eng,mat, 
	   (kor+eng+mat) as tot,
	   round((kor+eng+mat)/3,1) as avg,
	   rank() over(order by (kor+eng+mat) desc) as rank
	from sungtb
    order by idx

-- SQL Injection(����) : �׻��������� ���Խ�Ų��  (or 1=1)

select * from sungtb_view where idx=10   or 1=1

--����
delete from sungtb where idx=1


select * from sungtb_view














*/

create sequence seq_visit_idx

--���̺����
create table visit
(	idx int, --�Ϸù�ȣ
	name varchar2(100) not null, --�ۼ���
	content varchar2(2000) not null, --����
	pwd varchar2(100) not null, --��й�ȣ
	ip varchar2(100) not null, --������
	regdate date not null, --�ۼ�����
	modifydate date not null --�ֱټ�������
)

--�⺻Ű
alter table visit
   add constraint  pk_visit_idx  primary key(idx);

--sample data
insert into visit values( seq_visit_idx.nextVal,
                          '�ϱ浿',
                          '���� 1��',
                          '1234',
                          '192.168.0.23',
                          sysdate,
                          sysdate
                            );
insert into visit values( seq_visit_idx.nextVal,
                          '�̱浿',
                          '�ƽ��� ���� 1���� �� �־��µ�',
                          '1234',
                          '192.168.0.23',
                          sysdate,
                          sysdate
                            );                
commit                                                        
                            
select * from visit
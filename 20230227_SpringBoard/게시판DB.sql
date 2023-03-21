/*
--일련번호관리객체
  create sequence seq_board_b_idx

--테이블생성
  create table board
  (
      b_idx  int,					--일련번호
      b_subject varchar2(200),		--제목
      b_content clob,				--내용
      b_ip      varchar2(100),		--아이피
      b_regdate date,				--작성일자
      b_readhit int,				--조회수
      b_use    char(1) default 'y',	--사용유무(삭제유무)
      mem_idx   int,				--회원번호(FK)
      mem_name  varchar2(100),		--회원명
      b_ref     int,				--참조글번호
      b_step    int,				--글순서
      b_depth   int					--글깊이
  )

--기본키
  alter table board
     add constraint  pk_board_b_idx  primary key(b_idx);
     
--외래키
  alter table board
     add constraint  fk_board_mem_idx  foreign key(mem_idx) 
                                       references member(mem_idx); 
                                       
  select * from member 
                                                                                   
--새글쓰기
  insert into board values( seq_board_b_idx.nextVal,
                            '내가 1등이다!!',
                            '이번에도 1등이네',
                            '192.168.0.23',
                            sysdate,
                            0,
                            'y',
                            1,
                            '일길동',
                            seq_board_b_idx.currVal,
                            0,
                            0
    );                                           
        
--답글쓰기
    insert into board values( seq_board_b_idx.nextVal,
                            '아쉽네 내가 1등할수 있었는데',
                            '다음에는 내가 1등해야지',
                            '192.168.0.23',
                            sysdate,
                            0,
                            'y',
                            2,
                            '김관리',
                            1,
                            1,
                            1
    );   
    
    insert into board values( seq_board_b_idx.nextVal,
                            '그래 다음엔 네가 1등해',
                            '과연될까?',
                            '192.168.0.23',
                            sysdate,
                            0,
                            'y',
                            1,
                            '일길동',
                            1,
                            2,
                            2
    ); 
                                                                                                                                                                                                                          
                                                   
                                                         
    select * from board order by b_ref desc, b_step asc
     
    commit


*/
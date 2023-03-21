

--장바구니 일련번호
create sequence seq_cart_idx

--장바구니 테이블
create table cart
(
  c_idx  int,
  c_cnt  int,
  p_idx  int,
  mem_idx int
)

--기본키
alter table cart add constraint pk_cart_c_idx primary key(c_idx);

--상품테이블(product)의 p_idx와 p_idx간의 외래키 설정
alter table cart
  add constraint fk_cart_p_idx foreign key(p_idx) 
                               references product(p_idx)
                               on delete cascade;
                               
--회원테이블 mem_idx와 카트테이블의 mem_idx의 외래키 설정
alter table cart
  add constraint fk_cart_mem_idx foreign key(mem_idx)
                                 references member(mem_idx)
                                 on delete cascade ;

select * from product order by p_idx
select * from member  order by mem_idx
--                                            c_cnt p_idx mem_idx
insert into cart values(seq_cart_idx.nextVal,  1,    21,     1  );
insert into cart values(seq_cart_idx.nextVal,  1,    22,     1  );
insert into cart values(seq_cart_idx.nextVal,  1,    23,     3  );

select * from cart

commit

-- Join을 통해서 조회정보를 추출
create or replace view cart_view
as
	select
	   p.p_idx, c.c_idx, p_num, p_name, p_price, p_saleprice,
	   c_cnt, c_cnt* p_saleprice amount, mem_idx
	from product p inner join cart c on p.p_idx = c.p_idx  
	
select * from cart_view  where mem_idx=1 ;

--장바구니 상품의 총계
select sum(amount) from cart_view where mem_idx=1;


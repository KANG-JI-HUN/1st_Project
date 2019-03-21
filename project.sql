-- ■ ■ ■ drop table ■ ■ ■
DROP TABLE CUSTOMER;
DROP TABLE ADMIN;
DROP TABLE FREEBOARD;
DROP TABLE QBOARD;
DROP TABLE ITEMLIST;
DROP TABLE LIKEITEM;
DROP TABLE OAFTER;
DROP TABLE NBOARD;



-- ■ ■ ■ drop sequence ■ ■ ■
DROP SEQUENCE FREEBOARD_SEQ;
DROP SEQUENCE QBOARD_SEQ;
DROP SEQUENCE ITEMLIST_SEQ;
DROP SEQUENCE LIKEITEM_SEQ;
DROP SEQUENCE OAFTER_SEQ;
DROP SEQUENCE NBOARD_SEQ;



-- ★ ★ ★ customer table ★ ★ ★
CREATE TABLE CUSTOMER(
    cId VARCHAR2(30) PRIMARY KEY,
    cPw VARCHAR2(30) NOT NULL,
    cName VARCHAR2(30) NOT NULL,
    cTel VARCHAR2(30) NOT NULL,
    cEmail VARCHAR2(30) NOT NULL,
    cGender VARCHAR2(30) NOT NULL,
    cAddr VARCHAR2(100) NOT NULL);
-- INSERT DATA(join)
INSERT INTO CUSTOMER(CID, CPW, CNAME, CTEL, CEMAIL, CGENDER, CADDR)
    VALUES('AAA', 'AAA', '김태희', '010-0000-0000',
                'AAA@NAVER.COM', 'F', '서울시 강남구');
-- 답글을 달기 위한 MEMBER도 생성(1번째 답변글을 위한)            
INSERT INTO CUSTOMER(CID, CPW, CNAME, CTEL, CEMAIL, CGENDER, CADDR)
    VALUES('ZZZ', 'ZZZ', '장동건', '010-1111-1111',
                'ZZZ@DAUM.NET', 'M', '서울시 서초구');
-- 답글을 달기 위한 MEMBER도 생성(2번째 답변글을 위한)
INSERT INTO CUSTOMER(CID, CPW, CNAME, CTEL, CEMAIL, CGENDER, CADDR)
    VALUES('BBB', 'BBB', '이행인', '010-9999-9999',
                'BBB@GMAIL.COM', 'M', '서울시 동작구');
INSERT INTO CUSTOMER(CID, CPW, CNAME, CTEL, CEMAIL, CGENDER, CADDR)
    VALUES('CCC', 'CCC', '비타맨', '010-2222-2222',
                'CCC@HOT.COM', 'M', '서울시 송파구');
-- id confirm                
SELECT * FROM CUSTOMER WHERE CID='AAA';
-- modify
UPDATE CUSTOMER
    SET CPW = '111',
        CNAME = '김태희',
        CTEL = '010-0000-0000',
        CEMAIL = 'KIM@NAVER.COM',
        CGENDER = 'F',
        CADDR = '서울시 도봉구'
    WHERE CID = 'AAA';
-- 가입한 회원수
SELECT COUNT(*) FROM CUSTOMER;
-- startRow~endRow까지 list
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM(SELECT * FROM CUSTOMER ORDER BY CID) A)
    WHERE RN BETWEEN 2 AND 3;
SELECT * FROM CUSTOMER;
COMMIT;



-- ★ ★ ★ admin table ★ ★ ★
CREATE TABLE ADMIN(
    aId VARCHAR2(30) PRIMARY KEY,
    aPw VARCHAR2(30) NOT NULL,
    aName VARCHAR2(30) NOT NULL);
SELECT * FROM ADMIN;
-- dummy data
INSERT INTO ADMIN VALUES('ADMIN', 'ADMIN', '운영자');
-- admin loginCheck
SELECT * FROM ADMIN WHERE AID='ADMIN' AND APW='ADMIN';
-- admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AID='ADMIN';
COMMIT;



-- ★ ★ ★ freeboard table ★ ★ ★
CREATE SEQUENCE FREEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE FREEBOARD(
    fNo NUMBER(5) PRIMARY KEY,
    cId VARCHAR2(30) REFERENCES CUSTOMER(CID),
    fSubject VARCHAR2(300) NOT NULL,
    fContent VARCHAR2(1000) NOT NULL,
    fFilename VARCHAR2(100),
    fReadcount NUMBER(5) DEFAULT 0,
    fPw VARCHAR2(30) NOT NULL,
    fGroup NUMBER(5) NOT NULL,
    fStep NUMBER(5) NOT NULL,
    fIndent NUMBER(5) NOT NULL,
    fIp VARCHAR2(20) NOT NULL,
    fDate DATE DEFAULT SYSDATE);
-- DUMMY DATA(원글)
INSERT INTO FREEBOARD(FNO, CID, FSUBJECT, FCONTENT, FFILENAME, FPW,
                        FGROUP, FSTEP, FINDENT, FIP)
    VALUES(FREEBOARD_SEQ.NEXTVAL, 'AAA', '만족합니다!', '너무 마음에 드네요', NULL, 'AAA',
            FREEBOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');
SELECT * FROM FREEBOARD;
-- DUMMY DATA(위의 1번글에 대한 첫번째 답변글)
INSERT INTO FREEBOARD(FNO, CID, FSUBJECT, FCONTENT, FFILENAME, FPW,
                        FGROUP, FSTEP, FINDENT, FIP)
    VALUES(FREEBOARD_SEQ.NEXTVAL, 'ZZZ', '답변글입니다', '다행입니다^^', NULL, 'ZZZ',
            1, 1, 1, '192.168.10.151');
SELECT * FROM FREEBOARD ORDER BY FGROUP DESC, FSTEP;
-- DUMMT DATA(위의 1번글에 대한 2번째 답변글)
-- 답변글 추가전 STEP a 수행
UPDATE FREEBOARD SET FSTEP = FSTEP+1
    WHERE FGROUP = 1 AND FSTEP>0;
INSERT INTO FREEBOARD(FNO, CID, FSUBJECT, FCONTENT, FFILENAME, FPW,
                        FGROUP, FSTEP, FINDENT, FIP)
    VALUES(FREEBOARD_SEQ.NEXTVAL, 'BBB', '상품 어떤가요?', '괜찮은가요?', NULL, 'BBB',
            1, 1, 1, '192.168.10.151');
COMMIT;
-- 자유게시판 글목록(startRow부터 endRow까지)
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
        (SELECT * FROM FREEBOARD ORDER BY FGROUP DESC, FSTEP) A)
    WHERE RN BETWEEN 2 AND 3;
-- 자유게시판 글갯수
SELECT COUNT(*) FROM FREEBOARD;
-- 자유게시판 글쓰기(원글)
INSERT INTO FREEBOARD(FNO, CID, FSUBJECT, FCONTENT, FFILENAME, FPW,
                        FGROUP, FSTEP, FINDENT, FIP)
    VALUES(FREEBOARD_SEQ.NEXTVAL, 'AAA', '또올게요', '행복행복', NULL, 'AAA',
            FREEBOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');
-- fReadcount 하나 올리기(조회수)
UPDATE FREEBOARD SET FREADCOUNT = FREADCOUNT + 1 WHERE FNO=1;
-- 글 상세보기(조회수 up + fNo로 dto리턴)
SELECT F.*, CNAME FROM FREEBOARD F, CUSTOMER C
    WHERE F.CID = C.CID AND FNO = 1;
-- fNo로 글 dto보기
SELECT * FROM FREEBOARD WHERE FNO=1;
-- 글 수정하기(FNO, FSUBJECT, FCONTENT, FFILENAME, FPW, FIP, FDATE)
UPDATE FREEBOARD
    SET FSUBJECT = '글제목을바꿨어요',
        FCONTENT = '내용수정하겠습니다',
        FFILENAME = NULL,
        FPW = 'AAA',
        FIP = '192.168.10.151',
        FDATE = SYSDATE
    WHERE FNO=1;
-- 글 삭제하기(FNO로 삭제하기)
DELETE FROM FREEBOARD WHERE FNO=3;
-- 답변글 추가전 STEP a 수행
UPDATE FREEBOARD SET FSTEP = FSTEP + 1
    WHERE FGROUP = 1 AND FGROUP>0;
-- 답변글 쓰기
INSERT INTO FREEBOARD(FNO, CID, FSUBJECT, FCONTENT, FFILENAME, FPW,
                        FGROUP, FSTEP, FINDENT, FIP)
    VALUES(FREEBOARD_SEQ.NEXTVAL, 'AAA', '이젠안녕', '이젠진짜안녕히', NULL, 'AAA',
            FREEBOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');
SELECT * FROM FREEBOARD ORDER BY FGROUP DESC;
COMMIT;



-- ★ ★ ★ qboard table ★ ★ ★
CREATE SEQUENCE QBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE QBOARD(
    qNo NUMBER(5) PRIMARY KEY,
    cId VARCHAR2(30) REFERENCES CUSTOMER(CID),
    aId VARCHAR2(30) REFERENCES ADMIN(AID),
    qSubject VARCHAR2(300) NOT NULL,
    qContent VARCHAR2(1000) NOT NULL,
    qReadcount NUMBER(5) DEFAULT 0,
    qPw VARCHAR2(30) NOT NULL,
    qGroup NUMBER(5) NOT NULL,
    qStep NUMBER(5) NOT NULL,
    qIndent NUMBER(5) NOT NULL,
    qDate DATE DEFAULT SYSDATE);
-- DUMY DATA(원글)
INSERT INTO QBOARD(QNO, CID, QSUBJECT, QCONTENT, QPW,
                    QGROUP, QSTEP, QINDENT)
    VALUES(QBOARD_SEQ.NEXTVAL, 'CCC', '질문있어요', '내용인 즉슨', 'CCC',
            QBOARD_SEQ.CURRVAL, 0, 0);
SELECT * FROM QBOARD;
-- DUMMY DATA(위의 글에 대한 답변글)
INSERT INTO QBOARD(QNO, AID, QSUBJECT, QCONTENT, QPW,
                    QGROUP, QSTEP, QINDENT)
    VALUES(QBOARD_SEQ.NEXTVAL, 'ADMIN', '답변입니다', '내용인 즉슨', 'ADMIN',
            1, 1, 1);
SELECT * FROM QBOARD ORDER BY QGROUP DESC, QSTEP;
-- 답변글 추가전 STEP a 수행
UPDATE QBOARD SET QSTEP = QSTEP+1
    WHERE QGROUP = 1 AND QSTEP>0;
-- 추가 답변글 달기
INSERT INTO QBOARD(QNO, AID, QSUBJECT, QCONTENT, QPW,
                    QGROUP, QSTEP, QINDENT)
    VALUES(QBOARD_SEQ.NEXTVAL, 'ADMIN', '추가답변입니다', '내용인 즉슨', 'ADMIN',
            1, 1, 1);         
COMMIT;
-- 글목록(startRow부터 endRow까지)
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
        (SELECT * FROM QBOARD ORDER BY QGROUP DESC, QSTEP) A)
    WHERE RN BETWEEN 2 AND 3;
-- 글갯수
SELECT COUNT(*) FROM QBOARD;
-- 글쓰기(QNO, CID, QSUBJECT, QCONTENT, QPW, QGROUP, QSTEP, QINDENT)
INSERT INTO QBOARD(QNO, CID, QSUBJECT, QCONTENT, QPW,
                    QGROUP, QSTEP, QINDENT)
    VALUES(QBOARD_SEQ.NEXTVAL, 'AAA', '이건뭔가요?', '내용인 즉슨', 'AAA',
            QBOARD_SEQ.CURRVAL, 0, 0);
-- qReadcount 하나 올리기
UPDATE QBOARD SET QREADCOUNT = QREADCOUNT + 1 WHERE QNO = 1;
--글 상세보기(qNo로 글 dto보기)
SELECT Q.*, CNAME FROM QBOARD Q, CUSTOMER C WHERE Q.CID=C.CID AND QNO=1;
-- 답변글 view + 수정 view (bid로 dto리턴)
SELECT Q.*, CNAME FROM QBOARD Q, CUSTOMER C WHERE Q.CID=C.CID AND QNO=1;
-- 글 수정하기(QNO, QSUBJECT, QCONTENT, QPW, QDATE)
UPDATE QBOARD
    SET QSUBJECT = '답변을 수정할게요',
        QCONTENT = '내용을 수정했어요',
        QPW = 'BBB',
        QDATE = SYSDATE
    WHERE QNO=1;
SELECT * FROM QBOARD;
-- 글 삭제하기(QNO로 삭제하기)
DELETE FROM QBOARD WHERE QNO=3;
-- 답변글 추가전 STEP a 수행
UPDATE QBOARD SET QSTEP = QSTEP + 1
    WHERE QGROUP = 1 AND QGROUP>0;
-- 답변글 쓰기
INSERT INTO QBOARD(QNO, AID, QSUBJECT, QCONTENT, QPW,
                    QGROUP, QSTEP, QINDENT)
    VALUES(QBOARD_SEQ.NEXTVAL, 'ADMIN', '이용해주셔서감사합니다', '안녕히가세요',  'ADMIN',
            QBOARD_SEQ.CURRVAL, 0, 0);
SELECT * FROM QBOARD ORDER BY QGROUP DESC;
COMMIT;



-- ★ ★ ★ itemlist table ★ ★ ★
CREATE SEQUENCE ITEMLIST_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE ITEMLIST(
    iNo NUMBER(5) PRIMARY KEY,
    aId VARCHAR2(30) REFERENCES ADMIN(AID),
    iName VARCHAR2(50) NOT NULL,
    iSubject VARCHAR2(300) NOT NULL,
    iInfo VARCHAR2(1000) NOT NULL,
    iAmount NUMBER(8) NOT NULL,
    iReadcount NUMBER(5) DEFAULT 0,
    iFilename VARCHAR2(100));
-- 상품목록
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
        (SELECT * FROM ITEMLIST ORDER BY INO DESC) A)
    WHERE RN BETWEEN 2 AND 3;
-- 상품갯수
SELECT COUNT(*) FROM ITEMLIST;
-- 조회수 올리기
UPDATE ITEMLIST SET IREADCOUNT = IREADCOUNT + 1 WHERE INO=1;
SELECT * FROM ITEMLIST;
-- 상품 글쓰기(원글)
INSERT INTO ITEMLIST(INO, AID, INAME, ISUBJECT, IINFO, IAMOUNT, IFILENAME)
    VALUES(ITEMLIST_SEQ.NEXTVAL, 'ADMIN', '실버링귀걸이', '특가세일!', '은함량90%귀걸이입니다', '300', NULL);
SELECT * FROM ITEMLIST;
-- 글 상세보기(조회수 UP + iNo로 dto리턴)
SELECT I.*, INAME FROM ITEMLIST I, ADMIN A
    WHERE I.AID=A.AID AND INO =2;
    
SELECT * FROM ITEMLIST;
SELECT * FROM ADMIN;

-- 글 수정하기(INO, INAME, ISUBJECT, IINFO, IAMOUNT, IFILENAME)
UPDATE ITEMLIST
    SET INAME = '카모실버링귀걸이',
        ISUBJECT = '초특가세일!',
        IINFO = '은함량95%입니다',
        IAMOUNT = '200',
        IFILENAME = NULL
    WHERE INO=1;
-- 글 삭제하기(INO로 삭제하기)
DELETE FROM ITEMLIST WHERE INO=1;
COMMIT;



-- ★ ★ ★ likeitem table ★ ★ ★
CREATE SEQUENCE LIKEITEM_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE LIKEITEM(
    lNo NUMBER(5) PRIMARY KEY,
    cId VARCHAR2(30) REFERENCES CUSTOMER(CID),
    iNo NUMBER(5) REFERENCES ITEMLIST(INO),
    lCnt NUMBER(5) NOT NULL);
-- 아이디별 선택한 아이템목록
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
        (SELECT * FROM LIKEITEM ORDER BY LNO DESC) A)
    WHERE RN BETWEEN 2 AND 3;
-- 선택한 아이템 글갯수
SELECT COUNT(*) FROM LIKEITEM;
-- 선택한 아이템넣기
INSERT INTO LIKEITEM(LNO, CID, INO, LCNT)
    VALUES(LIKEITEM_SEQ.NEXTVAL, 'AAA', '10', '100');
            
SELECT * FROM LIKEITEM;
SELECT * FROM CUSTOMER;
    
COMMIT;



-- ★ ★ ★ oafter table ★ ★ ★
CREATE SEQUENCE OAFTER_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE OAFTER(
    oNo NUMBER(5) PRIMARY KEY,
    cId VARCHAR2(30) REFERENCES CUSTOMER(CID),
    iNo NUMBER(5) REFERENCES ITEMLIST(INO),
    oSubject VARCHAR2(300) NOT NULL,
    oContent VARCHAR2(1000) NOT NULL,
    oFilename VARCHAR2(100),
    oReadcount NUMBER(5) DEFAULT 0,
    oPw VARCHAR2(30) NOT NULL,
    oDate DATE DEFAULT SYSDATE);
-- 후기목록
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
        (SELECT * FROM OAFTER ORDER BY ONO DESC) A)
    WHERE RN BETWEEN 2 AND 8;
-- 후기갯수
SELECT COUNT(*) FROM OAFTER;
-- 후기쓰기
INSERT INTO OAFTER(ONO, CID, INO, OSUBJECT, OCONTENT, OFILENAME, OPW)
    VALUES(OAFTER_SEQ.NEXTVAL, 'AAA', '3', '후기입니다', '마음에 들어요', NULL, 'AAA');
-- oReadcount 하나 올리기
UPDATE OAFTER SET OREADCOUNT = OREADCOUNT + 1 WHERE ONO = 3;
-- 후기 상세보기(oNo로 글 dto보기)
SELECT O.*, CNAME FROM OAFTER O, CUSTOMER C WHERE O.CID=C.CID AND ONO=3;
-- 후기 수정하기(ONO, INO, OSUBJECT, OCONTENT, OFILENAME, OPW, ODATE)
UPDATE OAFTER
    SET OSUBJECT = '짱짱좋아요',
        OCONTENT = '도매구매할게요',
        OFILENAME = NULL,
        OPW = '222',
        ODATE = SYSDATE
    WHERE ONO = 3;
-- 후기 삭제하기
DELETE FROM OAFTER WHERE ONO=4;

SELECT * FROM OAFTER;
SELECT * FROM CUSTOMER;
COMMIT;



-- ★ ★ ★ nboard table ★ ★ ★
CREATE SEQUENCE NBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE NBOARD (
    nNo NUMBER(5) PRIMARY KEY,
    aId VARCHAR2(30) REFERENCES ADMIN(AID),
    nSubject VARCHAR2(300) NOT NULL,
    nContent VARCHAR2(1000) NOT NULL,
    nFilename VARCHAR2(100),
    nReadcount NUMBER(5) DEFAULT 0,
    nPw VARCHAR2(30) NOT NULL,
    nDate DATE DEFAULT SYSDATE);
SELECT * FROM NBOARD;
-- 공지사항 목록
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
        (SELECT * FROM NBOARD ORDER BY NNO DESC) A)
    WHERE RN BETWEEN 1 AND 7;
-- 공지사항 글갯수
SELECT COUNT(*) FROM NBOARD;
-- 공지사항 글쓰기
INSERT INTO NBOARD(NNO, AID, NSUBJECT, NCONTENT, NFILENAME, NPW)
    VALUES(NBOARD_SEQ.NEXTVAL, 'ADMIN', '첫번째 공지사항', '등업신청을 모두 해주세요', NULL, 'ADMIN');
-- nReadcount 하나 올리기
UPDATE NBOARD SET NREADCOUNT = NREADCOUNT + 1 WHERE NNO = 2;
-- 글 상세보기
SELECT N.*, ANAME FROM NBOARD N, ADMIN A WHERE N.AID=A.AID AND NNO=2;
-- NNO로 글 dto보기
SELECT * FROM NBOARD WHERE NNO=2;
-- 공지사항 수정하기(NNO, NSUBJECT, NCONTENT, NFILENAME, NPW, NDATE)
UPDATE NBOARD
    SET NSUBJECT = '[수정]두번째 공지사항',
        NCONTENT = '등업할사람누구',
        NFILENAME = NULL,
        NPW = 'ADMIN',
        NDATE = SYSDATE
    WHERE NNO = 2;
-- 공지사항 글삭제하기
DELETE FROM NBOARD WHERE NNO=1;
SELECT * FROM NBOARD;
COMMIT;
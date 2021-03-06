CREATE TABLE  "EVENT" 
   (	"EVENTNUM" NUMBER(20,0), 
	"EVENTGROUP" NUMBER(10,0), 
	"USER1" NUMBER(5,0), 
	"USER2" NUMBER(5,0), 
	"MESSAGE" VARCHAR2(200), 
	"CHECKED" NUMBER(1,0), 
	 CONSTRAINT "EVENT_PK" PRIMARY KEY ("EVENTNUM") ENABLE
   )
/

CREATE TABLE  "GAMEUSER" 
   (	"USERNUM" NUMBER, 
	"USERID" VARCHAR2(20), 
	"NICK" VARCHAR2(20), 
	"PASS" VARCHAR2(20), 
	"PLAY" NUMBER, 
	"IMAGE" VARCHAR2(100), 
	 CONSTRAINT "GAMEUSER_PK" PRIMARY KEY ("USERNUM") ENABLE, 
	 CONSTRAINT "GAMEUSER_CON" UNIQUE ("USERID") ENABLE
   )
/

CREATE TABLE  "ITEM" 
   (	"ITEMNUM" NUMBER(5,0), 
	"ITYPE" NUMBER(5,0), 
	"STAT" NUMBER(5,0), 
	"ITEMNAME" VARCHAR2(20), 
	 CONSTRAINT "ITEM_PK" PRIMARY KEY ("ITEMNUM") ENABLE
   )
/

CREATE TABLE  "ITEMMAP" 
   (	"IMNUM" NUMBER(5,0), 
	"MAPNUM" NUMBER(5,0), 
	"ITEMNUM" NUMBER(5,0), 
	"CHANCE" NUMBER(5,0), 
	 CONSTRAINT "ITEMMAP_PK" PRIMARY KEY ("IMNUM") ENABLE
   )
/

CREATE TABLE  "NOTICE" 
   (	"NOTICENUM" NUMBER(5,0), 
	"TITLE" VARCHAR2(50), 
	"CONTENT" VARCHAR2(500), 
	"NDATE" DATE, 
	 CONSTRAINT "NOTICE_PK" PRIMARY KEY ("NOTICENUM") ENABLE
   )
/

CREATE TABLE  "STATUS" 
   (	"USERNUM" NUMBER(5,0), 
	"MAXHEALTH" NUMBER(5,0), 
	"HEALTH" NUMBER(5,0), 
	"POWER" NUMBER(5,0), 
	"STAMINA" NUMBER(5,0), 
	"KILL" NUMBER(5,0), 
	"DEATH" NUMBER(5,0), 
	"LOCATION" NUMBER(5,0), 
	"DECISION" NUMBER(1,0), 
	"ITEMNUM" NUMBER(5,0), 
	 CONSTRAINT "STATUS_PK" PRIMARY KEY ("USERNUM") ENABLE
   )
/

CREATE TABLE  "USERITEM" 
   (	"ULNUM" NUMBER(5,0), 
	"USERNUM" NUMBER(5,0), 
	"ITEMNUM" NUMBER(5,0), 
	"QUANTITY" NUMBER(5,0), 
	 CONSTRAINT "USERITEM_PK" PRIMARY KEY ("ULNUM") ENABLE
   )
/

CREATE SEQUENCE "EV_SEQ"
 
CREATE SEQUENCE "IM_SEQ"

CREATE SEQUENCE "UI_SEQ"
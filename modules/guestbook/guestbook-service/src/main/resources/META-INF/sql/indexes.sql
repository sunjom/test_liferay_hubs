create index IX_67F935A1 on sdr_Guestbook (groupId);
create unique index IX_45F9809F on sdr_Guestbook (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7E8658D7 on sdr_GuestbookEntry (groupId, guestbookId);
create unique index IX_9F3CE78B on sdr_GuestbookEntry (uuid_[$COLUMN_LENGTH:75$], groupId);
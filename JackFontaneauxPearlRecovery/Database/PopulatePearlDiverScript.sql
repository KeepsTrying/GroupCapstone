USE PearlDiver;

-- Delete Test Data from Depended tables
DELETE FROM Comments WHERE CommentId >= 0;
DELETE FROM PagesUsers WHERE PageId >= 0;
DELETE FROM PostsUsers WHERE PostId >= 0;
DELETE FROM PagesImages WHERE PageId >= 0;
DELETE FROM PostsImages WHERE PostId >= 0;
DELETE FROM PostsTags WHERE PostId >= 0;
DELETE FROM PostsCategories WHERE PostId >= 0;


-- Delete Test Data from Dependent Tables
DELETE FROM Tags WHERE TagId >= 0;
DELETE FROM Categories WHERE CategoryId >= 0;
DELETE FROM Posts WHERE PostId >= 0;
DELETE FROM Users WHERE UserId >= 0;
DELETE FROM Images WHERE ImageId >= 0;
DELETE FROM ImageTypes WHERE ImageTypeId >= 0;
DELETE FROM StaticPages WHERE PageId >= 0;
DELETE FROM PageCategories WHERE PageCategoryId >= 0;

INSERT INTO Tags (TagId, Tag)
VALUES (1, "#sweet"), (2, "#cool"), (3, "#awesome"), (4, "#DRY");


INSERT INTO Categories (CategoryId, Category)
VALUES (1, "Diving"), (2, "Snorkling"), (3, "Oysters"), (4, "Rando");


INSERT INTO PageCategories (PageCategoryId, PageCategory)
VALUES (1, "Bio"), (2, "FAQ"), (3, "Contact Info"), (4, "Predetermined Category"),(5, "Events"), (6, "About Us");


INSERT INTO Users (UserId, UserName, Password, Enabled, Role, FirstName, LastName, Email, Latitude, Longitude)
VALUES (1, "admin01", "$2a$10$tXCb/DLv/f.qobd0MAWI1ue.k2WiOZyJfDQLcVNv5KuHzJyAJbkFS", 1, "Admin", "thisFirst", "thisLast", "email@gmail.com", 38.2527, -85.7585),
(2, "user01", "$2a$10$tXCb/DLv/f.qobd0MAWI1ue.k2WiOZyJfDQLcVNv5KuHzJyAJbkFS", 1, "Author", "thatFirst", "thatLast", "that@email.com", 66.6666, 66.6666);

INSERT INTO ImageTypes (ImageTypeId, ImageType)
VALUES (1, "preview"), (2, "header"), (3, "page"), (4, "post");


INSERT INTO Images (ImageId, Path, ImageTypeId)
VALUES (1, "http://i.imgur.com/XfVeWJR.jpg", 2), (2, "http://i.imgur.com/RRaLwxY.jpg", 2), (3, "http://i.imgur.com/DAKWjtK.jpg", 2), (4, "http://i.imgur.com/B7VXQLt.jpg", 2), (5, "http://i.imgur.com/lINwvS4.jpg", 1), (6, "http://i.imgur.com/dgMVusT.jpg", 1), (7,  "http://i.imgur.com/Sq8YsZi.jpg", 1), (8, "http://i.imgur.com/c8o5e1n.jpg", 1), (9, "http://i.imgur.com/xmSkX5G.jpg", 1), (10, "http://i.imgur.com/rAJpNkf.jpg", 3), (11, "http://i.imgur.com/2h5dgR6.jpg", 3), (12, "http://i.imgur.com/1KeEF3P.jpg", 3), (13, "http://i.imgur.com/63UeSkq.jpg", 3), (14, "http://i.imgur.com/u1LWDOB.jpg", 4), (15, "http://i.imgur.com/OJukXeu.jpg", 4), (16, "http://i.imgur.com/hZQPSki.jpg", 4), (17, "http://i.imgur.com/OMnOgw7.jpg", 4);


INSERT INTO StaticPages (PageId, PageCategoryId, NavName, Title, PublishDate, TextArea, Published, Completed)
VALUES (1, 2, "Read First!", "Our Site's FAQ", "2017-07-18", "This is the where users would learn about the company and site.", 1, 1), 
(2, 1, "A Bio Page", "Author's Bio", "2017-07-19", "Second static page will have this text body", 0, 1);


INSERT INTO Posts (PostId, PostDate, Title, Synopsis, Body, Published, Completed, Latitude, Longitude)
VALUES (1, "2017-07-18", "First Blog Post", "Quick synopsis for this Post", "Text area for the first Post", 1, 1, 39.2904, -76.6122), 
(2, "2017-07-19", "Unpublished Post", "This post is unfinished", "This will be an awesome blog foreal", 0, 0, 59.7121, -85.2539);

INSERT INTO Comments (CommentId, PostId, Comment, Name)
VALUES (1, 1, "A comment for the first blog post","Shnibbles"),
(2, 1, "Another comment for the first blog post","Toodles"),
(3, 2, "The first comment for the second blog post","Myahhs"),
(4, 2, "Another comment for the second blog post","BleeBloop");

INSERT INTO Authorities (UserName, Authority) 
VALUES ('admin01', 'ROLE_ADMIN'),('admin01', 'ROLE_USER'),('user01','ROLE_USER');

INSERT INTO PostsTags (PostId, TagId)
VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 4);


INSERT INTO PostsCategories (PostId, CategoryId)
VALUES (1, 1), (1, 3), (2, 1), (2, 4);


INSERT INTO PostsUsers (PostId, UserId)
VALUES (1, 1), (2, 1), (2, 2);


INSERT INTO PagesUsers (PageId, UserId)
VALUES (1, 1), (1, 2), (2, 1);


INSERT INTO PostsImages (PostId, ImageId)
VALUES (1, 1), (1, 2), (1, 5), (2, 3);


INSERT INTO PagesImages (PageId, ImageId)
VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 3), (1,11);